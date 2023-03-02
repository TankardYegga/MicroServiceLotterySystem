package org.example.domain.activity.model.vo;

import java.util.Date;
import java.util.List;

/**
 * @description: 策略信息
 * @author： tankardyegga, 微信号:同名
 * @date: 3/1/2023
 * @Copyright： levinforward@163.com
 */
public class StrategyVO {

    private Long strategyId;

    private String strategyName;

    private Integer strategyMode;

    private String strategyDesc;

    /**
     * 发奖模式：即时、定时、人工
     */
    private Integer grantMode;

    private Integer grantType;

    private Date grantDate;

    private String extInfo;

    private List<StrategyDetailVO> strategyDetailVOList;


    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Integer getStrategyMode() {
        return strategyMode;
    }

    public void setStrategyMode(Integer strategyMode) {
        this.strategyMode = strategyMode;
    }

    public Integer getGrantMode() {
        return grantMode;
    }

    public void setGrantMode(Integer grantMode) {
        this.grantMode = grantMode;
    }

    public Date getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public List<StrategyDetailVO> getStrategyDetailVOList() {
        return strategyDetailVOList;
    }

    public void setStrategyDetailVOList(List<StrategyDetailVO> strategyDetailVOList) {
        this.strategyDetailVOList = strategyDetailVOList;
    }

    public String getStrategyDesc() {
        return strategyDesc;
    }

    public void setStrategyDesc(String strategyDesc) {
        this.strategyDesc = strategyDesc;
    }

    public Integer getGrantType() {
        return grantType;
    }

    public void setGrantType(Integer grantType) {
        this.grantType = grantType;
    }
}
