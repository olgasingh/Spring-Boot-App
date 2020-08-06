package com.example.demo.Models.containers;

import java.util.ArrayList;
import java.util.List;

public class ListContainer {
    private List<ListItem> searchItems = new ArrayList<ListItem>();
    private String orderBy = "id";
    private String orderDirection = "DESC";

    private int pageNo = 0;
    private int pageSize = 1;

    public void setSearchItems(List<ListItem> searchItems) {
        this.searchItems = searchItems;
    }

    public List<ListItem> getSearchItems() {
        return searchItems;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}