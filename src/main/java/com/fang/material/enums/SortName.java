package com.fang.material.enums;

/**
 * Created by bgfang on 2017/4/15.
 */
public enum SortName {

    UPDATETIME("updateTime", "UPDATE_TIME"), CREATETIME("createTime", "CREATE_TIME");

    private String name;
    private String value;

    SortName(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static String getValue(String name) {
        for (SortName c : SortName.values()) {
            if (c.getName().equals(name)) {
                return c.getValue();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
