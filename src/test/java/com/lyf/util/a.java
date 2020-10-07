package com.lyf.util;

public class a {


    public static void main(String[] args) {

            int a=1;
            int b=1;
            int c=fun(a,b); //调用fun后return回一个值这个值被C获取
        System.out.println(c);

    }

    private static int fun(int a, int b) {
        return a+b;
    }

}
