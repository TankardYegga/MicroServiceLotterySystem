package org.example.domain.activity.repository;

import org.example.common.Constants;
import org.example.domain.activity.model.req.PartakeReq;
import org.example.domain.activity.model.vo.*;

import java.util.List;

/**
 * @description: 活动仓储服务（活动表、策略表、奖品配置表、策略明细表）
 * @author： tankardyegga, 微信号:同名
 * @date: 3/1/2023
 * @Copyright： levinforward@163.com
 */
public interface IActivityRepository {

    /**
     * 添加活动配置类
     * @param activityVO 活动配置类
     */
    void addActivity(ActivityVO activityVO);

    /**
     * 添加策略配置
     * @param strategyVO 策略配置类
     */
    void addStrategy(StrategyVO strategyVO);

    /**
     * 添加奖品配置集合
     * @param awardVOList 奖品配置类列表
     */
    void addAwardList(List<AwardVO> awardVOList);

    /**
     * 添加策略明细列表
     * @param strategyDetailVOList 策略明细列表
     */
    void addStrategyDetailList(List<StrategyDetailVO> strategyDetailVOList);

    /**
     * 变更活动状态
     * @param activityId 活动ID
     * @param beforeState 修改前状态
     * @param afterState 修改后状态
     * @return: 更新结果
     */
    boolean alterActivityState(Long activityId, Enum<Constants.ActivityState> beforeState,
                               Enum<Constants.ActivityState> afterState);


    /**
     * 查询用户参与活动的账单信息[用户信息、活动（ID,名字、库存、状态）、日期、策略、个人参与次数]
     * @param req 用户参与活动请求
     * @return 用户活动账单信息
     */
    ActivityBillVO queryActivityBill(PartakeReq req);

    /**
     * 扣减活动库存
     * @param activityId 活动ID
     * @return 扣减结果
     */
    int substractionActivityStock(Long activityId);
}
