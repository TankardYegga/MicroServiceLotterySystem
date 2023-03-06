package org.example.domain.rule.service.engine.impl;

import org.example.domain.rule.model.aggregates.TreeRuleRich;
import org.example.domain.rule.model.req.DecisionMatterReq;
import org.example.domain.rule.model.res.EngineResult;
import org.example.domain.rule.model.vo.TreeNodeVO;
import org.example.domain.rule.repository.IRuleRepository;
import org.example.domain.rule.service.engine.EngineBase;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 规则引擎处理器
 * @author： tankardyegga, 微信号:同名
 * @date: 3/5/2023
 * @Copyright： levinforward@163.com
 */
@Service("ruleEngineHandler")
public class RuleEngineHandler extends EngineBase {

    @Resource
    private IRuleRepository ruleRepository;

    @Override
    public EngineResult process(final DecisionMatterReq decisionMatter){
        // 将处理的功能交给 规则仓储服务 的接口来实现
        // 我们已经在EngineBase当中实现了依据 决策物料和树的配置信息 来 获取果实结点的方法
        // 我们完成当前process的功能，
        // 只需要：获取树的配置信息；将TreeNodeVO的果实结点 这种类型的结果包装成EngineResult类型
        TreeRuleRich treeRuleRich = ruleRepository.queryTreeRuleRich(decisionMatter.getTreeId());
        if(null == treeRuleRich){
            throw new RuntimeException("The rule tree is null!");
        }

        TreeNodeVO treeNodeVO = engineDecisionMaker(treeRuleRich, decisionMatter);

        return new EngineResult(decisionMatter.getuId(), decisionMatter.getTreeId(), treeNodeVO.getTreeNodeId(),
                treeNodeVO.getNodeValue());
    }

}
