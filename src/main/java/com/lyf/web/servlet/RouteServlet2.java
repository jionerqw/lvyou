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

@WebServlet("/routeServlet2")
public class RouteServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
