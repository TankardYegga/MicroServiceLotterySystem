package com.example.domain.strategy.service.algorithm.impl;

import com.example.domain.strategy.service.algorithm.BaseAlgorithm;

import java.security.SecureRandom;
import java.util.List;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/27/2023
 * Time: 12:04 PM
 * Email: levinforward@163.com
 */
public class SimpleRateRandomDrawAlgorithm extends BaseAlgorithm {


    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        //首先获取该策略的奖品抽奖概率元祖（概率和奖品的对应关系的Map）
        String[] rateTuple = super.rateTupleMap.get(strategyId);

        // 生成随机数，放大到100后进行哈希映射，从概率元组中获取抽中的奖品ID，再判断该奖品是否已经被排除了
        int randVal = new SecureRandom().nextInt(100) + 1;
        int idx = super.hashIdx(randVal);

        String awardId = rateTuple[idx];
        if(excludeAwardIds.contains(awardId)) return "未获奖";

        return awardId;
    }


}
