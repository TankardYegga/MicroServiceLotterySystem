package org.example.infrastructure.po;

import java.util.Date;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/3/2023
 * @Copyright： levinforward@163.com
 */
public class UserStrategyExport {

    private Long id;

    private String uId;

    private Long activityId;

    private Long orderId;

    private Long strategyId;

    private Integer strategyMode;

    private Integer grantType;

    private Date grantDate;

    private Integer grantState;

    private String awardId;

    private String awardName;

    private String awardContent;

    private Integer awardType;

    /** 防重ID */
    private String uuId;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getStrategyMode() {
        return strategyMode;
    }

    public void setStrategyMode(Integer strategyMode) {
        this.strategyMode = strategyMode;
    }

    public Integer getGrantType() {
        return grantType;
    }

    public void setGrantType(Integer grantType) {
        this.grantType = grantType;
    }

    public Date getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    public Integer getGrantState() {
        return grantState;
    }

    public void setGrantState(Integer grantState) {
        this.grantState = grantState;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
