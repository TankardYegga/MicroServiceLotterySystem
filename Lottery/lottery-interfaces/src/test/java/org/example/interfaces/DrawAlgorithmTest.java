package org.example.interfaces;

import org.example.domain.strategy.model.vo.AwardRateVO;
import org.example.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 2/27/2023
 * @Copyright： levinforward@163.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawAlgorithmTest {

    @Resource(name = "singleRateRandomDrawAlgorithm")
    private IDrawAlgorithm randomDrawAlgorithm;

//    @Resource
//    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    // 在每个测试方法执行前都会执行一次init函数
    @Before
    public void init(){
        //奖品信息
        List<AwardRateVO> strategyList = new ArrayList<>();
        strategyList.add(new AwardRateVO("一等奖：IMac", new BigDecimal("0.05")));
        strategyList.add(new AwardRateVO("二等奖：iphone", new BigDecimal("0.15")));
        strategyList.add(new AwardRateVO("三等奖：ipad", new BigDecimal("0.20")));
        strategyList.add(new AwardRateVO("四等奖: AirPods", new BigDecimal("0.25")));
        strategyList.add(new AwardRateVO("五等奖：充电宝", new BigDecimal("0.35")));

        //初始数据
        randomDrawAlgorithm.initRateTuple(10001L, strategyMode, strategyList);
    }

    @Test
    public void test_randomDrawAlgorithm(){
        List<String> excludeAwardIds = new ArrayList<>();
        excludeAwardIds.add("二等奖：iphone");
        excludeAwardIds.add("四等奖: AirPods");

        for(int i = 0; i < 20; i++){
            System.out.println("中奖结果：" + randomDrawAlgorithm.randomDraw(10001L, excludeAwardIds));
        }
    }
}
