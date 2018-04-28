package com.fang.material.enums;

/**
 * Created by bgfang on 2017/4/20.
 */
public enum CustomerType {

    CUSTOMER(1, "客户"),
    WHOLESALER(2, "批发商"),
    SUPPLIER(3, "供应商");

    private int type;
    private String name;

    CustomerType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getValue(int type) {
        for (CustomerType c : CustomerType.values()) {
            if (c.getType() == type) {
                return c.getName();
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
