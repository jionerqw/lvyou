package com.lyf.service;

import com.lyf.domain.PageBean;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteServiceTest {
    @Test
    public void test01(){
        RouteService routeService = new RouteService();
        PageBean pageBean=routeService.serach("宁夏",2,20);
        System.out.println(pageBean);
    }
    @Test
    public void test02(){
        RouteService routeService = new RouteService();
        PageBean pageBean = routeService.findPageById(5, 1, 20);
        System.out.println(pageBean);
    }

}