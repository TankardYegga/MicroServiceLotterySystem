package org.example.domain.activity.service.deploy.impl;

import com.alibaba.fastjson.JSON;
import org.example.domain.activity.model.aggregates.ActivityConfigRich;
import org.example.domain.activity.model.req.ActivityConfigReq;
import org.example.domain.activity.repository.IActivityRepository;
import org.example.domain.activity.service.deploy.IActivityDeploy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;


import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/1/2023
 * @Copyright： levinforward@163.com
 */
@Service
public class ActivityDeployImpl implements IActivityDeploy {

    private Logger logger = LoggerFactory.getLogger(ActivityDeployImpl.class);

    @Resource
    private IActivityRepository activityRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createActivity(ActivityConfigReq activityConfigReq) {
        logger.info("活动配置创建开始, 活动Id: {}", activityConfigReq.getActivityId());
        ActivityConfigRich activityConfigRich = activityConfigReq.getActivityConfigRich();

        try{

            activityRepository.addActivity(activityConfigRich.getActivityVO());

            activityRepository.addStrategy(activityConfigRich.getStrategyVO());

            activityRepository.addAwardList(activityConfigRich.getAwardVOList());

            activityRepository.addStrategyDetailList(activityConfigRich.getStrategyVO().getStrategyDetailVOList());

            logger.info("活动创建配置完成, activityId: {}", activityConfigReq.getActivityId());

        }catch (DuplicateKeyException e){

            logger.error("活动创建失败，唯一索引发生冲突，activityId: {}, reqJSON: {}",
                    activityConfigReq.getActivityId(), JSON.toJSONString(activityConfigReq));
            throw e;
        }

    }

    @Override
    public void updateActivity(ActivityConfigReq activityConfigReq) {

    }
}
