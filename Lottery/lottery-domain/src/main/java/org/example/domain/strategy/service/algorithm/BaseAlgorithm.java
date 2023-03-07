package org.example.domain.strategy.service.algorithm;

import org.example.common.Constants;
import org.example.domain.strategy.model.vo.AwardRateVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/27/2023
 * Time: 9:18 AM
 * Email: levinforward@163.com
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm{

    private final int HASH_INCREMENT = 0x61c88647;

    private final int RATE_TUPLE_LENGTH = 128;

    //一个策略对应一个概率元组，也就是该策略下不同概率抽中的奖品信息
    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    //一个策略所对应的：该策略可以获得的奖品以及在该策略下不同奖品的获奖概率
    protected Map<Long, List<AwardRateVO>> awardRateInfoMap = new ConcurrentHashMap<>();

    @Override
    public synchronized void initRateTuple(Long strategyId, Integer strategyMode, List<AwardRateVO> awardRateVOList) {

        // 前置判断
        if (isExistRateTuple(strategyId)){
            return;
        }

        // 非单项概率，不必存入缓存，因为这部分抽奖算法需要实时处理中奖概率。
        if (!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)) {
            return;
        }

        //保存奖品概率信息
        awardRateInfoMap.put(strategyId, awardRateVOList);

        //计算当前策略对应的概率元祖:
        // 如果已经存在，就直接返回；
        // 如果不存在，就在该Map中添加该策略ID一个新的key，
        // 其对应的value是一个长度为128的未初始化的String数组
        String[] tupleMap = rateTupleMap.computeIfAbsent(strategyId,
                k -> new String[RATE_TUPLE_LENGTH]);

        int cursorVal = 0;
        for(AwardRateVO awardRateVO : awardRateVOList){
            int rateVal = awardRateVO.getAwardRate().multiply(new BigDecimal(100)).intValue();
            for(int i = cursorVal + 1; i <= cursorVal + rateVal; i++){
                tupleMap[hashIdx(i)] = awardRateVO.getAwardId();
            }
            cursorVal += rateVal;
        }

    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return awardRateInfoMap.containsKey(strategyId);
    }


    protected int hashIdx(int val){
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }
}
