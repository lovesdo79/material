package com.fang.material.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Created by bgfang on 2017/4/13.
 */
public class ResultMap {
    private String rtnCode;
    private String rtnMsg;
    private Object data;

    public ResultMap() {
        this.rtnCode = Const.SUCCESS;
        this.rtnMsg = Const.SUCCESS_MSG;
    }

    public ResultMap(Object obj) {
        this.rtnCode = Const.SUCCESS;
        this.rtnMsg = Const.SUCCESS_MSG;
        this.data = obj;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
