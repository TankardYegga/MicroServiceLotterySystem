package org.example.domain.award.service.factory;

import org.example.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/1/2023
 * @Copyright： levinforward@163.com
 */
@Service
public class DistributionGoodsFactory extends GoodsConfig{

    public IDistributionGoods getDistributionServiceGoods(Integer awardType){
        System.out.println("goodsMap:" + goodsMap);
        return goodsMap.get(awardType);
    }
}
