package com.fang.material.entity.vo;

import com.fang.material.entity.domain.CustomerDomain;

/**
 * Created by fang on 2017/4/13.
 */
public class CustomerVo extends CustomerDomain {
    private double totalPrice;

    private String customerType;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}
