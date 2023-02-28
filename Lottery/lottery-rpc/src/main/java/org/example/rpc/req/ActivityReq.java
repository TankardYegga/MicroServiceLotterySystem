package org.example.rpc.req;

import java.io.Serializable;

/**
 * Description: lottery-5215-tankard
 * Creator: levin
 * Date: 2/25/2023
 * Time: 8:39 PM
 * Email: levinforward@163.com
 */
public class ActivityReq implements Serializable {

    private Long activityId;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
