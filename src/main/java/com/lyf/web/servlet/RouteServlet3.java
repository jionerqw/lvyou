package com.lyf.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyf.domain.PageBean;
import com.lyf.domain.ResponseInfo;
import com.lyf.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet("/route/*")
public class RouteServlet3 extends BaseServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http://localhost:8080/Tourist_war_exploded/route/search
        String uri = request.getRequestURI();//访问路径
        System.out.println(uri);
        //得到方法名
        int start = uri.lastIndexOf("/") + 1;
        String methodName  = uri.substring(start);
        System.out.println(methodName);
       /* if ("search".equals(methodName)){
            search(request,response);
        }
        if ("findByCid".equals(methodName)){
            findByCid(request,response);
        }*/
        //优化if
        try {
            Method method = RouteServlet3.class.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收请求，获取参数
        String keyword = request.getParameter("keyword");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage=1;
        if (currentPageStr!=null&&currentPageStr.length()>0){
            try {
                currentPage = Integer.parseInt(currentPageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        int pageSize=20;
        if (pageSizeStr!=null&&pageSizeStr.length()>0){
            try {
                pageSize = Integer.parseInt(pageSizeStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        //处理参数
        RouteService routeService = new RouteService();
        PageBean pageBean = routeService.serach(keyword, currentPage, pageSize);
        //响应浏览器
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(200);
        responseInfo.setData(pageBean);
        String json = toJson(200,"pb");
        response.getWriter().println(json);

    }
    public void findByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String cidStr = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        int cid=1;
        try {
            cid= Integer.parseInt(cidStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int currentPage=1;
        try {
            currentPage= Integer.parseInt(currentPageStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        int pageSize=1;
        try {
            pageSize= Integer.parseInt(pageSizeStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //处理参数
        RouteService routeService = new RouteService();
        PageBean pb = routeService.findPageById(cid,currentPage,pageSize);


        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(200);
        responseInfo.setData(pb);


        //生成json
        String json = new ObjectMapper().writeValueAsString(responseInfo);
        //响应浏览器
        response.getWriter().println(json);
    }
}


