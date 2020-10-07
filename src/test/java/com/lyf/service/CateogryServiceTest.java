package com.lyf.service;

import com.lyf.domain.Category;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CateogryServiceTest {
    @Test
    public void test01(){
        //导航测试
        CateogryService cateogryService= new CateogryService();
        List<Category> list = cateogryService.findAll();
        System.out.println(list);

    }


}