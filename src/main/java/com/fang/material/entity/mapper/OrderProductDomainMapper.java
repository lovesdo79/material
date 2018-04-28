package com.fang.material.entity.mapper;

import com.fang.material.entity.domain.OrderProductDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by fang on 2017/4/21.
 */
@Mapper
public interface OrderProductDomainMapper extends BaseMapper<OrderProductDomain> {
    /**
     * 根据订单ID获取所有商品列表
     *
     * @param orderId
     * @return
     */
    List<OrderProductDomain> selectListByOrderId(String orderId);

    int insertBatch(List<OrderProductDomain> list);
}
