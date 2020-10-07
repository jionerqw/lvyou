package com.lyf.service;

import com.lyf.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void register() {
        UserService userService = new UserService();
        User user = new User();
        user.setUsername("jackjacke");
        user.setPassword("12345678");
        user.setStatus("Y");
        int code = userService.register(user);
        System.out.println(code);
    }

    @Test
    public void active() {
        UserService userService = new UserService();
        int code = userService.active("b6627152dddc4eb984b997f80769c53e");
        System.out.println(code);

    }
}