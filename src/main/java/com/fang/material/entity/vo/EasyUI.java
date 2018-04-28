package com.fang.material.entity.vo;

import java.util.List;

/**
 * Created by bgfang on 2017/4/14.
 */
public class EasyUI<T> {

    private int total;

    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
