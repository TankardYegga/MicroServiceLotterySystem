package org.example.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.po.UserTakeActivity;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/3/2023
 * @Copyright： levinforward@163.com
 */
@Mapper
public interface IUserTakeActivityDao {

    /**
     * 插入用户领取活动的信息
     * @param userTakeActivity 入参
     */
    @DBRouter(key="uId")
    void insert(UserTakeActivity userTakeActivity);
}


