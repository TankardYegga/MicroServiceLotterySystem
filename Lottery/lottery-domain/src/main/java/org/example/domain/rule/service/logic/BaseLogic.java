package org.example.domain.rule.service.logic;

import org.example.common.Constants;
import org.example.domain.rule.model.req.DecisionMatterReq;
import org.example.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/5/2023
 * @Copyright： levinforward@163.com
 */
public abstract class BaseLogic implements LogicFilter{

    @Override
    public Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineVOInfoList) {
        for(TreeNodeLineVO nodeLine: treeNodeLineVOInfoList){
            if(decisionLogic(matterValue, nodeLine)){
                return nodeLine.getNodeIdTo();
            }
        }
        return Constants.Global.TREE_NULL_NODE;
    }

    @Override
    public abstract String matterValue(final DecisionMatterReq decisionMatter);

    private boolean decisionLogic(String matterValue, TreeNodeLineVO nodeLine){
        switch (nodeLine.getLimitType()){
            case Constants.RuleLimitType.EQ:
                return matterValue.equals(nodeLine.getLimitValue());
            case Constants.RuleLimitType.GT:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLine.getLimitValue());
            case Constants.RuleLimitType.LT:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLine.getLimitValue());
            case Constants.RuleLimitType.GE:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLine.getLimitValue());
            case Constants.RuleLimitType.LE:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLine.getLimitValue());
            default:
                return false;
        }
    }
}
