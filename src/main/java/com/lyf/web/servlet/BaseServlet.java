package com.lyf.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyf.domain.ResponseInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http://localhost:8080/Tourist_war_exploded/route/search
        String uri = request.getRequestURI();//访问路径
        System.out.println(uri);
        //得到方法名
        int start = uri.lastIndexOf("/") + 1;
        String methodName  = uri.substring(start);
        System.out.println(methodName);
        try {
            Method method = RouteServlet3.class.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public String toJson(int code, Object data){
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(code);
        responseInfo.setData(data);
        String json=null;
        try {
            json = new ObjectMapper().writeValueAsString(responseInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
          return json;
    }
}
