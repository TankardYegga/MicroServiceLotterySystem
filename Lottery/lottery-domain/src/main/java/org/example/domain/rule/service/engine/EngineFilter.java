package org.example.domain.rule.service.engine;

import org.example.domain.rule.model.req.DecisionMatterReq;
import org.example.domain.rule.model.res.EngineResult;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/5/2023
 * @Copyright： levinforward@163.com
 */
public interface EngineFilter {

    /**
     * 执行规则引擎过滤功能的接口
     * @param matter
     * @return
     */
    EngineResult process(final DecisionMatterReq matter);
}
