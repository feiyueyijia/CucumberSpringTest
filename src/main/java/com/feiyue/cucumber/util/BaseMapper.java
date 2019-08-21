package com.feiyue.cucumber.util;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * 微服务通用Mapper
 * Author jisongZhou
 * Date  2019-04-09
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T> {

    List<T> findSimpleListByAndCondition(T entity);

    List<T> findListByAndCondition(T entity);

    List<T> findSimpleListByORCondition(T entity);

    List<T> findListByORCondition(T entity);

}
