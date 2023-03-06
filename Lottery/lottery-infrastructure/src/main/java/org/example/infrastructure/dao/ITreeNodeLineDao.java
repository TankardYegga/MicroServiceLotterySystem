package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.po.TreeNodeLine;

import java.util.List;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/6/2023
 * @Copyright： levinforward@163.com
 */
@Mapper
public interface ITreeNodeLineDao {

    /**
     * 查询规则树的连线集合
     * @param req
     * @return
     */
    List<TreeNodeLine> queryRuleTreeNodeLineList(TreeNodeLine req);

    /**
     * 查询规则树的连线数量
     * @param treeId
     * @return
     */
    int queryRuleTreeNodeLineCount(Long treeId);


}
