package org.example.infrastructure.repository;

import org.example.common.Constants;
import org.example.domain.activity.model.vo.DrawOrderVO;
import org.example.domain.activity.model.vo.UserTakeActivityVO;
import org.example.domain.activity.repository.IUserTakeActivityRepository;
import org.example.infrastructure.dao.IUserStrategyExportDao;
import org.example.infrastructure.dao.IUserTakeActivityCountDao;
import org.example.infrastructure.dao.IUserTakeActivityDao;
import org.example.infrastructure.po.UserStrategyExport;
import org.example.infrastructure.po.UserTakeActivity;
import org.example.infrastructure.po.UserTakeActivityCount;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.invoke.ConstantCallSite;
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
    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

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
            userTakeActivity.setTakeCount(takeCount - userTakeLeftCount + 1);
        }

        userTakeActivity.setState(Constants.TakeState.NO_USED.getCode());

        // 给该对象手动设置不可重复键
        String uUId = activityId + "_" + uId + "_" + userTakeActivity.getTakeCount();
        userTakeActivity.setUuId(uUId);

        userTakeActivityDao.insert(userTakeActivity);
    }

    @Override
    public int lockTackActivity(String uId, Long activityId, Long takeId) {
        // 创建一个 用户领取活动 的对象
        // 然后由对应的Dao来锁定活动中奖单
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setTakeId(takeId);
        userTakeActivity.setUuId(uId);
        userTakeActivity.setActivityId(activityId);
        return userTakeActivityDao.lockTackActivity(userTakeActivity);
    }

    @Override
    public void saveUserStrategyExport(DrawOrderVO drawOrderVO) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setuId(drawOrderVO.getuId());
        userStrategyExport.setActivityId(drawOrderVO.getActivityId());
        userStrategyExport.setOrderId(drawOrderVO.getOrderId());
        userStrategyExport.setStrategyId(drawOrderVO.getStrategyId());
        userStrategyExport.setStrategyMode(drawOrderVO.getStrategyMode());
        userStrategyExport.setGrantType(drawOrderVO.getGrantType());
        userStrategyExport.setGrantDate(drawOrderVO.getGrantDate());
        userStrategyExport.setGrantState(drawOrderVO.getGrantState());
        userStrategyExport.setAwardId(drawOrderVO.getAwardId());
        userStrategyExport.setAwardType(drawOrderVO.getAwardType());
        userStrategyExport.setAwardName(drawOrderVO.getAwardName());
        userStrategyExport.setAwardContent(drawOrderVO.getAwardContent());
        userStrategyExport.setUuId(String.valueOf(drawOrderVO.getOrderId()));

        userStrategyExportDao.insert(userStrategyExport);

    }

    @Override
    public UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setActivityId(activityId);
        UserTakeActivity noConsumedUserTakeActivityOrder = userTakeActivityDao.queryNoConsumedTakeActivityOrder(userTakeActivity);

        //如何没有查到领取单，直接返回null
        if(null == noConsumedUserTakeActivityOrder){
            return null;
        }

        UserTakeActivityVO userTakeActivityVO = new UserTakeActivityVO();
        userTakeActivityVO.setActivityId(noConsumedUserTakeActivityOrder.getActivityId());
        userTakeActivityVO.setTakeId(noConsumedUserTakeActivityOrder.getTakeId());
        userTakeActivityVO.setStrategyId(noConsumedUserTakeActivityOrder.getStrategyId());
        userTakeActivityVO.setState(noConsumedUserTakeActivityOrder.getState());

        return userTakeActivityVO;
    }
}
