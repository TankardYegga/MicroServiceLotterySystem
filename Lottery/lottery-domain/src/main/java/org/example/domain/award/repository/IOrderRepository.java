package org.example.domain.award.repository;

/**
 * @description: 商品的仓储服务接口
 * @author： tankardyegga, 微信号:同名
 * @date: 2/28/2023
 * @Copyright： levinforward@163.com
 */
public interface IOrderRepository {

    void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState);

}
