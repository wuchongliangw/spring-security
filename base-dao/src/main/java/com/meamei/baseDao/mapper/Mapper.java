package com.meamei.baseDao.mapper;

import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.RowBoundsMapper;

/**
 * 通用Mapper接口,其他接口继承该接口即可
 *
 * @param <T> 不能为空
 * @author will
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface Mapper<T> extends
        BaseMapper<T>,
        ExampleMapper<T>,
        RowBoundsMapper<T>,
        MySqlMapper<T>, // 使mapper支持MySQL特有的批量插入和返回自增字段
        Marker {

}
