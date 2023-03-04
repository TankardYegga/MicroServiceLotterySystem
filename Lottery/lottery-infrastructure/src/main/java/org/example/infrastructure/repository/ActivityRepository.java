package org.example.infrastructure.repository;

import cn.hutool.core.bean.BeanUtil;
import org.example.common.Constants;
import org.example.domain.activity.model.req.PartakeReq;
import org.example.domain.activity.model.vo.*;
import org.example.domain.activity.repository.IActivityRepository;
import org.example.infrastructure.dao.*;
import org.example.infrastructure.po.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/1/2023
 * @Copyright： levinforward@163.com
 */
@Component
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IActivityDao activityDao;
    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;
    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Override
    public void addActivity(ActivityVO activityVO) {
        Activity req = new Activity();
        BeanUtil.copyProperties(activityVO, req);
        activityDao.insert(req);
    }

    @Override
    public void addStrategy(StrategyVO strategyVO) {
        Strategy req = new Strategy();
        BeanUtil.copyProperties(strategyVO, req);
        strategyDao.insert(req);
    }

    @Override
    public void addAwardList(List<AwardVO> awardVOList) {
        List<Award> req = new ArrayList<>();
        for(AwardVO awardVO: awardVOList){
            Award award = new Award();
            BeanUtil.copyProperties(awardVO, award);
            req.add(award);
        }
        awardDao.insertList(req);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailVOList) {
        List<StrategyDetail> req = new ArrayList<>();
        for(StrategyDetailVO strategyDetailVO: strategyDetailVOList){
            StrategyDetail strategyDetail = new StrategyDetail();
            BeanUtil.copyProperties(strategyDetailVO, strategyDetail);
            req.add(strategyDetail);
        }
        strategyDetailDao.insertList(req);
    }

    @Override
    public boolean alterActivityState(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        AlterStateVO alterStateVO = new AlterStateVO(activityId, ((Constants.ActivityState) beforeState).getCode(),
                ((Constants.ActivityState) afterState).getCode());
        int count = activityDao.alterActivityState(alterStateVO);
        return 1 == count;
    }

    @Override
    public ActivityBillVO queryActivityBill(PartakeReq req) {
        // 首先获取活动信息
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        // 获得用户领取活动的次数信息
        // 首先构造了一个用户参与活动次数的请求对象，该对象我们只知道其对应的用户ID和活动ID
        // 但是我们并不清楚其具体的个人可领取活动的总次数、已经领取活动的次数以及 创建和更新时间
        // 我们通过userTakeActivityCountDao来查询到这些并不清楚的信息
        // 查询函数的入参和返回值类型都是UserTakeActivityCountDao，但是入参只有用户和活动的编号信息用于索引
        UserTakeActivityCount userTakeActivityCountReq = new UserTakeActivityCount();
        userTakeActivityCountReq.setuId(req.getuId());
        userTakeActivityCountReq.setActivityId(req.getActivityId());
        UserTakeActivityCount userTakeActivityCount = userTakeActivityCountDao.queryUserTakeActivityCount(userTakeActivityCountReq);

        // 构造活动账单信息并返回
        ActivityBillVO activityBillVO = new ActivityBillVO();
        activityBillVO.setuID(req.getuId());
        activityBillVO.setActivityId(req.getActivityId());
        activityBillVO.setActivityName(activity.getActivityName());
        activityBillVO.setBeginDateTime(activity.getBeginDateTime());
        activityBillVO.setEndDateTime(activity.getEndDateTime());
        activityBillVO.setTakeCount(activity.getTakeCount());
        activityBillVO.setState(activity.getState());
        activityBillVO.setStrategyId(activity.getStrategyId());
        activityBillVO.setStockSurplusCount(activity.getStockSurplusCount());
        // 如果并没有在数据库中查询到该用户该活动所对应的 用户活动领取次数 对象，
        // 那么在该活动的账单信息中认为用户的活动领取次数信息是尚未可知的，所以也直接设置成null
        // 否则，两者要设置为一致。
        // 这里一定要注意的是：userTakeLeftCount的含义是用户已经消耗的活动领取次数，
        // left一般理解为剩下的，所以我怀疑这里应该是作者前后把语义搞反了？
        activityBillVO.setUserTakeLeftCount(null == userTakeActivityCount ? null: userTakeActivityCount.getLeftCount());

        return activityBillVO;
    }

    @Override
    public int substractionActivityStock(Long activityId) {
        return activityDao.substractionActivityStock(activityId);
    }
}
