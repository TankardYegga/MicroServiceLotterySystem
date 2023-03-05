package org.example.domain.activity.service.partake;

import org.example.common.Result;
import org.example.domain.activity.model.req.PartakeReq;
import org.example.domain.activity.model.res.PartakeResult;
import org.example.domain.activity.model.vo.DrawOrderVO;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/1/2023
 * @Copyright： levinforward@163.com
 */
public interface IActivityPartake {

    /**
     * 活动参与接口
     */
    PartakeResult doPartake(PartakeReq req);

    /**
     * 保存奖品单
     * @param drawOrderVO 奖品单
     * @return: 保存奖品单的结果
     */
    Result recordDrawOrder(DrawOrderVO drawOrderVO);
}
