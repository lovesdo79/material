package com.fang.material.service;

import com.fang.material.entity.domain.OrderProductDomain;
import com.fang.material.entity.mapper.OrderProductDomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fang on 2017/4/28.
 */
@Service
public class OrderProductService {
    @Autowired
    private OrderProductDomainMapper mapper;

    public int insertBatch(List<OrderProductDomain> list) {
        return mapper.insertBatch(list);
    }

    public List<OrderProductDomain> selectListByOrderId(String orderId){
        return mapper.selectListByOrderId(orderId);
    }
}
