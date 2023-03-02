package org.example.domain.activity.service.stateflow;

import org.example.common.Constants;
import org.example.common.Result;
import org.example.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/1/2023
 * @Copyright： levinforward@163.com
 */
public abstract class AbstractState {

    @Resource
    protected IActivityRepository activityRepository;

    /**
     * 对活动进行提审操作
     * @param activityId 活动ID
     * @param currentState 当前状态
     * @return 活动提审的结果
     */
    public abstract Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 审核通过
     * @param activityId  活动ID
     * @param currentState 当前
     * @return 审核通过的结果
     */
    public abstract Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 撤销审核
     * @param activityId
     * @param currentState
     * @return
     */
    public abstract Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 拒绝活动
     * @param activityId
     * @param currentState
     * @return
     */
    public abstract Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 执行活动
     * @param activityId
     * @param currentState
     * @return
     */
    public abstract Result doing(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 关闭当前活动
     * @param activityId
     * @param currentState
     * @return
     */
    public abstract Result close(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 打开当前活动
     * @param activityId
     * @param currentState
     * @return
     */
    public abstract Result open(Long activityId, Enum<Constants.ActivityState> currentState);
}
