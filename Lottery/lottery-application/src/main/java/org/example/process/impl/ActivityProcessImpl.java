package org.example.process.impl;

import org.example.common.Constants;
import org.example.domain.activity.model.req.PartakeReq;
import org.example.domain.activity.model.res.PartakeResult;
import org.example.domain.activity.model.vo.DrawOrderVO;
import org.example.domain.activity.service.partake.IActivityPartake;
import org.example.domain.rule.model.req.DecisionMatterReq;
import org.example.domain.rule.model.res.EngineResult;
import org.example.domain.rule.service.engine.EngineFilter;
import org.example.domain.strategy.model.req.DrawReq;
import org.example.domain.strategy.model.res.DrawResult;
import org.example.domain.strategy.model.vo.DrawAwardVO;
import org.example.domain.strategy.service.draw.IDrawExec;
import org.example.domain.support.ids.IIdGenerator;
import org.example.process.IActivityProcess;
import org.example.process.req.DrawProcessReq;
import org.example.process.res.DrawProcessResult;
import org.example.process.res.RuleQuantificationCrowdResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/5/2023
 * @Copyright： levinforward@163.com
 */
@Service
public class ActivityProcessImpl implements IActivityProcess {

    @Resource
    private IDrawExec drawExec;

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Resource(name = "ruleEngineHandler")
    private EngineFilter engineFilter;

    @Override
    public DrawProcessResult doDrawProcess(DrawProcessReq req) {
        // 1. 用户领取活动：检查活动的状态、日期、库存、个人可用次数等来判断用户是否可以继续参加该活动
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeReq(req.getuId(), req.getActivityId()));
        // 如果已经不能参加该活动了，那么抽奖活动不会开始，直接返回领取活动的结果
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(partakeResult.getCode())){
            // 因为抽奖都没有执行，所以没有抽奖奖品信息这一成员变量
            return new DrawProcessResult(partakeResult.getCode(), partakeResult.getInfo());
        }

        // 2. 用户执行抽奖流程
        // 当用户成功领取活动之后，就可以执行抽奖过程
        // 【1】首先我们需要构造抽奖请求信息
        // 抽奖请求信息包括：用户ID、抽奖策略ID、防重ID
        // 这里我们从领取活动的结果中可以获取抽奖策略ID，因为一个活动必然对应一个策略
        // 同样我们可以从领取活动的结果中获得流水号，将这个流水号来作为防重ID
        Long strategyId = partakeResult.getStrategyId();
        Long takeId = partakeResult.getTakeId();
        DrawReq drawReq = new DrawReq(req.getuId(), strategyId, String.valueOf(takeId));
        DrawResult drawResult = drawExec.doDrawExec(drawReq);
        // 【2】依据不同的抽奖结果继续做不同的后续处理
        // 如果没有抽中，则直接返回未抽中的抽奖过程结果
        if(Constants.DrawState.FAIL.getCode().equals(drawResult.getDrawState())){
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());
        }
        // 如果抽奖抽中了，则可以从抽奖结果中分离出抽中的奖品信息
        DrawAwardVO drawAwardVO = drawResult.getDrawAwardInfo();

        // 3. 抽奖结果存入数据库（落库）
        // 需要构造一个奖品单对象，然后将该奖品单对象存入存入数据库
        activityPartake.recordDrawOrder(buildDrawOrderVO(req, strategyId, takeId, drawAwardVO));

        // 4. 发送MQ消息，启动发奖流程

        // 5. 返回结果
        return new DrawProcessResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

    @Override
    public RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req) {
        //1. 执行量化引擎
        EngineResult engineResult = engineFilter.process(req);

        if(!engineResult.isSuccess()){
            return new RuleQuantificationCrowdResult(Constants.ResponseCode.RULE_ERR.getCode(),
                    Constants.ResponseCode.RULE_ERR.getInfo());
        }

        //2. 封装结果
        RuleQuantificationCrowdResult ruleQuantificationCrowdResult = new RuleQuantificationCrowdResult(Constants.ResponseCode.SUCCESS.getCode(),
                Constants.ResponseCode.SUCCESS.getInfo());
        ruleQuantificationCrowdResult.setActivityId(Long.valueOf(engineResult.getNodeValue()));

        return ruleQuantificationCrowdResult;
    }

    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardVO drawAwardVO){
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setuId(req.getuId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setGrantDate(drawAwardVO.getAwardDate());
        drawOrderVO.setGrantType(drawAwardVO.getAwardType());
        drawOrderVO.setGrantState(Constants.GrantState.INIT.getCode());
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawOrderVO.getStrategyMode());
        drawOrderVO.setOrderId(drawOrderVO.getOrderId());
        drawOrderVO.setAwardId(drawAwardVO.getAwardId());
        drawOrderVO.setAwardName(drawAwardVO.getAwardName());
        drawOrderVO.setAwardType(drawAwardVO.getAwardType());
        drawOrderVO.setAwardContent(drawAwardVO.getAwardContent());
        return drawOrderVO;
    }
}
