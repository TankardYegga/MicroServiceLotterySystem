package org.example.interfaces;

import com.alibaba.fastjson.JSON;
import org.example.rpc.ILotteryActivityBooth;
import org.example.rpc.req.DrawReq;
import org.example.rpc.req.QuantificationDrawReq;
import org.example.rpc.res.DrawRes;
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
 * @date: 3/7/2023
 * @Copyright： levinforward@163.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryActivityBoothTest {

    private Logger logger = LoggerFactory.getLogger(LotteryActivityBoothTest.class);

    @Resource
    private ILotteryActivityBooth lotteryActivityBooth;

    @Test
    public void test_doDraw(){
        DrawReq drawReq = new DrawReq();
        drawReq.setuId("xiaofuge");
        drawReq.setActivityId(100001L);
        DrawRes drawRes = lotteryActivityBooth.doDraw(drawReq);
        System.out.println("请求参数：" + JSON.toJSONString(drawReq));
        System.out.println("请求结果：" + JSON.toJSONString(drawRes));
    }

    @Test
    public void test_doQuantificationDraw(){
        QuantificationDrawReq quantificationDrawReq = new QuantificationDrawReq();
        quantificationDrawReq.setuId("xiaofuge");
        quantificationDrawReq.setTreeId(2110081902L);
        quantificationDrawReq.setValMap(new HashMap<String, Object>(){{
            put("userGender", "man");
            put("userAge", "18");
        }});

        DrawRes drawRes = lotteryActivityBooth.doQuantificationDraw(quantificationDrawReq);
        System.out.println("请求参数：" + JSON.toJSONString(quantificationDrawReq));
        System.out.println("请求参数：" + JSON.toJSONString(drawRes));
    }
}
