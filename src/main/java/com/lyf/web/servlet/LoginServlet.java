package com.lyf.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyf.domain.ResponseInfo;
import com.lyf.domain.User;
import com.lyf.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            responseInfo.setData("登录失败，验证码出错");

            //json
            String json = new ObjectMapper().writeValueAsString(responseInfo);
            response.getWriter().println(json);
            return;

        }

        //获取请求参数
        Map<String, String[]> map = request.getParameterMap();
        User u = new User();
        try {
            //参1 javaBean 参2 map
            BeanUtils.populate(u,map);//将map里面所有的参数赋值给javaBean
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //处理参数
        UserService userService = new UserService();
        int code = userService.login(u);
        //响应给浏览器 ajax 是响应json给浏览器就可以
        ResponseInfo info = new ResponseInfo();
        info.setCode(code);
        if(code == - 1){
            info.setData("用户不存在");
        }else  if(code ==  1){
            info.setData("登录成功");
            User user = userService.findUserByName(u.getUsername());
            request.getSession().setAttribute("user",user);

        }else  if(code ==  -2){
            info.setData("账号密码出错");
        }else  if(code ==  -3){
            info.setData("账号未激活");
        }
        //转成json
        String json =new ObjectMapper().writeValueAsString(info);
        response.getWriter().println(json);
    }
}
