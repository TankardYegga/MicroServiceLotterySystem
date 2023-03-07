package org.example.mq;

import jdk.nashorn.internal.runtime.options.Option;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/7/2023
 * @Copyright： levinforward@163.com
 */
@Component
public class KafkaConsumer {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST, groupId = KafkaProducer.TOPIC_GROUP)
    public void topicTest(ConsumerRecord<?,?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        Optional<?> message = Optional.ofNullable(record.value());
        if(message.isPresent()){
            Object msg = message.get();
            System.out.println("消费[" + topic + "]主题，获得的消息是：" + msg.toString());
            ack.acknowledge();
        }
    }
}
