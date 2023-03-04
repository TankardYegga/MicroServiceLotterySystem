package org.example.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.po.UserStrategyExport;

/**
 * @description: 用户策略计算结果表
 * @author： tankardyegga, 微信号:同名
 * @date: 3/3/2023
 * @Copyright： levinforward@163.com
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserStrategyExportDao {

    /**
     * 新增数据
     * @param userStrategyExport 用户策略
     */
    @DBRouter(key = "uId")
    void insert(UserStrategyExport userStrategyExport);

    /** 查询数据
     * @param uId 用户ID
     */
    @DBRouter
    UserStrategyExport queryUserStrategyExportByUId(String uId);
}




