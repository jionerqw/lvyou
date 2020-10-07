package com.lyf.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyf.domain.Category;
import com.lyf.domain.ResponseInfo;
import com.lyf.service.CateogryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet( "/categoryServlet")
public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建业务对象
        CateogryService cateogryService= new CateogryService();
        //所有分类
        List<Category> categoryList = cateogryService.findAll();
       // 响应给浏览器
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(200);
        responseInfo.setData(categoryList);
        //返回json格式
        String json = new ObjectMapper().writeValueAsString(responseInfo);
        response.getWriter().println(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
