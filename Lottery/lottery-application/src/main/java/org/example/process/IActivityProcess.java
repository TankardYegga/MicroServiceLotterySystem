package org.example.process;

import org.example.domain.rule.model.req.DecisionMatterReq;
import org.example.process.req.DrawProcessReq;
import org.example.process.res.DrawProcessResult;
import org.example.process.res.RuleQuantificationCrowdResult;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/5/2023
 * @Copyright： levinforward@163.com
 */
public interface IActivityProcess {

    /**
     * 执行抽奖的流程
     * @param req 抽奖请求
     * @return 抽奖结果
     */
    DrawProcessResult doDrawProcess(DrawProcessReq req);

    /**
     * 规则量化人群，返回可以参与的活动ID
     * @param req 规则请求
     * @return 量化结果，用户可以参与的ID
     */
    RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req);
}
