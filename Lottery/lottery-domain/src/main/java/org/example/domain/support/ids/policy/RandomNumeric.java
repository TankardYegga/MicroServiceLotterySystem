package org.example.domain.support.ids.policy;

import org.example.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.RandomStringUtils;



/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/2/2023
 * @Copyright： levinforward@163.com
 */
@Component
public class RandomNumeric implements IIdGenerator {

    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
