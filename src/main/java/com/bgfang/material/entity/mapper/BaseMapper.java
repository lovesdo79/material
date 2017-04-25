package com.bgfang.material.entity.mapper;

import com.bgfang.material.condition.BaseCondition;

import java.util.List;

/**
 * Created by bgfang on 2017/4/16.
 */
public interface BaseMapper<T> {
    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> list();

    List<T> getList(BaseCondition conditon);

    int getListCount(BaseCondition conditon);

    int deleteByPrimaryKey(String id);

    int delete(List<String> ids);

    int virtualDelete(String id);
}
