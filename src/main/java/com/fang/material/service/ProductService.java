package com.fang.material.service;

import com.fang.material.condition.ProductCondition;
import com.fang.material.entity.domain.ProductDomain;
import com.fang.material.entity.mapper.ProductDomainMapper;
import com.fang.material.entity.vo.ProductVo;
import com.fang.material.enums.ProductType;
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

    public List<ProductVo> getListByPager(ProductCondition condition) {
        List<ProductVo> productVos = mapper.getListByPager(condition);
        setDynamicFields(productVos);
        return productVos;
    }


    private void setDynamicFields(List<ProductVo> productVos) {
        for (ProductVo productVo : productVos) {
            //设置产品类型
            String type = ProductType.getValue(productVo.getProductType());
            productVo.setType(type);
        }
    }

    public int getListCount(ProductCondition condition) {
        return mapper.getListCount(condition);
    }
}
