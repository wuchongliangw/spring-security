package com.meamei.baseDao.mapper;

import com.meamei.baseDao.provider.CustomUpdateProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 通用Mapper接口,更新
 *
 * @param <T> 不能为空
 * @author will
 */
@RegisterMapper
public interface UpdateByPrimaryKeyMapper<T> {

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param record
     * @return
     */
    @UpdateProvider(type = CustomUpdateProvider.class, method = "dynamicSQL")
    int updateByPrimaryKey(T record);

}
