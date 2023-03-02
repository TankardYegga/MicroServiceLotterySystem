package org.example.domain.activity.service.stateflow;

import org.example.common.Constants;
import org.example.common.Result;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/1/2023
 * @Copyright： levinforward@163.com
 */
public interface IStateHandler {

    Result arraignment(Long activityId, Enum<Constants.ActivityState> currentStatus);


    Result checkPass(Long activityId, Enum<Constants.ActivityState> currentStatus);


    Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentStatus);


    Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentStatus);


    Result close(Long activityId, Enum<Constants.ActivityState> currentStatus);


    Result open(Long activityId, Enum<Constants.ActivityState> currentStatus);


    Result doing(Long activityId, Enum<Constants.ActivityState> currentStatus);


}
