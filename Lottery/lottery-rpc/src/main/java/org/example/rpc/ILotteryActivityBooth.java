package org.example.rpc;

import org.example.rpc.req.DrawReq;
import org.example.rpc.req.QuantificationDrawReq;
import org.example.rpc.res.DrawRes;

/**
 * @description: 抽奖活动展台接口
 * @author： tankardyegga, 微信号:同名
 * @date: 3/7/2023
 * @Copyright： levinforward@163.com
 */
public interface ILotteryActivityBooth {

    /**
     * 执行抽奖
     * @param req 抽奖请求
     * @return 抽奖结果
     */
    DrawRes doDraw(DrawReq req);

    /**
     * 执行量化人群抽奖
     * @param quantificationDrawReq 量化人群抽奖请求
     * @return 请求结果
     */
    DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq);
}
