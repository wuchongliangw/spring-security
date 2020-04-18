package com.meamei.baseDao.mapper;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 通用Mapper接口,基础查询
 *
 * @param <T> 不能为空
 * @author will
 */
@RegisterMapper
public interface BaseInsertMapper<T> extends
        InsertMapper<T>,
        InsertSelectiveMapper<T> {

}
