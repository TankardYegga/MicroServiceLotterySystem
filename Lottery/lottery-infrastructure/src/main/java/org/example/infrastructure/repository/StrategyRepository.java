package org.example.infrastructure.repository;

import cn.hutool.core.bean.BeanUtil;
import org.example.domain.strategy.model.aggregates.StrategyRich;
import org.example.domain.strategy.model.vo.AwardBriefVO;
import org.example.domain.strategy.model.vo.StrategyBriefVO;
import org.example.domain.strategy.model.vo.StrategyDetailBriefVO;
import org.example.domain.strategy.repository.IStrategyRepository;
import org.example.infrastructure.dao.IAwardDao;
import org.example.infrastructure.dao.IStrategyDao;
import org.example.infrastructure.dao.IStrategyDetailDao;
import org.example.infrastructure.po.Award;

import org.example.infrastructure.po.Strategy;
import org.example.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
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

        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        BeanUtil.copyProperties(strategy, strategyBriefVO);

        List<StrategyDetailBriefVO> strategyDetailBriefVOList = new ArrayList<>();
        for(StrategyDetail strategyDetail: strategyDetailList){
            StrategyDetailBriefVO strategyDetailBriefVO = new StrategyDetailBriefVO();
            BeanUtil.copyProperties(strategyDetail, strategyDetailBriefVO);
            strategyDetailBriefVOList.add(strategyDetailBriefVO);

        }
        return new StrategyRich(strategyId, strategyBriefVO, strategyDetailBriefVOList);
    }

    @Override
    public AwardBriefVO queryAwardInfo(String awardId) {

        Award award = awardDao.queryAwardInfo(awardId);

        AwardBriefVO awardBriefVO = new AwardBriefVO();
        awardBriefVO.setAwardId(award.getAwardId());
        awardBriefVO.setAwardName(award.getAwardName());
        awardBriefVO.setAwardType(award.getAwardType());
        awardBriefVO.setAwardContent(award.getAwardContent());

        return awardBriefVO;
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
