package com.meamei.baseService.service;

import tk.mybatis.mapper.entity.Condition;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, ID extends Serializable> {
    /**
     * 通过id查找实体
     *
     * @param id 实体id
     * @return 实体
     */
    T find(ID id);

    /**
     * 通过条件查找实体
     *
     * @param entity 条件
     * @return 实体
     */
    T find(T entity);

    /**
     * 保存实体，null的属性会保存，不会使用数据库默认值
     *
     * @param entity 实体
     */
    int save(T entity);

    /**
     * 保存实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity 实体
     */
    int saveIgnoreNull(T entity);

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity 待更新实体
     */
    int update(T entity);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity 待更新实体
     */
    int updateIgnoreNull(T entity);

    /**
     * 删除实体
     *
     * @param entity 实体
     */
    int delete(T entity);

    /**
     * 删除实体
     *
     * @param id 实体id
     */
    int delete(ID id);

    /**
     * 删除实体ids传入数组
     *
     * @param ids 实体ids
     */
    int delete(ID... ids);

    /**
     * 查找全部列表（大表慎用）
     *
     * @return
     */
    List<T> findAll();

    /**
     * 根据实体条件返回计数
     *
     * @param filter 条件
     * @return
     */
    int count(T filter);

    /**
     * 筛选实体列表
     *
     * @param filter 条件
     * @return
     */
    List<T> findList(T filter);

    /**
     * 自定义条件查找列表
     *
     * @param condition 自定义条件
     * @return
     */
    List<T> findByCondition(Condition condition);

    /**
     * 实体是否存在
     *
     * @param id 实体ID
     * @return
     */
    boolean exist(ID id);


}
