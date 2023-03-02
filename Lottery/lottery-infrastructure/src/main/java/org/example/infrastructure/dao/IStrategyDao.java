package org.example.infrastructure.dao;

import org.example.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/26/2023
 * Time: 11:42 PM
 * Email: levinforward@163.com
 */
//@Mapper
public interface IStrategyDao {

    Strategy queryStrategy(Long strategyId);

    void insert(Strategy req);
}
