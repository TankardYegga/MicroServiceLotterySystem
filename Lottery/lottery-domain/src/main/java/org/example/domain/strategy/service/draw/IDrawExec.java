package org.example.domain.strategy.service.draw;

import org.example.domain.strategy.model.req.DrawReq;
import org.example.domain.strategy.model.res.DrawResult;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 2/27/2023
 * @Copyright： levinforward@163.com
 */
public interface IDrawExec {

    /**
     * description:
     * @param req 策略请求
     * @return com.example.domain.strategy.model.res.DrawResult
     */
    DrawResult doDrawExec(DrawReq req);
}
