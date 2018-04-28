package com.fang.material.entity.mapper;

import com.fang.material.condition.UserCondition;
import com.fang.material.entity.domain.UserDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by fang on 2017/4/25.
 */
@Mapper
public interface UserDomainMapper extends BaseMapper<UserDomain> {

    UserDomain selectByUserName(String userName);

    List<UserDomain> getListByPager(UserCondition condition);
}
