package com.fang.material.condition;

/**
 * Created by bgfang on 2017/4/21.
 */
public class ProductCondition extends BaseCondition {

    private String orderId;

    private String customerId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
