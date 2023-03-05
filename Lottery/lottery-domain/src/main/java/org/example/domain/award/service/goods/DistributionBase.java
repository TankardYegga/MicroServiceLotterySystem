package org.example.domain.award.service.goods;

import org.example.domain.award.repository.IOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @description: 货物配送的基础通用类
 * @author： tankardyegga, 微信号:同名
 * @date: 2/28/2023
 * @Copyright： levinforward@163.com
 */
public class DistributionBase {

    protected Logger logger = LoggerFactory.getLogger(DistributionBase.class);

    @Resource
    private IOrderRepository awardRepository;

    protected void updateUserAwardState(String uId, String orderId, String awardId, Integer awardState, String awardStateInfo){

    }
}
