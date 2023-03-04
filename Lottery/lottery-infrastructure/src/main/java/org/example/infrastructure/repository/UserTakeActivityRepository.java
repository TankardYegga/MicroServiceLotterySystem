package org.example.infrastructure.repository;

import org.example.domain.activity.repository.IUserTakeActivityRepository;
import org.example.infrastructure.dao.IUserTakeActivityCountDao;
import org.example.infrastructure.dao.IUserTakeActivityDao;
import org.example.infrastructure.po.UserTakeActivity;
import org.example.infrastructure.po.UserTakeActivityCount;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.Date;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/4/2023
 * @Copyright： levinforward@163.com
 */
@Component
public class UserTakeActivityRepository implements IUserTakeActivityRepository {

    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;
    @Resource
    private IUserTakeActivityDao userTakeActivityDao;

    @Override
    public int substractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate) {
        // 并不是特别理解参考代码？
        // 参考代码中的一个思路是：
        // 当参数中的userTakeLeftCount为null，或者说不可知时，就认为当前用户已经领取的活动次数 = 可领取的总活动次数 - 1；
        // 当参数中的userTakeLeftCount不为null时，只提供只有uId和activityId的UserTakeActivityCount对象，
        // 让userTakeActivityCountDao根据提供的对象来更新leftCount（疑问，这到底是怎么来进行更新的？）
        if(null == userTakeLeftCount){
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            userTakeActivityCount.setTotalCount(takeCount);
            userTakeActivityCount.setLeftCount(takeCount - 1);
            userTakeActivityCountDao.insert(userTakeActivityCount);
            return 1;
        }else {
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            return userTakeActivityCountDao.updateLeftCount(userTakeActivityCount);
        }
    }

    @Override
    public void takeActivity(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate, Long takeId) {
        // 插入一条用户参与活动的数据：
        // 这里的代码的条件和结论之间的对应关系也不是特别理解：
        // 如果没有提供已经领取的活动次数，无论当前函数入参的takeCount是多少，都将takeCount设置为1
        // 我个人感觉这是假定：已经领取的活动次数 = takeCount - 1
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setActivityName(activityName);
        userTakeActivity.setTakeId(takeId);
        userTakeActivity.setTakeDate(partakeDate);
        if(null == userTakeLeftCount){
            userTakeActivity.setTakeCount(1);
        }else{
            userTakeActivity.setTakeCount(takeCount - userTakeLeftCount);
        }

        // 给该对象手动设置不可重复键
        String uUId = activityId + "_" + uId + "_" + userTakeActivity.getTakeCount();
        userTakeActivity.setUuId(uUId);

        userTakeActivityDao.insert(userTakeActivity);
    }
}
