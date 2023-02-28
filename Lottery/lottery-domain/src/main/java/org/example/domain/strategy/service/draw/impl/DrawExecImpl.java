package org.example.domain.strategy.service.draw.impl;

import org.example.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.example.domain.strategy.service.draw.AbstractDrawBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 2/27/2023
 * @Copyright： levinforward@163.com
 */
public class DrawExecImpl extends AbstractDrawBase {

    private Logger logger = LoggerFactory.getLogger(DrawExecImpl.class);

    @Override
    protected List<String> queryExcludeAwardIds(Long strategyId) {
        List<String> awardIds = strategyRepository.queryNoStockStrategyAwardList(strategyId);
        logger.info("现在执行的抽奖策略是 strategyId: {}, 目前排除在外的奖品列表是 awardList：{}",
                strategyId, JSON.toJSONString(awardIds));
        return awardIds;
    }


    @Override
    protected String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds) {

        String awardId = drawAlgorithm.randomDraw(strategyId, excludeAwardIds);

        if(null == awardId){
            return null;
        }

        //对当前策略、当前奖品进行扣减库存
        boolean isSuccess = strategyRepository.deductStock(strategyId, awardId);

        return isSuccess ? awardId: null;
    }
}
