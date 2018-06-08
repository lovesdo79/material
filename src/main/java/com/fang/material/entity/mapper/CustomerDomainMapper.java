package com.fang.material.entity.mapper;

import com.fang.material.condition.CustomerCondition;
import com.fang.material.entity.domain.CustomerDomain;
import com.fang.material.entity.vo.CustomerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerDomainMapper extends BaseMapper<CustomerDomain> {

    List<CustomerVo> getListByPager(CustomerCondition condition);

    int getListCount(CustomerCondition condition);

    int updateDeleteStatus(@Param("id") String id, @Param("isDelete") boolean isDelete);

    CustomerDomain getCustomerByOrderId(String orderId);

    List<CustomerDomain> getAllList();

}