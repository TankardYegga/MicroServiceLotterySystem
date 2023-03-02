package org.example.domain.activity.model.aggregates;

import org.example.domain.activity.model.vo.ActivityVO;
import org.example.domain.activity.model.vo.AwardVO;
import org.example.domain.activity.model.vo.StrategyVO;

import java.util.List;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/1/2023
 * @Copyright： levinforward@163.com
 */
public class ActivityConfigRich {

    private ActivityVO activityVO;

    private StrategyVO strategyVO;

    private List<AwardVO> awardVOList;

    public ActivityConfigRich() {
    }

    public ActivityConfigRich(ActivityVO activityVO, StrategyVO strategyVO, List<AwardVO> awardVOList) {
        this.activityVO = activityVO;
        this.strategyVO = strategyVO;
        this.awardVOList = awardVOList;
    }

    public ActivityVO getActivityVO() {
        return activityVO;
    }

    public void setActivityVO(ActivityVO activityVO) {
        this.activityVO = activityVO;
    }

    public StrategyVO getStrategyVO() {
        return strategyVO;
    }

    public void setStrategyVO(StrategyVO strategyVO) {
        this.strategyVO = strategyVO;
    }

    public List<AwardVO> getAwardVOList() {
        return awardVOList;
    }

    public void setAwardVOList(List<AwardVO> awardVOList) {
        this.awardVOList = awardVOList;
    }
}
