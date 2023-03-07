package org.example.interfaces.application;

import org.example.common.Constants;
import org.example.domain.activity.model.vo.InvoiceVO;
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

//        while (true){
//            String msg = "你好啊" + new Date();
//            System.out.println("消息是：" + msg);
//            kafkaProducer.send(msg);
//            Thread.sleep(1000);
//        }

        InvoiceVO invoice = new InvoiceVO();
        invoice.setuId("fustack");
        invoice.setOrderId(1444540456057864192L);
        invoice.setAwardId("3");
        invoice.setAwardType(Constants.AwardType.DESC.getCode());
        invoice.setAwardName("Code");
        invoice.setAwardContent("苹果电脑");
        invoice.setShippingAddress(null);
        invoice.setExtInfo(null);

        kafkaProducer.sendLotteryInvoice(invoice);

        while (true){
            Thread.sleep(10000);
        }

    }
}
