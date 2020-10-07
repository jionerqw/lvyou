package com.lyf.service;

import com.lyf.domain.User;
import org.junit.Test;

//测试
public class TestUserService {
    @Test
    public void test01() {
        //将用户输入账号与密码发到后台
        UserService userService = new UserService();
        //1:查找用户数据
        User user = userService.findUserByName("jackhello");
        //2:根据数据， 正确，错误，不存在。
        if (user == null) {
            System.out.println("不存在");
        } else {
            //1:3种情况
            System.out.println("存在");
        }
    }

    @Test
    public void test02() {
        //将用户输入账号与密码发到后台
        UserService userService = new UserService();
        //1:查找用户数据
        User user = new User();
        user.setUsername("jackhello");
        user.setPassword("jackhello");
        user.setStatus("Y");
        int code = userService.login(user);
        //2:根据数据， 正确，错误，不存在。
        System.out.println(code);
    }

}
