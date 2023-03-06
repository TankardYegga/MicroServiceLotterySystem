package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.po.RuleTree;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/6/2023
 * @Copyright： levinforward@163.com
 */
@Mapper
public interface IRuleTreeDao {

    /**
     * 根据id查询规则树信息
     * @param id id
     * @return 规则树
     */
    RuleTree queryRuleTreeByTreeId(Long id);

    /**
     * 查询规则树简要信息
     * @param treeId 规则树ID
     * @return 简要信息
     */
    RuleTree queryTreeSummaryInfo(Long treeId);
}
