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

@WebServlet("/routeServlet")
public class RouteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String json = new ObjectMapper().writeValueAsString(responseInfo);
        response.getWriter().println(json);

    }
}
