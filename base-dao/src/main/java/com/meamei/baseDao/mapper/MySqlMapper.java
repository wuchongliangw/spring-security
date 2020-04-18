package com.meamei.baseDao.mapper;

/**
 * 通用Mapper接口,MySql独有的通用方法
 *
 * @param <T> 不能为空
 * @author will
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface MySqlMapper<T> extends
        InsertListMapper<T> {

}
