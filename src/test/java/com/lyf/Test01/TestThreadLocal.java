package com.lyf.Test01;

import org.junit.Before;
import org.junit.Test;

public class TestThreadLocal {
    private ThreadLocal<String> map= new ThreadLocal<String>();

    @Before
    public void test01(){
        System.out.println(Thread.currentThread());
        map.set("狗东西");//map.put(Thread.currentThread(),"hello")


    }
    @Test
    public void test02(){
        System.out.println(Thread.currentThread());
        System.out.println(map.get());//map.get(Thread.currentThread())
    }
    }

