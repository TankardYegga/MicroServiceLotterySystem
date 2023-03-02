package org.example.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.example.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;


/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/2/2023
 * @Copyright： levinforward@163.com
 */
@Component
public class SnowFlake implements IIdGenerator {

    private Snowflake snowFlake;

    @Override
    public synchronized long nextId() {
        // 生成workerId
        long workerId;

        System.out.println("local host str:" + NetUtil.getLocalhostStr());
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        }catch (Exception e){
            workerId = NetUtil.getLocalhostStr().hashCode();
        }

        workerId = workerId >> 16 & 31;

        Long dataCenterId = 1L;
        snowFlake = IdUtil.getSnowflake(workerId, dataCenterId);

        return snowFlake.nextId();
    }
}
