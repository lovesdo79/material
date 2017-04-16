package com.bgfang.material.entity.mapper;

import com.bgfang.material.condition.CustomerCondition;
import com.bgfang.material.entity.domain.CustomerDomain;
import com.bgfang.material.entity.vo.CustomerVo;

import java.util.List;

public interface CustomerDomainMapper extends BaseMapper<CustomerDomain>{

    List<CustomerVo> getListByPager(CustomerCondition condition);

    int getListCount(CustomerCondition condition);
}