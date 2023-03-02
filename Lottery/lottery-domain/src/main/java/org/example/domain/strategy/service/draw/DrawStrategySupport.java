package org.example.domain.strategy.service.draw;

import org.example.domain.strategy.model.aggregates.StrategyRich;
import org.example.domain.strategy.model.vo.AwardBriefVO;
import org.example.domain.strategy.repository.IStrategyRepository;


import javax.annotation.Resource;

/**
 * @description: 抽奖策略的数据支持
 * @author： tankardyegga, 微信号:同名
 * @date: 2/27/2023
 * @Copyright： levinforward@163.com
 */
public class DrawStrategySupport extends DrawConfig{

    @Resource
    protected IStrategyRepository strategyRepository;

    /**
     * 依据策略id来查询策略的配置信息
     * @param strategyId 策略id
     */
    protected StrategyRich queryStrategyRich(Long strategyId){
        return strategyRepository.queryStrategyRich(strategyId);
    }

    protected AwardBriefVO queryAwardInfoById(String awardId){
        return strategyRepository.queryAwardInfo(awardId);
    }
}
