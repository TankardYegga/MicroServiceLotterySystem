package org.example.domain.strategy.service.algorithm;

import org.example.domain.strategy.model.vo.AwardRateVO;

import java.util.List;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/27/2023
 * Time: 9:09 AM
 * Email: levinforward@163.com
 */
public interface IDrawAlgorithm {

    /**
     * description: 依据策略和该策略对应的不同奖品的获奖信息，来初始化概率元祖
     * @param strategyId 策略ID
     * @param awardRateVOList 奖品概率信息列表
     */
    void initRateTuple(Long strategyId, List<AwardRateVO> awardRateVOList);

    //判断某个策略对应的数据是否已经进行了初始化
    boolean isExistRateTuple(Long strategyId);

    //按照某种策略进行随机抽奖，获得对应的奖品信息
    //要排除掉已经不能用作抽奖的奖品ID，这些奖品将用于风控和库存等其他目的
    String randomDraw(Long strategyId, List<String> excludeAwardIds);
}

