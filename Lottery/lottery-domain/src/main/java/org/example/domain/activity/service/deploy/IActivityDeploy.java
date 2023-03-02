package org.example.domain.activity.service.deploy;

import org.example.domain.activity.model.req.ActivityConfigReq;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/1/2023
 * @Copyright： levinforward@163.com
 */
public interface IActivityDeploy {

    /**
     * 创建活动
     * @param activityConfigReq 活动配置信息
     */
    void createActivity(ActivityConfigReq activityConfigReq);

    /**
     * 更新活动
     * @param activityConfigReq 活动配置信息
     */
    void updateActivity(ActivityConfigReq activityConfigReq);

}
