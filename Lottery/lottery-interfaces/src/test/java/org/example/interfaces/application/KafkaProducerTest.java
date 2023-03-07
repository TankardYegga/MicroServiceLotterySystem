package org.example.interfaces.application;

import org.example.mq.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/7/2023
 * @Copyright： levinforward@163.com
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class KafkaProducerTest {

    @Resource
    private KafkaProducer kafkaProducer;

    @Test
    public void testKafkaProducer() throws InterruptedException {

        while (true){
            String msg = "你好啊" + new Date();
            System.out.println("消息是：" + msg);
            kafkaProducer.send(msg);
            Thread.sleep(1000);
        }
    }
}
