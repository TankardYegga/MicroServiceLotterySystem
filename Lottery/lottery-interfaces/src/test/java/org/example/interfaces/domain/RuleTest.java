package org.example.interfaces.domain;

import com.alibaba.fastjson.JSON;
import org.example.domain.rule.model.req.DecisionMatterReq;
import org.example.domain.rule.model.res.EngineResult;
import org.example.domain.rule.service.engine.EngineFilter;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/6/2023
 * @Copyright： levinforward@163.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleTest {

    private Logger logger = LoggerFactory.getLogger(RuleTest.class);

    @Resource
    private EngineFilter engineFilter;

    @Test
    public void test_process(){
        DecisionMatterReq decisionMatterReq = new DecisionMatterReq();
        decisionMatterReq.setTreeId(2110081902L);
        decisionMatterReq.setuId("fustack");
        decisionMatterReq.setValMap(new HashMap<String, Object>(){{
                put("userGender", "man");
                put("userAge", "25");
        }});

        EngineResult engineResult = engineFilter.process(decisionMatterReq);

        System.out.println("请求参数" + JSON.toJSONString(decisionMatterReq));
        System.out.println("请求结果" + JSON.toJSONString(engineResult));

    }
}
