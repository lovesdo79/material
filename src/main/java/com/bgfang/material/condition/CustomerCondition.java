package com.bgfang.material.condition;

/**
 * Created by bgfang on 2017/4/13.
 */
public class CustomerCondition extends BaseCondition {

    private String name;

    private String addr;

    private String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
