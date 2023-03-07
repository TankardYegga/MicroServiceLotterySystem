package org.example.mq;

import com.alibaba.fastjson.JSON;
import com.sun.java.swing.plaf.windows.resources.windows;
import org.aspectj.weaver.World;
import org.example.domain.activity.model.vo.InvoiceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.kafka.support.SendResult;


import javax.annotation.Resource;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/7/2023
 * @Copyright： levinforward@163.com
 */
@Component
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /** 指定本生产者的发送主题和对应消费者的接受组 */
//    public static final String TOPIC_TEST = "tankard";
//
//    public static final String TOPIC_GROUP = "test-consumer-group";

//    .\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Kafka-World


//    public void send(Object obj){
//        // 将对象转化成字符串进行发送
//        String json2Obj = JSON.toJSONString(obj);
//        System.out.println("待发送的消息是：" + json2Obj);
//
//        // 进行发送，设置发送后的回调函数
//        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_TEST, json2Obj);
//        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                System.out.println(TOPIC_TEST + "- 生产者消息发送失败: " + throwable.getMessage());
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
//                System.out.println(TOPIC_TEST + "- 生产者消息发送成功：" + stringObjectSendResult.toString());
//            }
//        });
//     }

    private static final String TOPIC_INVOICE = "lottery_invoice";

    /**
     * 发送中奖发货单的MQ消息
     * @param invoiceVO 中奖发货单
     * @return
     */
    public ListenableFuture<SendResult<String, Object>> sendLotteryInvoice(InvoiceVO invoiceVO){
        String obj2Json = JSON.toJSONString(invoiceVO);
        return kafkaTemplate.send(TOPIC_INVOICE, obj2Json);
    }
}
