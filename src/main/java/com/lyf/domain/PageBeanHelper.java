package com.lyf.domain;

public class PageBeanHelper {
    public static PageBean start(int currentPage,int pageSize){
        PageBean pb = new PageBean();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        return pb;
    }


}
