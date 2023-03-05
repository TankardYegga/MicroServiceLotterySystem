package org.example.domain.strategy.model.req;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/26/2023
 * Time: 11:23 PM
 * Email: levinforward@163.com
 */
public class DrawReq {

    private String uId;

    private Long strategyId;

    /**防重ID*/
    private String uuId;

    public DrawReq(){

    }

    public DrawReq(String uId, Long strategyId) {
        this.uId = uId;
        this.strategyId = strategyId;
    }

    public DrawReq(String uId, Long strategyId, String uuId) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.uuId = uuId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }
}
