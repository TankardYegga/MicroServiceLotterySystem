package org.example.mq.consumer;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.common.Constants;
import org.example.domain.activity.model.vo.InvoiceVO;
import org.example.domain.award.model.req.GoodReq;
import org.example.domain.award.model.res.DistributionRes;
import org.example.domain.award.service.factory.DistributionGoodsFactory;
import org.example.domain.award.service.goods.IDistributionGoods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;


/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/8/2023
 * @Copyright： levinforward@163.com
 */
@Component
public class LotteryInvoiceConsumer {

    private Logger logger = LoggerFactory.getLogger(LotteryInvoiceConsumer.class);

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;

    @KafkaListener(topics = "lottery_invoice", groupId = "lottery")
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack,
                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        // 1. 获取MQ消息
        Optional<?> message = Optional.ofNullable(record.value());

        //2 . 判断消息是否存在
        if(!message.isPresent()){
            return;
        }

        //3. 对MQ消息进行处理
        try{

            // 将MQ消息转换对象
            InvoiceVO invoiceVO = JSON.parseObject((String) message.get(), InvoiceVO.class);

            // 获取奖品发送工厂，执行发奖过程
            IDistributionGoods distributionGoodsService = distributionGoodsFactory.getDistributionGoodsService(invoiceVO.getAwardType());
            DistributionRes distributionRes = distributionGoodsService.doDistribution(new GoodReq(invoiceVO.getuId(),
                    invoiceVO.getOrderId(), invoiceVO.getAwardId(),invoiceVO.getAwardName(),invoiceVO.getAwardContent()));

            Assert.isTrue(Constants.AwardState.SUCCESS.getCode().equals(distributionRes.getCode()),distributionRes.getInfo());

            logger.info("消费MQ消息成功！消息的主题是：{}， 内容是：{}，发货的用户对象是：{}", topic, JSON.toJSONString(distributionRes),
                    invoiceVO.getuId());

            ack.acknowledge();

        }catch (Exception e){
            logger.error("消费MQ消息失败，消息的主题是：{}，内容是:{}", topic, message.get());
            throw e;
        }

    }
}
