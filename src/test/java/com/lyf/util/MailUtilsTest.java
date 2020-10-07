package com.lyf.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MailUtilsTest {

    @Test
    public void sendMail() {
        //参1 收件人
        //参2 内容
        //参3 标题
        MailUtils.sendMail("1064963180@qq.com","<a href='http://localhost:8080/lvyou_war_exploded/activeServlet?activeCode=bb26f0d05ea745c5986bc18ff7b4cef9'>点击激活途牛旅游账户</a>","激活账户");
    }

}