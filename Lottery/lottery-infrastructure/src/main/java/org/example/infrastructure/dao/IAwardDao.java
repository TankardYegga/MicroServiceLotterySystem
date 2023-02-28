package org.example.infrastructure.dao;

import org.example.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description: Lottery
 * Creator: levin
 * Date: 2/26/2023
 * Time: 11:26 PM
 * Email: levinforward@163.com
 */
//@Mapper
public interface IAwardDao {

    Award queryAwardInfo(String awardId);
}
