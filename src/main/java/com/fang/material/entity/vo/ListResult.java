package com.fang.material.entity.vo;

import com.fang.material.util.Const;

import java.util.List;

public class ListResult<T> {
    private int total;

    private List<T> rows;

    private String rtnCode;

    private String rtnMsg;

    public ListResult() {
        this.rtnCode = Const.SUCCESS;
        this.rtnMsg = Const.SUCCESS_MSG;
    }

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

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }
}
