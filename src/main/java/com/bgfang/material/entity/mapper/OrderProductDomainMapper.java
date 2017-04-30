package com.bgfang.material.entity.mapper;

import com.bgfang.material.entity.domain.OrderProductDomain;

import java.util.List;

/**
 * Created by bgfang on 2017/4/21.
 */
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
