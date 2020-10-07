package com.lyf.service;

import com.lyf.domain.Category;
import com.lyf.dao.CategoryDao;
import com.lyf.util.MySessionUtils2;

import java.util.List;

public class CateogryService {
    public List<Category> findAll(){
        CategoryDao categoryDao = MySessionUtils2.getMapper(CategoryDao.class);
        List<Category> list= categoryDao.findAll();
        return list;

    }


}
