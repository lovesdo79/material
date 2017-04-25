package com.bgfang.material.entity.mapper;

import com.bgfang.material.condition.CustomerCondition;
import com.bgfang.material.condition.OrderCondition;
import com.bgfang.material.entity.domain.OrdersDomain;
import com.bgfang.material.entity.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by bgfang on 2017/4/21.
 */
public interface OrdersDomainMapper extends BaseMapper<OrdersDomain> {
    List<OrderVo> getListByPager(OrderCondition condition);

    int updateDeleteStatus(@Param("orderId") String id, @Param("isDelete") boolean isDelete);


}
