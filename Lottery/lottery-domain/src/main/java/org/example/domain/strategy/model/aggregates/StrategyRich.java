package org.example.domain.strategy.model.aggregates;

import org.example.domain.strategy.model.vo.StrategyBriefVO;
import org.example.domain.strategy.model.vo.StrategyDetailBriefVO;

import java.util.List;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/26/2023
 * Time: 11:48 PM
 * Email: levinforward@163.com
 */
public class StrategyRich {

    //策略的Id
    private Long strategyId;

    //策略配置
    private StrategyBriefVO strategy;

    //策略细节列表
    private List<StrategyDetailBriefVO> strategyDetailList;


    public StrategyRich() {
    }

    public StrategyRich(Long strategyId, StrategyBriefVO strategy, List<StrategyDetailBriefVO> strategyDetailList) {
        this.strategyId = strategyId;
        this.strategy = strategy;
        this.strategyDetailList = strategyDetailList;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public StrategyBriefVO getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyBriefVO strategy) {
        this.strategy = strategy;
    }

    public List<StrategyDetailBriefVO> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetailBriefVO> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }
}
