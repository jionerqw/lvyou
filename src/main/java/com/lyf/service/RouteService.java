package com.lyf.service;

import com.lyf.dao.RouteDao;
import com.lyf.domain.PageBean;
import com.lyf.domain.PageBeanHelper;
import com.lyf.domain.Route;
import com.lyf.util.MySessionUtils;
import com.lyf.util.MySessionUtils2;

import java.util.List;

public class RouteService {
    public PageBean serach(String keyword, int currentPage, int pageSize) {
        /*//创建分页数据对象  包含4个整数与一个集合
        PageBean pb = new PageBean();
        //设置当前页号
        pb.setCurrentPage(currentPage);
        //设置每页记录数
        pb.setPageSize(pageSize);
        //设置总记录数*/
        RouteDao routeDao = MySessionUtils2.getMapper(RouteDao.class);
        int totalCount  = routeDao.findCountByName("%"+keyword+"%");
      /*  pb.setTotalCount(totalCount);*/

        //设置总页数  每页最多只能放20条   40  2  与 41  3
        //在java中，整数相除只保留整数部，丢失小数部  41/20  就是2
      /*  int totalPage=totalCount%pageSize==0 ?totalCount/pageSize:totalCount/pageSize+1;*/
      /*  pb.setTotalPage(totalPage);*/

        //设置PageBean中的list集合
        int start = (currentPage-1)*pageSize;
        List<Route> list = routeDao.findPageByName("%"+keyword+"%",start,pageSize);
       /* pb.setList(list);*/
        PageBean pb = PageBeanHelper.start(currentPage, pageSize).count(totalCount).list(list);
        return pb;

    }

    public PageBean findPageById(int cid, int currentPage, int pageSize) {
       /* PageBean pb = new PageBean();
        //设置当前页号
        pb.setCurrentPage(currentPage);
        //设置当前页的条数
        pb.setPageSize(pageSize);
*/
        //设置总记录数
        RouteDao routeDao = MySessionUtils2.getMapper(RouteDao.class);
        int totalCount=routeDao.findCountById(cid);
       /* pb.setTotalCount(totalCount);
*/
        //设置总页数
       /* int totalPage = totalCount%pageSize==0 ? totalCount/pageSize : pageSize/currentPage+1;*/
       /* pb.setTotalPage(totalPage);*/

        //设置list
        int start=(currentPage-1)*pageSize;
        List<Route> list=routeDao.findPageByCid(cid,start,pageSize);
       /* pb.setList(list);*/
       PageBean pb= PageBeanHelper.start(currentPage,pageSize).count(totalCount).list(list);
        return pb;

    }
}
