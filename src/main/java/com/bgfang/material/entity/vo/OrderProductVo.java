package com.bgfang.material.entity.vo;

import com.bgfang.material.entity.domain.OrderProductDomain;
import com.bgfang.material.entity.domain.ProductDomain;

import java.util.List;

/**
 * Created by bgfang on 2017/4/21.
 */
public class OrderProductVo extends OrderProductDomain {
    private ProductDomain product;

    public ProductDomain getProduct() {
        return product;
    }

    public void setProduct(ProductDomain product) {
        this.product = product;
    }
}
