package org.example.infrastructure.repository;

import cn.hutool.core.bean.BeanUtil;
import org.example.common.Constants;
import org.example.domain.activity.model.vo.*;
import org.example.domain.activity.repository.IActivityRepository;
import org.example.infrastructure.dao.IActivityDao;
import org.example.infrastructure.dao.IAwardDao;
import org.example.infrastructure.dao.IStrategyDao;
import org.example.infrastructure.dao.IStrategyDetailDao;
import org.example.infrastructure.po.Activity;
import org.example.infrastructure.po.Award;
import org.example.infrastructure.po.Strategy;
import org.example.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/1/2023
 * @Copyright： levinforward@163.com
 */
@Component
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IActivityDao activityDao;
    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Override
    public void addActivity(ActivityVO activityVO) {
        Activity req = new Activity();
        BeanUtil.copyProperties(activityVO, req);
        activityDao.insert(req);
    }

    @Override
    public void addStrategy(StrategyVO strategyVO) {
        Strategy req = new Strategy();
        BeanUtil.copyProperties(strategyVO, req);
        strategyDao.insert(req);
    }

    @Override
    public void addAwardList(List<AwardVO> awardVOList) {
        List<Award> req = new ArrayList<>();
        for(AwardVO awardVO: awardVOList){
            Award award = new Award();
            BeanUtil.copyProperties(awardVO, award);
            req.add(award);
        }
        awardDao.insertList(req);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailVOList) {
        List<StrategyDetail> req = new ArrayList<>();
        for(StrategyDetailVO strategyDetailVO: strategyDetailVOList){
            StrategyDetail strategyDetail = new StrategyDetail();
            BeanUtil.copyProperties(strategyDetailVO, strategyDetail);
            req.add(strategyDetail);
        }
        strategyDetailDao.insertList(req);
    }

    @Override
    public boolean alterActivityState(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        AlterStateVO alterStateVO = new AlterStateVO(activityId, ((Constants.ActivityState) beforeState).getCode(),
                ((Constants.ActivityState) afterState).getCode());
        int count = activityDao.alterActivityState(alterStateVO);
        return 1 == count;
    }
}
