package org.example.domain.award.service.goods;

import org.example.domain.award.model.req.GoodReq;
import org.example.domain.award.model.res.DistributionRes;

/**
 * @description: 将奖品的发送抽象为配送货物的过程
 * @author： tankardyegga, 微信号:同名
 * @date: 2/28/2023
 * @Copyright： levinforward@163.com
 */
public interface IDistributionGoods {

    /**
     *
     * @param req 奖品订单信息
     * @return 配送结果
     */
    DistributionRes doDistribution(GoodReq req);

    Integer getDistributionGoodsName();
}
