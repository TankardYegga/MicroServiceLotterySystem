package org.example.domain.activity.repository;

import org.example.common.Constants;
import org.example.domain.activity.model.vo.ActivityVO;
import org.example.domain.activity.model.vo.AwardVO;
import org.example.domain.activity.model.vo.StrategyDetailVO;
import org.example.domain.activity.model.vo.StrategyVO;

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
    boolean alterActivityState(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState);
}
