package com.example.interfaces;

import com.example.common.Constants;
import com.example.common.Result;
import com.example.infrastructure.dao.IActivityDao;
import com.example.infrastructure.po.Activity;
import com.example.rpc.IActivityBooth;
import com.example.rpc.dto.ActivityDto;
import com.example.rpc.req.ActivityReq;
import com.example.rpc.res.ActivityRes;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * Description: lottery-5215-tankard
 * Creator: levin
 * Date: 2/25/2023
 * Time: 9:30 PM
 * Email: levinforward@163.com
 */
//对rpc中定义的IActivityBooth接口进行实现
//实现依据封装了活动id的ID类来获得该活动的结果
// 利用基础层里的Dao层来进行数据的获取
@Service
public class ActivityBooth implements IActivityBooth {

    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {

        //首先从ID类来获取活动id
        Long activityId = req.getActivityId();

        //依据活动id来获取活动对象
        Activity activity = activityDao.queryActivityById(activityId);

        ActivityDto  activityDto = new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setState(activity.getState());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(),
                Constants.ResponseCode.SUCCESS.getInfo()), activityDto);
    }
}
