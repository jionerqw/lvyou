package com.lyf.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MySessionUtils {

    private static SqlSessionFactory sqlSessionFactory;
    static {
        //1 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
       final InputStream resourceAsStream = MySessionUtils.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory= sqlSessionFactoryBuilder.build(resourceAsStream);

    }

    public static SqlSession getSession(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}
