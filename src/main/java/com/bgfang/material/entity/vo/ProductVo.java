package com.bgfang.material.entity.vo;

import com.bgfang.material.entity.domain.ProductDomain;

/**
 * Created by bgfang on 2017/4/20.
 */
public class ProductVo extends ProductDomain {
    private int quantity;

    private int valuationMethod;

    private double totalPrice;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getValuationMethod() {
        return valuationMethod;
    }

    public void setValuationMethod(int valuationMethod) {
        this.valuationMethod = valuationMethod;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
