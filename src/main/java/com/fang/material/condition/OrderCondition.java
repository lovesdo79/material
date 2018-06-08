package com.fang.material.condition;

/**
 * Created by bgfang on 2017/4/21.
 */
public class OrderCondition extends BaseCondition {

    private String name;

    private String product;

    private String address;

    private String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
