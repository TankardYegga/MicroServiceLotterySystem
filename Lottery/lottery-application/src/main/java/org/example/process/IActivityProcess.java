package org.example.process;

import org.example.process.req.DrawProcessReq;
import org.example.process.res.DrawProcessResult;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/5/2023
 * @Copyright： levinforward@163.com
 */
public interface IActivityProcess {

    /**
     * 执行抽奖的流程
     * @param req 抽奖请求
     * @return 抽奖结果
     */
    DrawProcessResult doDrawProcess(DrawProcessReq req);
}
