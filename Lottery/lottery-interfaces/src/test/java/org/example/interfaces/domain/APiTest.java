package org.example.interfaces.domain;

import com.alibaba.fastjson.JSON;
import org.example.common.Constants;
import org.example.domain.award.model.req.GoodReq;
import org.example.domain.award.model.res.DistributionRes;
import org.example.domain.award.service.factory.DistributionGoodsFactory;
import org.example.domain.award.service.goods.IDistributionGoods;
import org.example.domain.strategy.model.req.DrawReq;
import org.example.domain.strategy.model.res.DrawResult;
import org.example.domain.strategy.model.vo.DrawAwardVO;
import org.example.domain.strategy.service.draw.IDrawExec;
import org.example.infrastructure.dao.IActivityDao;
import org.example.infrastructure.po.Activity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APiTest {

    private Logger logger = LoggerFactory.getLogger(APiTest.class);

    @Resource
    IActivityDao activityDao;

    @Resource
    IDrawExec drawExec;

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;

    @Test
    public void test_insert(){
        Activity activity = new Activity();
        activity.setActivityId(100005L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("仅用于插入数据测试");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(500);
        activity.setTakeCount(10);
        activity.setState(0);
        activity.setCreator("zlw");
        activityDao.insert(activity);
    }

    @Test
    public void test_select(){
        Activity activity = activityDao.queryActivityById(100002L);
//        logger.info("测试结果: {}", JSON.toJSONString(activity));
        System.out.println(activity);
    }

    @Test
    public void test_distributionGoodsFactory(){
        //执行抽奖算法，得到抽奖结果
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq("zxf", 10001L));

        Integer drawState = drawResult.getDrawState();
        if(Constants.DrawState.FAIL.getCode().equals(drawState)){
            logger.info("抽奖失败, uId:{}", drawResult.getuId());
            return;
        }

        DrawAwardVO drawAwardVO = drawResult.getDrawAwardInfo();
//        assert drawAwardVO != null;
        if(null == drawAwardVO){
            System.out.println("drawAwardVO:" + drawAwardVO);
            logger.info("获奖信息为空：" + drawAwardVO);
            return;
        }

        if(null == drawAwardVO.getAwardType()){
            System.out.println("drawAwardVO:" + drawAwardVO);
            logger.info("奖品信息为空：" + drawAwardVO);
            return;
        }

        GoodReq goodsReq = new GoodReq(drawResult.getuId(), "2334434354", drawAwardVO.getAwardId(), drawAwardVO.getAwardName(),
                drawAwardVO.getAwardContent());

        System.out.println("awardType is:" + drawAwardVO.getAwardType());

        //根据奖品类型从抽象工厂中获取对应的发奖服务
        IDistributionGoods distributionGoodsService = distributionGoodsFactory.getDistributionServiceGoods(drawAwardVO.getAwardType());
        DistributionRes distributionRes = distributionGoodsService.doDistribution(goodsReq);
        logger.info("测试结果：", JSON.toJSONString(distributionRes));
    }

}
