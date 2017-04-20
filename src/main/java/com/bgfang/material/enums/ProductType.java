package com.bgfang.material.enums;

/**
 * Created by bgfang on 2017/4/20.
 */
public enum ProductType {

    CURTAIN(1, "窗帘"),
    CABINET(2, "橱柜"),
    FITTINGS(3, "配件");

    private int type;
    private String name;

    ProductType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getValue(int type) {
        for (ProductType c : ProductType.values()) {
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
