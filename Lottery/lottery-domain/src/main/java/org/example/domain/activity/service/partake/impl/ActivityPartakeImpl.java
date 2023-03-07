package org.example.domain.activity.service.partake.impl;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import org.example.common.Constants;
import org.example.common.Result;
import org.example.domain.activity.model.req.PartakeReq;
import org.example.domain.activity.model.vo.ActivityBillVO;
import org.example.domain.activity.model.vo.DrawOrderVO;
import org.example.domain.activity.repository.IUserTakeActivityRepository;
import org.example.domain.activity.service.partake.BaseActivityPartake;
import org.example.domain.support.ids.IIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/4/2023
 * @Copyright： levinforward@163.com
 */
@Service
public class ActivityPartakeImpl extends BaseActivityPartake {

    private Logger logger = LoggerFactory.getLogger(ActivityPartakeImpl.class);

    @Resource
    private IUserTakeActivityRepository userTakeActivityRepository;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private IDBRouterStrategy dbRouter;


    @Override
    protected Result checkActivityBill(PartakeReq req, ActivityBillVO activityBillVO) {
        //校验：活动状态
        // 只有当前活动正在运行，用户才能参与到该活动中
        if(!Constants.ActivityState.DOING.getCode().equals(activityBillVO.getState())){
            logger.warn("当前活动状态不可用，state:{}", activityBillVO.getState());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(), "当前活动状态不可用");
        }

        //校验日期信息
        // 因为小傅哥的项目一年前创建的，所以开始日期和结束日期基本都是2021年，而这里的req.getPartakeDate()却是now()
//        if(activityBillVO.getBeginDateTime().after(req.getPartakeDate()) ||
//                activityBillVO.getEndDateTime().before(req.getPartakeDate())){
//            logger.warn("时间范围不可用，beginDate:{}, endDate:{}", activityBillVO.getBeginDateTime(),
//                    activityBillVO.getEndDateTime());
//            return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(), "时间范围不可用");
//        }

        //校验库存信息
        if(activityBillVO.getStockSurplusCount() <= 0){
            logger.warn("活动库存不足，stockSurplusCount:{}", activityBillVO.getStockSurplusCount());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(), "活动库存不足");
        }

        //校验个人剩余的可用活动参与次数
        if(null != activityBillVO.getUserTakeLeftCount() && activityBillVO.getUserTakeLeftCount() <= 0){
            logger.warn("用户个人可参与次数不足， userTakeLeftCount:{}", activityBillVO.getUserTakeLeftCount());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(), "用户个人可参与次数不足");
        }
        return Result.buildSuccessResult();
    }

    @Override
    protected Result substractionActivityStock(PartakeReq req) {
        int count = activityRepository.subtractionActivityStock(req.getActivityId());
        if(0 == count){
            logger.warn("扣减库存失败，活动id：{}", req.getActivityId());
            return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
        }
        return Result.buildSuccessResult();
    }

    @Override
    protected Result grabActivity(PartakeReq req, ActivityBillVO activityBillVO, Long takeId) {
        try{
            dbRouter.doRouter(req.getuId());
            return transactionTemplate.execute(transactionStatus -> {
                try{
                    //事物内部需要同时执行两个sql语句操作
                    //一个sql语句是将当前用户的当前所参与活动的个人可参加次数减1
                    int updatedCount = userTakeActivityRepository.substractionLeftCount(
                            activityBillVO.getActivityId(), activityBillVO.getActivityName(),
                            activityBillVO.getTakeCount(), activityBillVO.getUserTakeLeftCount(),
                            req.getuId(), req.getPartakeDate()
                    );
                    // （如果当前sql语句执行失败，那么说明当前用户没有成功领取该活动）
                    if(0 == updatedCount){
                        transactionStatus.setRollbackOnly();
                        logger.warn("用户扣减活动参与次数失败，uId:{}, activityId:{}", req.getuId(),
                                activityBillVO.getActivityId());
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                    }

                    //另一个sql语句是将当前已经完成的用户参与活动信息写入表中
                    //使用雪花算法生成订单的ID
                    userTakeActivityRepository.takeActivity(activityBillVO.getActivityId(), activityBillVO.getActivityName(),
                            activityBillVO.getTakeCount(), activityBillVO.getUserTakeLeftCount(),
                            req.getuId(), req.getPartakeDate(), takeId);

                }catch (DuplicateKeyException e){
                    transactionStatus.setRollbackOnly();
                    logger.warn("主键重复, uId:{}, activityId:{}", req.getuId(), req.getActivityId(), e);
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                };

                return Result.buildSuccessResult();
            });
        }finally {
            dbRouter.clear();
        }
    }

    @Override
    public Result recordDrawOrder(DrawOrderVO drawOrderVO) {
        try{
            dbRouter.doRouter(drawOrderVO.getuId());
            return transactionTemplate.execute(transactionStatus -> {
                try{
                    // 锁定活动领取记录
                    int lockCount = userTakeActivityRepository.lockTackActivity(drawOrderVO.getuId(),
                            drawOrderVO.getActivityId(), drawOrderVO.getTakeId());
                    if(0 == lockCount){
                        transactionStatus.setRollbackOnly();
                        logger.error("记录中奖单，个人参与活动抽奖已消耗完！ activityId: {}, uId: {}",
                                drawOrderVO.getActivityId(), drawOrderVO.getuId());
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);

                    }

                    // 保存抽奖信息
                    userTakeActivityRepository.saveUserStrategyExport(drawOrderVO);

                }catch (DuplicateKeyException e){
                    transactionStatus.setRollbackOnly();
                    logger.error("记录中奖单，唯一索引冲突 activityId: {}, uId: {}", drawOrderVO.getActivityId(),
                            drawOrderVO.getOrderId(), e);
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                };
                return Result.buildSuccessResult();
            });
        }finally {
            dbRouter.clear();
        }
    }
}
