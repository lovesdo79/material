package com.fang.material.enums;

/**
 * Created by bgfang on 2017/4/20.
 */
public enum ValuationMethod {

    AMOUNT(1, "数量"),
    AREA(2, "面积"),
    LENGTH(3, "长度");

    private int type;
    private String name;

    ValuationMethod(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getValue(int type) {
        for (ValuationMethod c : ValuationMethod.values()) {
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
