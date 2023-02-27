package com.example.domain.strategy.repository.iml;

import com.example.domain.strategy.model.aggregates.StrategyRich;
import com.example.domain.strategy.repository.IStrategyRepository;
import com.example.infrastructure.dao.IAwardDao;
import com.example.infrastructure.dao.IStrategyDao;
import com.example.infrastructure.dao.IStrategyDetailDao;
import com.example.infrastructure.po.Award;
import com.example.infrastructure.po.Strategy;
import com.example.infrastructure.po.StrategyDetail;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/26/2023
 * Time: 11:59 PM
 * Email: levinforward@163.com
 */
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IAwardDao awardDao;

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);
        return new StrategyRich(strategyId, strategy, strategyDetailList);
    }

    @Override
    public Award queryAwardInfo(String awardId) {
        return awardDao.queryAwardInfo(awardId);
    }
}
