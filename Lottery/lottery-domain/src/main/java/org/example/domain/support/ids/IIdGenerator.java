package org.example.domain.support.ids;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/2/2023
 * @Copyright： levinforward@163.com
 */
public interface IIdGenerator {

    /**
     * 执行特定策略的随机ID的生成算法
     * @return 随机ID
     */
    long nextId();
}
