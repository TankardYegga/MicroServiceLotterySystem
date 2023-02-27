package com.example.domain.strategy.repository;

import com.example.domain.strategy.model.aggregates.StrategyRich;
import com.example.infrastructure.po.Award;

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
}
