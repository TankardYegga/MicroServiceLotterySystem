package org.example.domain.activity.model.vo;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/5/2023
 * @Copyright： levinforward@163.com
 */
public class UserTakeActivityVO {

    private Long activityId;

    private Long takeId;

    private Long strategyId;

    private Integer state;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
