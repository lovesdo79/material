package com.bgfang.material.util;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by bgfang on 2017/4/13.
 */
public class ResultMap<T> {
    private String rtnCode;
    private String rtnMsg;
    private List<T> data;

    public ResultMap() {
        this.rtnCode = Const.SUCCESS;
        this.rtnMsg = Const.SUCCESS_MSG;
    }

    public ResultMap(String rtnCode) {
        this.rtnCode = rtnCode;
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
