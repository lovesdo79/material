package com.bgfang.material.entity.mapper;

import com.bgfang.material.condition.CustomerCondition;
import com.bgfang.material.condition.UserCondition;
import com.bgfang.material.entity.domain.UserDomain;
import com.bgfang.material.entity.vo.CustomerVo;

import java.util.List;

/**
 * Created by bgfang on 2017/4/25.
 */
public interface UserDomainMapper extends BaseMapper<UserDomain> {

    UserDomain selectByUserName(String userName);

    List<UserDomain> getListByPager(UserCondition condition);
}
