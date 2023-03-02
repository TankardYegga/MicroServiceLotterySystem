package org.example.interfaces.domain;

import org.example.common.Constants;
import org.example.domain.support.ids.IIdGenerator;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/2/2023
 * @Copyright： levinforward@163.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SupportTest {

    private Logger logger = LoggerFactory.getLogger(SupportTest.class);

    @Resource
    private Map<Constants.Ids, IIdGenerator> idsGeneratorMap;

    @Test
    public void test_idsGenerator(){
        Long snowFlakeId = idsGeneratorMap.get(Constants.Ids.Snowflake).nextId();
        Long shortCodeId = idsGeneratorMap.get(Constants.Ids.ShortCode).nextId();
        Long randomNumericId = idsGeneratorMap.get(Constants.Ids.RandomNumeric).nextId();
        System.out.println("雪花算法生成的ID：" + snowFlakeId);
        System.out.println("日期算法生成的ID:" + shortCodeId);
        System.out.println("随机数算法生成的ID：" + randomNumericId);
    }
}
