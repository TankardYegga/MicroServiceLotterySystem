package org.example.domain.strategy.service.algorithm.impl;

import org.example.domain.strategy.model.vo.AwardRateInfo;
import org.example.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 该策略一定会中奖，每次都会重新计算中奖范围
 * Creator: levin
 * Date: 2/27/2023
 * Time: 10:44 AM
 * Email: levinforward@163.com
 */
@Component("entiretyRateRandomDrawAlgorithm")
public class EntiretyRateRandomDrawAlgorithm extends BaseAlgorithm {

    //在给定策略id和已经排除的奖品Id的情况下，进行一次随机抽奖，返回抽奖对应的奖品Id
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        //1. 获得现有的可用的奖品抽奖概率的信息
        List<AwardRateInfo> differenceAwardRateInfoList = new ArrayList<>();
        List<AwardRateInfo> awardRateInfoList = awardRateInfoMap.get(strategyId);

        //需要计算所有可用奖品的抽奖概率之和，用于后续概率的重新计算
        BigDecimal differenceDominator = BigDecimal.ZERO;

        for(AwardRateInfo awardRateInfo: awardRateInfoList){
            String awardId = awardRateInfo.getAwardId();
            if(excludeAwardIds.contains(awardId)){
                continue;
            }
            differenceAwardRateInfoList.add(awardRateInfo);
            differenceDominator = differenceDominator.add(awardRateInfo.getAwardRate());
        }

        // 2. 生成一个1-100的随机数，
        // 判断该随机数在该策略的斐波那契散列空间里的位置，
        // 依据这个位置来得到抽奖结果
        SecureRandom random = new SecureRandom();
        int randVal = random.nextInt(100) + 1;

        String awardId = "";
        int cursorVal = 0;
        for(AwardRateInfo awardRateInfo: differenceAwardRateInfoList){
            int rateVal = awardRateInfo.getAwardRate().divide(differenceDominator, 2, BigDecimal.ROUND_UP).
                    multiply(new BigDecimal(100)).intValue();
            if(randVal <= cursorVal + rateVal){
                awardId = awardRateInfo.getAwardId();
            }
            cursorVal += rateVal;
        }

        return awardId;
    }


}
