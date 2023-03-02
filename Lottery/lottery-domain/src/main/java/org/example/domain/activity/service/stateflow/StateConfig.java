package org.example.domain.activity.service.stateflow;

import org.example.common.Constants;
import org.example.domain.activity.service.stateflow.event.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/1/2023
 * @Copyright： levinforward@163.com
 */
public class StateConfig {

    protected Map<Enum<Constants.ActivityState>, AbstractState> stateGroup = new ConcurrentHashMap<>();

    @Resource
    private ArraignmentState arraignmentState;

    @Resource
    private PassState passState;

    @Resource
    private OpenState openState;

    @Resource
    private CloseState closeState;

    @Resource
    private RefuseState refuseState;

    @Resource
    private DoingState doingState;

    @Resource
    private EditingState editingState;

    @PostConstruct
    public void init(){
        stateGroup.put(Constants.ActivityState.EDIT, editingState);
        stateGroup.put(Constants.ActivityState.ARRAIGNMENT, arraignmentState);
        stateGroup.put(Constants.ActivityState.PASS, passState);
        stateGroup.put(Constants.ActivityState.REFUSE, refuseState);
        stateGroup.put(Constants.ActivityState.CLOSE, closeState);
        stateGroup.put(Constants.ActivityState.DOING, doingState);
        stateGroup.put(Constants.ActivityState.OPEN, openState);
    }
}
