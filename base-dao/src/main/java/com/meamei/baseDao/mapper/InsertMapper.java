package com.meamei.baseDao.mapper;

import com.meamei.baseDao.provider.CustomInsertProvider;
import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 通用Mapper接口,插入
 *
 * @param <T> 不能为空
 * @author will
 */
@RegisterMapper
public interface InsertMapper<T> {

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param record
     * @return
     */
    @InsertProvider(type = CustomInsertProvider.class, method = "dynamicSQL")
    int insert(T record);

}
