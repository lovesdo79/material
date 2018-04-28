package com.fang.material.entity.mapper;

import com.fang.material.condition.OrderCondition;
import com.fang.material.entity.domain.OrdersDomain;
import com.fang.material.entity.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by fang on 2017/4/21.
 */
@Mapper
public interface OrdersDomainMapper extends BaseMapper<OrdersDomain> {
    List<OrderVo> getListByPager(OrderCondition condition);

    int updateDeleteStatus(@Param("orderId") String id, @Param("isDelete") boolean isDelete);


}
