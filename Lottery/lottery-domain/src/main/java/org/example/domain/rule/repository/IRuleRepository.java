package org.example.domain.rule.repository;

import org.example.domain.rule.model.aggregates.TreeRuleRich;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/6/2023
 * @Copyright： levinforward@163.com
 */
public interface IRuleRepository {

    /**
     * 查询规则决策树的配置
     * @param treeId 决策树的ID
     * @return 决策树的配置
     */
    TreeRuleRich queryTreeRuleRich(Long treeId);
}
