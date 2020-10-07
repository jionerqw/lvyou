package com.lyf.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MySessionUtils2 {

    private static SqlSessionFactory sqlSessionFactory;
    static {
        //1 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream resourceAsStream = MySessionUtils2.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory= sqlSessionFactoryBuilder.build(resourceAsStream);

    }

    private static ThreadLocal<SqlSession> map=new ThreadLocal<SqlSession>();
    public static SqlSession getSession(){
        SqlSession sqlSession = map.get();
        if (sqlSession != null){
            return sqlSession;
        }else {
            sqlSession  = sqlSessionFactory.openSession();
            map.set(sqlSession);
            return sqlSession;

        }

    }

    public static void commitAndClose() {
        //将来进行写操作，之后需要提交，我们定义的方法
        SqlSession session = map.get();
        if (session != null) {
            session.commit();//提交
            session.close();//释放
            //已经关闭的session不能留在local
            //所以要删除
            map.remove();
        }
    }

    public static void rollbackAndClose() {
        //将来进行写操作，之后需要提交，我们定义的方法
        SqlSession session = map.get();
        if (session != null) {
            session.rollback();//回滚
            session.close();//释放
            //已经关闭的session不能留在local
            //所以要删除
            map.remove();
        }
    }

    public static <T> T getMapper(Class clz) {
        return (T) getSession().getMapper(clz);
    }
}
