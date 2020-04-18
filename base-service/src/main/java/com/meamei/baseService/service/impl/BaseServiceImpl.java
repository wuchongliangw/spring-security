package com.meamei.baseService.service.impl;

import com.meamei.baseDao.mapper.Mapper;
import com.meamei.baseService.service.BaseService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import java.io.Serializable;
import java.util.List;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
    private Mapper<T> baseMapper;

    public void setBaseMapper(Mapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 通过id查找实体
     *
     * @param id 实体id
     * @return 实体
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public T find(ID id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过条件查找实体
     *
     * @param entity 条件
     * @return 实体
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public T find(T entity) {
        return baseMapper.selectOne(entity);
    }

    /**
     * 保存实体，null的属性会保存，不会使用数据库默认值
     *
     * @param entity 实体
     */
    @Override
    public int save(T entity) {
        return baseMapper.insert(entity);
    }

    /**
     * 保存实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity 实体
     */
    @Override
    public int saveIgnoreNull(T entity) {
        return baseMapper.insertSelective(entity);
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity 待更新实体
     */
    @Override
    public int update(T entity) {
        return baseMapper.updateByPrimaryKey(entity);
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity 待更新实体
     */
    @Override
    public int updateIgnoreNull(T entity) {
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 删除实体
     *
     * @param entity 实体
     */
    @Override
    public int delete(T entity) {
        return baseMapper.delete(entity);
    }

    /**
     * 删除实体
     *
     * @param id 实体id
     */
    @Override
    public int delete(ID id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除实体id
     * list
     *
     * @param ids 实体ids
     */
    @Override
    public int delete(ID[] ids) {
        int count = 0;
        if (ids != null) {
            for (ID id : ids) {
                count += baseMapper.deleteByPrimaryKey(id);
            }
        }
        return count;
    }

    /**
     * 查找全部列表（大表慎用）
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<T> findAll() {
        return baseMapper.selectAll();
    }

    /**
     * 根据实体条件返回计数
     *
     * @param query 条件
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public int count(T query) {
        return baseMapper.selectCount(query);
    }

    /**
     * 筛选实体列表
     *
     * @param filter 条件
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<T> findList(T filter) {
        return baseMapper.select(filter);
    }

    /**
     * 自定义条件查找列表
     *
     * @param condition 自定义条件
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<T> findByCondition(Condition condition) {
        return baseMapper.selectByExample(condition);
    }

    /**
     * 实体是否存在
     *
     * @param id 实体ID
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public boolean exist(ID id) {
        return baseMapper.existsWithPrimaryKey(id);
    }

}
