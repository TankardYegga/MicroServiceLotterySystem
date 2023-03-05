package org.example.domain.activity.service.partake;

import org.apache.ibatis.annotations.Mapper;
import org.example.common.Constants;
import org.example.common.Result;
import org.example.domain.activity.model.req.PartakeReq;
import org.example.domain.activity.model.res.PartakeResult;
import org.example.domain.activity.model.vo.ActivityBillVO;
import org.example.domain.support.ids.IIdGenerator;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 活动领取的模板抽象类
 * @author： tankardyegga, 微信号:同名
 * @date: 3/4/2023
 * @Copyright： levinforward@163.com
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake {

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public PartakeResult doPartake(PartakeReq req) {

        //查询活动账单
        ActivityBillVO activityBillVO = super.queryActivityBill(req);

        //活动信息的校验处理
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())){
            return new PartakeResult(checkResult.getCode(), checkResult.getInfo());
        }

        //扣减活动库存: 当前直接在表格中进行库存减小，后续优化使用redis进行扣减
        Result substractionActivityResult = this.substractionActivityStock(req);
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(substractionActivityResult.getCode())){
            return new PartakeResult(substractionActivityResult.getCode(), substractionActivityResult.getInfo());
        }

        //领取活动信息[用户把个人信息写到活动表]
        Long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        Result grabResult = this.grabActivity(req, activityBillVO, takeId);
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())){
            return new PartakeResult(grabResult.getCode(), grabResult.getInfo());
        }

        //封装结果【返回策略ID,用于继续进行抽奖】
        PartakeResult partakeResult = new PartakeResult(Constants.ResponseCode.SUCCESS.getCode(),
                Constants.ResponseCode.SUCCESS.getInfo());
        partakeResult.setStrategyId(partakeResult.getStrategyId());
        partakeResult.setTakeId(takeId);
        return partakeResult;
    }

    protected abstract Result checkActivityBill(PartakeReq req, ActivityBillVO activityBillVO);

    protected abstract Result substractionActivityStock(PartakeReq req);

    protected abstract Result grabActivity(PartakeReq req, ActivityBillVO activityBillVO, Long takeId);
}
