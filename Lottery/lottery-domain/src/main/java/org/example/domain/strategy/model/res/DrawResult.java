package org.example.domain.strategy.model.res;

import org.example.common.Constants;
import org.example.domain.strategy.model.vo.DrawAwardVO;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/26/2023
 * Time: 11:19 PM
 * Email: levinforward@163.com
 */
public class DrawResult {

    private String uId;

    private Long strategyId;

    private Integer drawState = Constants.DrawState.FAIL.getCode();

    private DrawAwardVO drawAwardVO;

    public DrawResult() {
    }

    public DrawResult(String uId, Long strategyId, Integer drawState) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
    }

    public DrawResult(String uId, Long strategyId, Integer drawState, DrawAwardVO drawAwardVO) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
        this.drawAwardVO = drawAwardVO;
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

    public Integer getDrawState() {
        return drawState;
    }

    public void setDrawState(Integer drawState) {
        this.drawState = drawState;
    }

    public DrawAwardVO getDrawAwardInfo() {
        return drawAwardVO;
    }

    public void setDrawAwardInfo(DrawAwardVO drawAwardVO) {
        this.drawAwardVO = drawAwardVO;
    }
}
