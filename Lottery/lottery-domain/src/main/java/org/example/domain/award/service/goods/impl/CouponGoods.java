package org.example.domain.award.service.goods.impl;

import org.example.common.Constants;
import org.example.domain.award.model.req.GoodReq;
import org.example.domain.award.model.res.DistributionRes;
import org.example.domain.award.service.goods.DistributionBase;
import org.example.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * @description: 优惠券奖品配送
 * @author： tankardyegga, 微信号:同名
 * @date: 2/28/2023
 * @Copyright： levinforward@163.com
 */
@Component
public class CouponGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodReq req) {

        logger.info("模拟优惠券发放, uId: {}, awardContent: {}", req.getuId(), req.getAwardContent());

        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(),
                Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(),
                Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.CouponGoods.getCode();
    }
}
