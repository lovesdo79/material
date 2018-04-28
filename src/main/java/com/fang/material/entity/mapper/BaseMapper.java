package com.fang.material.entity.mapper;

import com.fang.material.condition.BaseCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by fang on 2017/4/16.
 */
@Mapper
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
