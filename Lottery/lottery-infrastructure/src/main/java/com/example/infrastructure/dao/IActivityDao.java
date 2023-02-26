package com.example.infrastructure.dao;

import com.example.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description: lottery-5215-tankard
 * Creator: levin
 * Date: 2/25/2023
 * Time: 8:58 PM
 * Email: levinforward@163.com
 */
//@Mapper
public interface IActivityDao {

    void insert(Activity activity);

    Activity queryActivityById(Long activityId);
}



