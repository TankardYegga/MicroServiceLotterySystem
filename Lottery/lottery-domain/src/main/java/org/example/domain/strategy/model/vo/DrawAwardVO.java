package org.example.domain.strategy.model.vo;

import java.util.Date;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 2/27/2023
 * @Copyright： levinforward@163.com
 */
public class DrawAwardVO {

    private String awardId;

    private String awardName;

    private Integer awardType;

    private String awardContent;

    private Date awardDate;

    private Integer strategyMode;

    private Integer grantType;

    private Date grantDate;

    public DrawAwardVO() {
    }

    public DrawAwardVO(String awardId, Integer awardType, String awardName, String awardContent) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardType = awardType;
        this.awardContent = awardContent;
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

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }

    public Date getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(Date awardDate) {
        this.awardDate = awardDate;
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
}
