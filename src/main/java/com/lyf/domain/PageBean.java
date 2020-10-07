package com.lyf.domain;

import java.awt.*;
import java.util.List;

public class PageBean {
    //总记录数 100
    private int totalCount;
    //每页记录数 20
    private  int pageSize;
    //总页数 5
    private int totalPage;
    //当前页号
    private int currentPage;
    //当前页的数据
    private List<Route> list;

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", list=" + list +
                '}';
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Route> getList() {
        return list;
    }

    public void setList(List<Route> list) {
        this.list = list;
    }

    public PageBean count(int totalCount) {
        //设置总条数
        this.setTotalCount(totalCount);

        //设置总页数
        int totalPage = totalCount%pageSize==0 ? totalCount/pageSize : totalCount/pageSize+1;
        this.setTotalPage(totalPage);
        return this;
    }

    public PageBean list(List<Route> list) {
        this.list=list;
        return this;

    }
}
