package com.fang.material.entity.vo;

import com.fang.material.entity.domain.CustomerDomain;
import com.fang.material.entity.domain.OrdersDomain;

import java.util.List;

/**
 * Created by fang on 2017/4/20.
 */
public class OrderVo extends OrdersDomain {
    private CustomerDomain customer;

    private String productNames;

    private List<OrderProductVo> orderProductVos;

    private double totalPrice;

    public CustomerDomain getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDomain customer) {
        this.customer = customer;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public List<OrderProductVo> getOrderProductVos() {
        return orderProductVos;
    }

    public void setOrderProductVos(List<OrderProductVo> orderProductVos) {
        this.orderProductVos = orderProductVos;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
