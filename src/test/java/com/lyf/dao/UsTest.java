package com.lyf.dao;

import com.lyf.domain.User;
import com.lyf.util.MySessionUtils2;
import org.junit.Test;

import java.util.Date;

public class UsTest {
    @Test
    public void findByUserName() {
        UserDao dao =    MySessionUtils2.getMapper(UserDao.class);
        User user = dao.findByUserName("jackhello");
        System.out.println(user);
    }

    @Test
    public void save() {
        UserDao userDao= MySessionUtils2.getMapper(UserDao.class);
        User user = new User(33,"jackhello44","jackhello34",new Date(),"jack","男","3333","333@qq.com","N","8dsa");
        userDao.save(user);
        MySessionUtils2.commitAndClose();
    }

    @Test
    public void updateStatus() {
        UserDao dao =    MySessionUtils2.getMapper(UserDao.class);
        int code =  dao.updateStatus("b6627152dddc4eb984b997f80769c53e");
        System.out.println(code);
        MySessionUtils2.commitAndClose();
    }
}
