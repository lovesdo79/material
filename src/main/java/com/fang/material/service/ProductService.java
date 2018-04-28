package com.fang.material.service;

import com.fang.material.entity.domain.ProductDomain;
import com.fang.material.entity.mapper.ProductDomainMapper;
import com.fang.material.entity.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fang on 2017/4/28.
 */
@Service
public class ProductService {

    @Autowired
    private ProductDomainMapper mapper;

    public int insertBatch(List<ProductVo> list) {
        return mapper.insertBatch(list);
    }

    public ProductDomain selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<ProductDomain> selectListByOrderId(String orderId) {
        return mapper.selectListByOrderId(orderId);
    }
}
