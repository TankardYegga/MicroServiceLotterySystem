package org.example.domain.activity.model.vo;

import java.util.Date;

/**
 * @description: 活动账单【用户、活动（ID、名称、库存、状态）、日期、策略、参与次数、】
 * @author： tankardyegga, 微信号:同名
 * @date: 3/4/2023
 * @Copyright： levinforward@163.com
 */
public class ActivityBillVO {

    private String uID;

    private Long activityId;

    private String activityName;

    private Integer state;

    private Integer stockSurplusCount;

    private Date beginDateTime;

    private Date endDateTime;

    private Integer takeCount;

    private Integer userTakeLeftCount;

    private Long strategyId;

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getStockSurplusCount() {
        return stockSurplusCount;
    }

    public void setStockSurplusCount(Integer stockSurplusCount) {
        this.stockSurplusCount = stockSurplusCount;
    }

    public Date getBeginDateTime() {
        return beginDateTime;
    }

    public void setBeginDateTime(Date beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getTakeCount() {
        return takeCount;
    }

    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }

    public Integer getUserTakeLeftCount() {
        return userTakeLeftCount;
    }

    public void setUserTakeLeftCount(Integer userTakeLeftCount) {
        this.userTakeLeftCount = userTakeLeftCount;
    }

    @Override
    public String toString() {
        return "ActivityBillVO{" +
                "uID='" + uID + '\'' +
                ", activityId=" + activityId +
                ", activityName=" + activityName +
                ", state=" + state +
                ", stockSurplusCount=" + stockSurplusCount +
                ", beginDateTime=" + beginDateTime +
                ", endDateTime=" + endDateTime +
                ", takeCount=" + takeCount +
                ", userTakeLeftCount=" + userTakeLeftCount +
                '}';
    }
}
