package org.example.domain.rule.model.res;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/5/2023
 * @Copyright： levinforward@163.com
 */
public class EngineResult {

    private boolean isSuccess;

    private String uId;

    private Long treeId;

    private Long nodeId;

    private String nodeValue;

    public EngineResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public EngineResult(String uId, Long treeId, Long nodeId, String nodeValue) {
        this.uId = uId;
        this.treeId = treeId;
        this.nodeId = nodeId;
        this.nodeValue = nodeValue;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }
}
