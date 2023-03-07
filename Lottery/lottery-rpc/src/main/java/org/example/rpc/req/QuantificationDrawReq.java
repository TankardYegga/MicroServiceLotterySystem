package org.example.rpc.req;

import java.io.Serializable;
import java.util.Map;

/**
 * @description: 量化人群的抽奖请求参数（为啥这里不需要实现序列化接口？）
 * @author： tankardyegga, 微信号:同名
 * @date: 3/7/2023
 * @Copyright： levinforward@163.com
 */
public class QuantificationDrawReq {

    private String uId;

    private Long treeId;

    private Map<String, Object> valMap;

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

    public Map<String, Object> getValMap() {
        return valMap;
    }

    public void setValMap(Map<String, Object> valMap) {
        this.valMap = valMap;
    }
}
