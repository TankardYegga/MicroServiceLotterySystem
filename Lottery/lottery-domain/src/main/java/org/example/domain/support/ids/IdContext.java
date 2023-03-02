package org.example.domain.support.ids;

import org.example.common.Constants;
import org.example.domain.support.ids.policy.RandomNumeric;
import org.example.domain.support.ids.policy.ShortCode;
import org.example.domain.support.ids.policy.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/2/2023
 * @Copyright： levinforward@163.com
 */
@Configuration
public class IdContext {



    @Bean
    public Map<Constants.Ids, IIdGenerator> idsGenerator(SnowFlake snowFlake, ShortCode shortCode,
                                                          RandomNumeric randomNumeric){
        Map<Constants.Ids, IIdGenerator> idGeneratorMap = new HashMap<>(8);
        idGeneratorMap.put(Constants.Ids.RandomNumeric, randomNumeric);
        idGeneratorMap.put(Constants.Ids.Snowflake, snowFlake);
        idGeneratorMap.put(Constants.Ids.ShortCode, shortCode);
        return idGeneratorMap;
    }

}
