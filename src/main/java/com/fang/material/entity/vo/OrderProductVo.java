package com.fang.material.entity.vo;

import com.fang.material.entity.domain.OrderProductDomain;
import com.fang.material.entity.domain.ProductDomain;

/**
 * Created by fang on 2017/4/21.
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
