package org.example.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.po.UserTakeActivity;
import org.example.infrastructure.po.UserTakeActivityCount;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/4/2023
 * @Copyright： levinforward@163.com
 */
@Mapper
public interface IUserTakeActivityCountDao {

    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount userTakeActivityCountReq);

    void insert(UserTakeActivityCount userTakeActivityCount);

    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);

}
