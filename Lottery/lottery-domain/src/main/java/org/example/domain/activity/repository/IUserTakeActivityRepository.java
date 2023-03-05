package org.example.domain.activity.repository;

import org.example.domain.activity.model.vo.DrawOrderVO;
import org.example.domain.activity.model.vo.UserTakeActivityVO;

import java.util.Date;

/**
 * @description: 用户参与活动的仓储接口
 * @author： tankardyegga, 微信号:同名
 * @date: 3/4/2023
 * @Copyright： levinforward@163.com
 */
public interface IUserTakeActivityRepository {

    /**
     *  扣减用户个人参与活动的次数
     * @param activityId
     * @param activityName
     * @param takeCount
     * @param userTakeLeftCount
     * @param uId
     * @param partakeDate
     * @return
     */
    int substractionLeftCount(Long activityId, String activityName, Integer takeCount,
                              Integer userTakeLeftCount, String uId, Date partakeDate);

    /**
     * 领取活动
     * @param activityId
     * @param activityName
     * @param takeCount
     * @param userTakeLeftCount
     * @param uId
     * @param partakeDate
     * @param takeId 领取ID
     */
    void takeActivity(Long activityId, String activityName, Integer takeCount,
                      Integer userTakeLeftCount, String uId, Date partakeDate, Long takeId);


    /**
     * 锁定活动领取记录
     *
     * @param uId           用户ID
     * @param activityId    活动ID
     * @param takeId        领取ID
     * @return 更新结果
     */
    int lockTackActivity(String uId, Long activityId, Long takeId);

    /**
     * 保存抽奖信息
     *
     * @param drawOrderVO 中奖单
     */
    void saveUserStrategyExport(DrawOrderVO drawOrderVO);

    /**
     * 查询是否存在未执行的抽奖领取活动单
     * @param activityId 活动ID
     * @param uId  用户ID
     * @return
     */
    UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);

}
