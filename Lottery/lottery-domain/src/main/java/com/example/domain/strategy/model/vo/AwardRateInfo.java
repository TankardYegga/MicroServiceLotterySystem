package com.example.domain.strategy.model.vo;

import java.math.BigDecimal;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/26/2023
 * Time: 11:16 PM
 * Email: levinforward@163.com
 */
public class AwardRateInfo {

    private String awardId;

    private BigDecimal awardRate;

    public AwardRateInfo(){

    }

    public AwardRateInfo(String awardId, BigDecimal awardRate){
        this.awardId = awardId;
        this.awardRate = awardRate;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public BigDecimal getAwardRate() {
        return awardRate;
    }

    public void setAwardRate(BigDecimal awardRate) {
        this.awardRate = awardRate;
    }
}
