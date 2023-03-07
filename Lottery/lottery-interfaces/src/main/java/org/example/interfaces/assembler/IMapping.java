package org.example.interfaces.assembler;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Stream;


/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/7/2023
 * @Copyright： levinforward@163.com
 */
@MapperConfig
public interface IMapping<SOURCE, TARGET> {

    /**
     * 隐射同名属性
     * @param var1 源
     * @return 结果
     */
    @Mapping(target = "createTime", dateFormat = "YYYY:MM:dd-HH:mm:ss")
    TARGET sourceToTarget(SOURCE var1);

    /**
     * 隐射同名属性，反向
     * @param var1 源
     * @return 结果
     */
    @InheritInverseConfiguration(name = "targetToSource")
    SOURCE targetToSource(TARGET var1);

    /**
     * 隐射同名属性，集合形式
     * @param var1 源
     * @return 结果
     */
    @InheritConfiguration(name = "sourceToTarget")
    List<TARGET> sourceToTarget(List<SOURCE> var1);

    /**
     * 隐射同名属性，反向，集合形式
     * @param var1 源
     * @return 结果
     */
    @InheritConfiguration(name = "targetToSource")
    List<SOURCE> targetToSource(List<TARGET> var1);

    /**
     * 隐射同名属性，集合流形式
     * @param var1 源
     * @return 结果
     */
    List<TARGET> sourceToTarget(Stream<SOURCE> var1);

    /**
     * 隐射同名属性，集合流形式
     * @param var1 源
     * @return 结果
     */
    List<SOURCE> targetToSource(Stream<TARGET> var1);
}
