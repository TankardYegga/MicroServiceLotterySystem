package org.example.domain.rule.service.engine;

import org.example.common.Constants;
import org.example.domain.rule.model.aggregates.TreeRuleRich;
import org.example.domain.rule.model.req.DecisionMatterReq;
import org.example.domain.rule.model.res.EngineResult;
import org.example.domain.rule.model.vo.TreeNodeVO;
import org.example.domain.rule.model.vo.TreeRootVO;
import org.example.domain.rule.service.logic.LogicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * @description: 规则引擎基础类
 * @author： tankardyegga, 微信号:同名
 * @date: 3/5/2023
 * @Copyright： levinforward@163.com
 */
public abstract class EngineBase extends EngineConfig implements EngineFilter{

    private Logger logger = LoggerFactory.getLogger(EngineBase.class);

    @Override
    public EngineResult process(DecisionMatterReq matter) {
        throw  new RuntimeException("未实现规则引擎");
    }

    /**
     * 给定规则树和决策物料请求，得到果实结点的信息
     */
    protected TreeNodeVO engineDecisionMaker(TreeRuleRich treeRuleRich, DecisionMatterReq decisionMatter){
        //获取规则数的根结点，以及每个结点ID与结点的Map
        TreeRootVO treeRootVO = treeRuleRich.getTreeRoot();
        Map<Long, TreeNodeVO> treeNodeVOMap = treeRuleRich.getTreeNodeMap();


        // 从根结点出发，一直往下进行决策树的决策判断过程
        // 当前结点的判断过程是：
        // 判断当前结点的类型：
        // 如果是FRUIT类型，则向下决策的过程终止；
        // 如果是STEM类型，则：
        // 【1】从 树根结点 可以获得当前树的ID、树的名称、以及 结点本身的ID
        // 【2】从Map中 依据 key = 结点本身ID 来获取对应的TreeNodeVO对象
        // 【3】从TreeNodeVO对象来获取当前结点的比较关键字
        // 【4】依据该关键字从EngineConfig中获取该关键字的logicFilter
        // 注：不同关键字的logicFilter的区别仅仅在于其获取的决策值不同
        // 其依据当前决策值和所有决策分支信息来获取下一个结点ID的逻辑都是完全一样的
        // 【5】利用logicFilter先计算一次决策值
        // 【6】从TreeNodeVO对象来获取当前结点出发的所有决策分支信息
        // 【7】利用logicFilter来计算下一个结点ID

        Long treeId = treeRootVO.getTreeId();
        String treeName = treeRootVO.getTreeName();
        Long treeRootNodeId = treeRootVO.getTreeRootNodeId();
        TreeNodeVO treeNodeVO = treeNodeVOMap.get(treeRootNodeId);

        System.out.println("Current tree: treeId is" + treeId + "treeName is " + treeName);
        while (Constants.NodeType.STEM.equals(treeNodeVO.getNodeType())){
            String ruleKeyName = treeNodeVO.getRuleKey();
            System.out.println("ruleKeyName:" + ruleKeyName);
            LogicFilter logicFilter = logicFilterMap.get(ruleKeyName);
            String matterValue = logicFilter.matterValue(decisionMatter);
            Long nextTreeNodeId = logicFilter.filter(matterValue, treeNodeVO.getTreeNodeLineVOList());
            System.out.println("Matter value of key " + ruleKeyName + "is:" + matterValue);
            treeNodeVO = treeNodeVOMap.get(nextTreeNodeId);
        }

        return treeNodeVO;

    }
}
