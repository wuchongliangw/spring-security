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
public interface UpdateByPrimaryKeySelectiveMapper<T> {

    /**
     * 根据主键更新属性不为null的值
     *
     * @param record
     * @return
     */
    @UpdateProvider(type = CustomUpdateProvider.class, method = "dynamicSQL")
    int updateByPrimaryKeySelective(T record);

}
