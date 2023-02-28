package org.example.infrastructure.dao;

import org.example.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author levin
 */
//@Mapper
public interface IActivityDao {

   void insert(Activity req);

   Activity queryActivityById(Long activityId);
}
