package org.example.infrastructure.dao;

import org.example.domain.activity.model.vo.ActivityVO;
import org.example.domain.activity.model.vo.AlterStateVO;
import org.example.infrastructure.po.Activity;

import java.util.List;


/**
 * @author levin
 */
//@Mapper
public interface IActivityDao {

   void insert(Activity req);

   Activity queryActivityById(Long activityId);

    int alterActivityState(AlterStateVO alterStateVO);

    int subtractionActivityStock(Long activityId);

    List<ActivityVO> scanToDoActivityList(long id);
}
