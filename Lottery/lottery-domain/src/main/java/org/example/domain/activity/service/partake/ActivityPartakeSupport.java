package org.example.domain.activity.service.partake;

import org.example.domain.activity.model.req.PartakeReq;
import org.example.domain.activity.model.vo.ActivityBillVO;
import org.example.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/4/2023
 * @Copyright： levinforward@163.com
 */
public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req){
        return activityRepository.queryActivityBill(req);
    }
}
