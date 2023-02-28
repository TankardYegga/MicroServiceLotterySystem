package org.example.domain.strategy.repository.iml;

import org.example.domain.strategy.model.aggregates.StrategyRich;
import org.example.domain.strategy.repository.IStrategyRepository;
import org.example.infrastructure.dao.IAwardDao;
import org.example.infrastructure.dao.IStrategyDao;
import org.example.infrastructure.dao.IStrategyDetailDao;
import org.example.infrastructure.po.Award;
import org.example.infrastructure.po.Strategy;
import org.example.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/26/2023
 * Time: 11:59 PM
 * Email: levinforward@163.com
 */
@Component
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


    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        StrategyDetail strategyDetailReq = new StrategyDetail();
        strategyDetailReq.setStrategyId(strategyId);
        strategyDetailReq.setAwardId(awardId);
        int count = strategyDetailDao.deductStock(strategyDetailReq);
        return count == 1;
    }
}
