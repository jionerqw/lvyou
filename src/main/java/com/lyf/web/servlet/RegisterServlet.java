package com.lyf.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyf.domain.ResponseInfo;
import com.lyf.domain.User;
import com.lyf.service.UserService;
import com.sun.org.apache.xpath.internal.objects.XObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求中获取check1
        String check1 = request.getParameter("check");
        //从session中获取check
        String check2 = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        System.out.println(check1);
        System.out.println(check2);
        //验证码一次性产品，用完以后就销毁
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        //比较c1和C2
        if (check1 ==null ||!check1.equalsIgnoreCase(check2)){
            ResponseInfo responseInfo = new ResponseInfo();
            responseInfo.setCode(-4);
            responseInfo.setData("注册失败，验证码出错");

            //json
            String json = new ObjectMapper().writeValueAsString(responseInfo);
            response.getWriter().println(json);
            return ;

        }

        ConvertUtils.register(new Converter() {
            @Override
            public Object  convert(Class type, Object value) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return  simpleDateFormat.parse(value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, Date.class);

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService userService = new UserService();
        int code = userService.register(user);

        //响应给浏览器
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(code);
        if (code==1){
            responseInfo.setData("注册成功");
            System.out.println("mmp");
        }else {
            responseInfo.setData("注册失败");
        }
        //json
         String json=new ObjectMapper().writeValueAsString(responseInfo);
         response.getWriter().println(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
