package com.bgfang.material.service;

import com.bgfang.material.condition.BaseCondition;
import com.bgfang.material.entity.mapper.BaseMapper;
import com.bgfang.material.spring.SpringApplicationContextHolder;
import org.apache.log4j.Logger;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by bgfang on 2017/4/16.
 */
public class BaseService<T, M> {
    protected static final Logger LOGGER = Logger.getLogger(BaseService.class);

    private BaseMapper<T> baseMapper;
    private Class<T> clazz;

    public BaseService() {
        if (clazz == null) {
            clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
        if (baseMapper == null) {
            Object object = SpringApplicationContextHolder.getWebApplicationContext().getBean(clazz);
            baseMapper = (BaseMapper<T>) object;
        }
    }

    public int insert(T record) {
        return baseMapper.insert(record);
    }

    public int insertSelective(T record) {
        return baseMapper.insertSelective(record);
    }

    public T selectByPrimaryKey(String id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }

    public List<T> list() {
        return baseMapper.list();
    }

    public List<T> getList(BaseCondition conditon) {
        return baseMapper.getList(conditon);
    }

    public int getListCount(BaseCondition conditon) {
        return baseMapper.getListCount(conditon);
    }

    public int delete(List<String> ids) {
        return baseMapper.delete(ids);
    }

    public int deleteByPrimaryKey(String id) {
        return baseMapper.deleteByPrimaryKey(id);
    }
}
