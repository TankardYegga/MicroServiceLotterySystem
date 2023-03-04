package org.example.domain.activity.repository;

import java.util.Date;

/**
 * @description: 用户参与活动的仓储接口
 * @author： tankardyegga, 微信号:同名
 * @date: 3/4/2023
 * @Copyright： levinforward@163.com
 */
public interface IUserTakeActivityRepository {

    /**
     *  扣减用户个人参与活动的次数
     * @param activityId
     * @param activityName
     * @param takeCount
     * @param userTakeLeftCount
     * @param uId
     * @param partakeDate
     * @return
     */
    int substractionLeftCount(Long activityId, String activityName, Integer takeCount,
                              Integer userTakeLeftCount, String uId, Date partakeDate);

    /**
     * 领取活动
     * @param activityId
     * @param activityName
     * @param takeCount
     * @param userTakeLeftCount
     * @param uId
     * @param partakeDate
     * @param takeId 领取ID
     */
    void takeActivity(Long activityId, String activityName, Integer takeCount,
                      Integer userTakeLeftCount, String uId, Date partakeDate, Long takeId);


}
