package org.example.infrastructure.dao;

import org.example.domain.activity.model.vo.AlterStateVO;
import org.example.infrastructure.po.Activity;


/**
 * @author levin
 */
//@Mapper
public interface IActivityDao {

   void insert(Activity req);

   Activity queryActivityById(Long activityId);

    int alterActivityState(AlterStateVO alterStateVO);

    int substractionActivityStock(Long activityId);
}
