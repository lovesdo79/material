package com.bgfang.material.entity.domain;

public class ProductSupplierDomain {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRODUCTS_SUPPLIER.PRODUCTS_SUPPLIER_ID
     *
     * @mbg.generated Thu Apr 20 23:44:38 CST 2017
     */
    private String productsSupplierId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRODUCTS_SUPPLIER.PRODUCT_ID
     *
     * @mbg.generated Thu Apr 20 23:44:38 CST 2017
     */
    private String productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRODUCTS_SUPPLIER.SUPPLIER_ID
     *
     * @mbg.generated Thu Apr 20 23:44:38 CST 2017
     */
    private String supplierId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRODUCTS_SUPPLIER.PRODUCTS_SUPPLIER_ID
     *
     * @return the value of PRODUCTS_SUPPLIER.PRODUCTS_SUPPLIER_ID
     *
     * @mbg.generated Thu Apr 20 23:44:38 CST 2017
     */
    public String getProductsSupplierId() {
        return productsSupplierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRODUCTS_SUPPLIER.PRODUCTS_SUPPLIER_ID
     *
     * @param productsSupplierId the value for PRODUCTS_SUPPLIER.PRODUCTS_SUPPLIER_ID
     *
     * @mbg.generated Thu Apr 20 23:44:38 CST 2017
     */
    public void setProductsSupplierId(String productsSupplierId) {
        this.productsSupplierId = productsSupplierId == null ? null : productsSupplierId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRODUCTS_SUPPLIER.PRODUCT_ID
     *
     * @return the value of PRODUCTS_SUPPLIER.PRODUCT_ID
     *
     * @mbg.generated Thu Apr 20 23:44:38 CST 2017
     */
    public String getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRODUCTS_SUPPLIER.PRODUCT_ID
     *
     * @param productId the value for PRODUCTS_SUPPLIER.PRODUCT_ID
     *
     * @mbg.generated Thu Apr 20 23:44:38 CST 2017
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRODUCTS_SUPPLIER.SUPPLIER_ID
     *
     * @return the value of PRODUCTS_SUPPLIER.SUPPLIER_ID
     *
     * @mbg.generated Thu Apr 20 23:44:38 CST 2017
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRODUCTS_SUPPLIER.SUPPLIER_ID
     *
     * @param supplierId the value for PRODUCTS_SUPPLIER.SUPPLIER_ID
     *
     * @mbg.generated Thu Apr 20 23:44:38 CST 2017
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }
}