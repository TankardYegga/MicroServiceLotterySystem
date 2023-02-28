package org.example.infrastructure.dao;

import org.example.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/26/2023
 * Time: 11:43 PM
 * Email: levinforward@163.com
 */
//@Mapper
public interface IStrategyDetailDao {

    /**
     * description: 查询某个策略的详细策略配置：这个策略与奖品之间的关系
     * @param strategyId 策略ID
     * @return java.util.List<com.example.infrastructure.po.StrategyDetail>
     */
    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * description: 扣减库存
     * @param strategyDetailReq 被扣减的策略 被扣减的奖品
     * @return boolean 扣减结果
     */
    int deductStock(StrategyDetail strategyDetailReq);
}
