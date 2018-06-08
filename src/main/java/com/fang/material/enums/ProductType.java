package com.fang.material.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.EnumSet;

/**
 * Created by bgfang on 2017/4/20.
 */
public enum ProductType {
    PACKAGE(0, "套餐"),
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

    public static JSONArray getAll() {
        JSONArray array = new JSONArray();
        for (ProductType c : ProductType.values()) {
            JSONObject object = new JSONObject();
            object.put("type", c.getType());
            object.put("name", c.getName());
            array.add(object);
        }

        return array;
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
