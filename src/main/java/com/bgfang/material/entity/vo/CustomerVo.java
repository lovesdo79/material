package com.bgfang.material.entity.vo;

import com.bgfang.material.entity.domain.CustomerDomain;

/**
 * Created by bgfang on 2017/4/13.
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
