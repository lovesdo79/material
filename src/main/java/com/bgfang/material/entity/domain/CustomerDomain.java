package com.bgfang.material.entity.domain;

import java.util.Date;

public class CustomerDomain {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER.CUSTOMER_ID
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    private String customerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER.NAME
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER.ADDRESS
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER.TEL
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    private String tel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER.REMARK
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER.CREATE_TIME
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER.UPDATE_TIME
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER.IS_DELETE
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    private Boolean isDelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER.type
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    private Integer type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER.CUSTOMER_ID
     *
     * @return the value of CUSTOMER.CUSTOMER_ID
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER.CUSTOMER_ID
     *
     * @param customerId the value for CUSTOMER.CUSTOMER_ID
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER.NAME
     *
     * @return the value of CUSTOMER.NAME
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER.NAME
     *
     * @param name the value for CUSTOMER.NAME
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER.ADDRESS
     *
     * @return the value of CUSTOMER.ADDRESS
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER.ADDRESS
     *
     * @param address the value for CUSTOMER.ADDRESS
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER.TEL
     *
     * @return the value of CUSTOMER.TEL
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER.TEL
     *
     * @param tel the value for CUSTOMER.TEL
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER.REMARK
     *
     * @return the value of CUSTOMER.REMARK
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER.REMARK
     *
     * @param remark the value for CUSTOMER.REMARK
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER.CREATE_TIME
     *
     * @return the value of CUSTOMER.CREATE_TIME
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER.CREATE_TIME
     *
     * @param createTime the value for CUSTOMER.CREATE_TIME
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER.UPDATE_TIME
     *
     * @return the value of CUSTOMER.UPDATE_TIME
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER.UPDATE_TIME
     *
     * @param updateTime the value for CUSTOMER.UPDATE_TIME
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER.IS_DELETE
     *
     * @return the value of CUSTOMER.IS_DELETE
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER.IS_DELETE
     *
     * @param isDelete the value for CUSTOMER.IS_DELETE
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER.type
     *
     * @return the value of CUSTOMER.type
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER.type
     *
     * @param type the value for CUSTOMER.type
     *
     * @mbg.generated Thu Apr 20 19:13:19 CST 2017
     */
    public void setType(Integer type) {
        this.type = type;
    }
}