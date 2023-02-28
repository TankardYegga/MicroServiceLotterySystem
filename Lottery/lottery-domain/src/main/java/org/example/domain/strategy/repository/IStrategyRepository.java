package org.example.domain.strategy.repository;

import org.example.domain.strategy.model.aggregates.StrategyRich;
import org.example.infrastructure.po.Award;

import java.util.List;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/26/2023
 * Time: 11:55 PM
 * Email: levinforward@163.com
 */
public interface IStrategyRepository {

    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String awardId);

    List<String> queryNoStockStrategyAwardList(Long strategyId);

    boolean deductStock(Long strategyId, String awardId);
}
