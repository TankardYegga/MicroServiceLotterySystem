package org.example.domain.rule.service.logic;

import org.example.domain.rule.model.req.DecisionMatterReq;
import org.example.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * @description: 定义逻辑决策器的两个基本功能：
 * 一是依据当前的决策值和决策链路或者说决策分支来确定下一个结点ID是多少
 * 二是依据当前的决策请求返回一个决策值：
 *      这个功能在过滤引擎的接口里面也定义了类似的，不过其返回值却是EngineResult类型的
 *      包含比决策值更多的其他信息
 * @author： tankardyegga, 微信号:同名
 * @date: 3/5/2023
 * @Copyright： levinforward@163.com
 */

public interface LogicFilter {

    Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineVOInfoList);

    String matterValue(final DecisionMatterReq decisionMatter);
}
