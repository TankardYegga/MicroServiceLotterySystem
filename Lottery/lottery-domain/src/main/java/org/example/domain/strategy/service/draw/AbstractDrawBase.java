package org.example.domain.strategy.service.draw;

import org.example.common.Constants;
import org.example.domain.strategy.model.aggregates.StrategyRich;
import org.example.domain.strategy.model.req.DrawReq;
import org.example.domain.strategy.model.res.DrawResult;
import org.example.domain.strategy.model.vo.AwardRateInfo;
import org.example.domain.strategy.model.vo.DrawAwardInfo;
import org.example.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.example.infrastructure.po.Award;
import org.example.infrastructure.po.Strategy;
import org.example.infrastructure.po.StrategyDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 2/27/2023
 * @Copyright： levinforward@163.com
 */
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec{

    private Logger logger = LoggerFactory.getLogger(AbstractDrawBase.class);

    @Override
    public DrawResult doDrawExec(DrawReq req){
        // 1. 获取抽奖策略
        Long strategtId = req.getStrategyId();
        StrategyRich strategyRich = super.queryStrategyRich(strategtId);
        Strategy strategy = strategyRich.getStrategy();

        // 2. 判断抽奖策略是否已经初始化到内存
        checkAndInitRateData(strategtId, strategy.getStrategyMode(),strategyRich.getStrategyDetailList());

        // 3. 找出哪些奖品已经是被排除的类
        List<String> excludeAwardIds = this.queryExcludeAwardIds(strategtId);

        // 4. 执行抽奖算法
        String awardId = this.drawAlgorithm(strategtId, drawAlgorithmGroup.get(strategy.getStrategyMode()),
                excludeAwardIds);

        // 5. 包装抽奖结果
        return buildDrawResult(req.getuId(), strategtId, awardId);
    }

    /**
     * description:
     * @param strategyId java.lang.Long 策略ID
     * @param strategyMode java.lang.Integer 策略模式
     * @param strategyDetailList java.util.List<com.example.infrastructure.po.StrategyDetail> 策略细节列表
     * @return void
     */
    private void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList){

        //如果是非单项概率，也就是说是总体概率
        //那么因为其概率总是变化的，所以没有必要缓存
        if(!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)){
            return;
        }

        IDrawAlgorithm drawAlgorithm = drawAlgorithmGroup.get(strategyMode);

        // 检查该策略对应的概率元素是否已经在内存中
        boolean isExistRateTuple = drawAlgorithm.isExistRateTuple(strategyId);

        // 已经存在，则无需再初始化
        if(isExistRateTuple){
            return;
        }

        // 不存在，进行初始化
        // 要获取该策略所对应的奖品概率信息列表
        // 可以从这里的该策略的策略详细清单来获取
        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for(StrategyDetail strategyDetail: strategyDetailList){
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }

        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);
    }

    /**
     * description: 不在抽奖范围内的场景列表，原因可能是：风控策略、临时调整、奖品库存为空
     * @param strategyId java.lang.Long 策略ID
     * @return java.util.List<java.lang.String>
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    /**
     * description:
     * @param strategyId java.lang.Long
     * @param drawAlgorithm com.example.domain.strategy.service.algorithm.IDrawAlgorithm
     * @param excludeAwardIds  并发情况下，库存临界值从1-》0，会有用户抽奖结果为null
     * @return 中奖结果
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm,
                                            List<String> excludeAwardIds);


    /**
     * description: 包装抽奖结果，打印获奖信息，返回抽奖结果对象
     * @param uId java.lang.String
     * @param  strategyId java.lang.Long
     * @param  awardId java.lang.String
     * @return com.example.domain.strategy.model.res.DrawResult
     */
    private DrawResult buildDrawResult(String uId, Long strategyId, String awardId){
        if(null == awardId){
            logger.info("用户抽奖策略执行完成：[未中奖]，用户id：{}, 策略id: {}", uId, strategyId);
            return new DrawResult(uId, strategyId, Constants.DrawState.FAIL.getCode());
        }

        Award award = super.queryAwardInfoById(awardId);
        DrawAwardInfo drawAwardInfo = new DrawAwardInfo(awardId, award.getAwardName());


        logger.info("用户抽奖策略完成：[已中奖！], 用户id:{}, 策略id: {}, 奖品id: {}", uId, strategyId, awardId);

        return new DrawResult(uId, strategyId, Constants.DrawState.SUCCESS.getCode(),
                drawAwardInfo);
    }

}
