package com.fang.material.entity.vo;

import com.fang.material.entity.domain.ProductDomain;

/**
 * Created by fang on 2017/4/20.
 */
public class ProductVo extends ProductDomain {
    private double quantity;

    private double totalPrice;

    private String orderProductId;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(String orderProductId) {
        this.orderProductId = orderProductId;
    }
}
