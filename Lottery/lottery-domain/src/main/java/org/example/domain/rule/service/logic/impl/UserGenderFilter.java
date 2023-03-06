package org.example.domain.rule.service.logic.impl;

import org.example.domain.rule.model.req.DecisionMatterReq;
import org.example.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/5/2023
 * @Copyright： levinforward@163.com
 */
@Component
public class UserGenderFilter extends BaseLogic {

    @Override
    public String matterValue(DecisionMatterReq decisionMatter) {
        return decisionMatter.getValMap().get("userGender").toString();
    }
}
