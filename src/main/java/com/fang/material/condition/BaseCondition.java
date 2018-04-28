package com.fang.material.condition;

/**
 * Created by bgfang on 2017/4/13.
 */
public class BaseCondition {

    private String token;

    /**
     * 当前页数
     */
    private int page;
    /**
     * 每页多少条记录
     */
    private int rows;

    private int startSize;

    private int pageSize;
    /**
     * 排序字段
     */
    private String sort;
    /**
     * 排序方式（升序、降序）
     */
    private String order;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStartSize() {
        if (0 == this.getPage()) {
            return 0;
        }
        int start = this.getPageSize() * (this.getPage() - 1) - 1;
        return startSize;
    }

    public void setStartSize(int startSize) {
        this.startSize = startSize;
    }

    public int getPageSize() {
        return rows;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
