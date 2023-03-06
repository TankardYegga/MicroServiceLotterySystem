package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.po.RuleTreeNode;

import java.util.List;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/6/2023
 * @Copyright： levinforward@163.com
 */
@Mapper
public interface IRuleTreeNodeDao {

    /**
     * 查询规则树的所有规则树结点
     * @param treeId
     * @return
     */
    List<RuleTreeNode> queryRuleTreeNodeList(Long treeId);

    /**
     * 查询规则树的规则点？
     * @param treeId
     * @return
     */
    List<RuleTreeNode> queryTreeRulePoint(Long treeId);

    /**
     * 查询规则树的结点总数
     * @param treeId
     * @return
     */
    int queryRuleTreeNodeCount(Long treeId);

}
