package org.example.domain.rule.model.vo;

import java.util.List;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/5/2023
 * @Copyright： levinforward@163.com
 */
public class TreeNodeVO {

    private Long treeId;

    private Long treeNodeId;

    private Integer nodeType;

    private String nodeValue;

    private String ruleKey;

    private String ruleDesc;

    private List<TreeNodeLineVO> treeNodeLineVOList;

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeNodeId() {
        return treeNodeId;
    }

    public void setTreeNodeId(Long treeNodeId) {
        this.treeNodeId = treeNodeId;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getRuleKey() {
        return ruleKey;
    }

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public List<TreeNodeLineVO> getTreeNodeLineVOList() {
        return treeNodeLineVOList;
    }

    public void setTreeNodeLineVOList(List<TreeNodeLineVO> treeNodeLineVOInfoList) {
        this.treeNodeLineVOList = treeNodeLineVOInfoList;
    }

    @Override
    public String toString() {
        return "TreeNodeVO{" +
                "treeId=" + treeId +
                ", treeNodeId=" + treeNodeId +
                ", nodeType=" + nodeType +
                ", nodeValue='" + nodeValue + '\'' +
                ", ruleKey='" + ruleKey + '\'' +
                ", ruleDesc='" + ruleDesc + '\'' +
                ", treeNodeLineVOInfoList=" + treeNodeLineVOList +
                '}';
    }
}
