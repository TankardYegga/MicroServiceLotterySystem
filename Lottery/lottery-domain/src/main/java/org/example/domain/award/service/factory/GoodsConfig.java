package org.example.domain.award.service.factory;

import org.example.common.Constants;
import org.example.domain.award.service.goods.IDistributionGoods;
import org.example.domain.award.service.goods.impl.CouponGoods;
import org.example.domain.award.service.goods.impl.DescGoods;
import org.example.domain.award.service.goods.impl.PhysicalGoods;
import org.example.domain.award.service.goods.impl.RedeemCodeGoods;
import org.springframework.beans.factory.annotation.Configurable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 各类发奖奖品的配置类
 * @author： tankardyegga, 微信号:同名
 * @date: 2/28/2023
 * @Copyright： levinforward@163.com
 */
public class GoodsConfig {

    /** 奖品投放策略组 */
    protected static Map<Integer, IDistributionGoods> goodsMap = new ConcurrentHashMap<>();

    @Resource
    private DescGoods descGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @PostConstruct
    public void init(){
        goodsMap.put(Constants.AwardType.DESC.getCode(), descGoods);
        goodsMap.put(Constants.AwardType.CouponGoods.getCode(), couponGoods);
        goodsMap.put(Constants.AwardType.RedeemCodeGoods.getCode(), redeemCodeGoods);
        goodsMap.put(Constants.AwardType.PhysicalGoods.getCode(), physicalGoods);
    }
}
