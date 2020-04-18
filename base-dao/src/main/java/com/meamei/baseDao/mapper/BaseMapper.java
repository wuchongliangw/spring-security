package com.meamei.baseDao.mapper;

import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;

/**
 * 通用Mapper接口,其他接口继承该接口即可
 *
 * @param <T> 不能为空
 * @author will
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface BaseMapper<T> extends
        BaseSelectMapper<T>,
        BaseInsertMapper<T>,
        BaseUpdateMapper<T>,
        BaseDeleteMapper<T> {

}
