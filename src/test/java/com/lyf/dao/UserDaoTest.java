package com.lyf.dao;

import com.lyf.domain.User;
import com.lyf.util.MySessionUtils;
import com.lyf.util.MySessionUtils2;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class UserDaoTest {

//    @Before：用来初始化@Test方法要使用到的资源
//    @After:用来释放资源
    // @Before ->@Test -> @After
    private SqlSession session;
    private  UserDao userDao;
    @Before
    public void init() throws Exception {
        System.out.println("Before");
        //创建Session对象
         session = MySessionUtils.getSession();
         userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destory() throws Exception {
        System.out.println("After");

        //提交与关闭session
        session.commit();
        session.close();
    }

    @Test
    public void test01() {
      //查找对象
        User user = userDao.findByName("jack");
     //查看对象
        System.out.println(user);

    }
    @Test
    public void test02() {
        User user = userDao.findByName("admin123");
        //查看对象
        System.out.println(user);

    }



}