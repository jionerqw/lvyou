<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 头部 start -->
<header id="header">
    <div class="top_banner">
        <img src="images/top_banner.jpg" alt="">
    </div>
    <div class="shortcut">
        <!-- 未登录状态  -->
        <div class="login_out">
            <a href="login.jsp">登录</a>
            <a href="register.jsp">注册</a>
        </div>
        <!-- 登录状态  -->
        <div class="login">
            <%--            将session中的用户数据取出来 --%>
            <%--            判断用户对象是否为空  如果是提示请登录，否则显示用户信息--%>
            <c:if test="${user !=null }">
                <span>欢迎登录${user.name}</span>
            </c:if>
            <c:if test="${user==null}">
                <span>请登录</span>
            </c:if>

            <a href="myfavorite.html" class="collection">我的收藏</a>
            <a href="${pageContext.request.contextPath}/loginOutServlet">退出</a>
        </div>
    </div>
<script src="js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript">
        function searchRequest() {
            var keyword =$("#searchInput").val();
            if (keyword != null&& keyword.length>0){

                 window.location ='http://localhost:8080/Tourist_war_exploded/search_result.jsp?keyword='+keyword;
            }
            else {
                alert("不能为空");
            }

        }

    </script>
    <div class="header_wrap">
        <div class="topbar">
            <div class="logo">
                <a href="/"><img src="images/logo.jpg" alt=""></a>
            </div>
            <div class="search">
                <input id="searchInput" type="text" placeholder="请输入路线名称" class="search_input" autocomplete="off">
                <a href="javascript:searchRequest()" class="search-button">搜索</a>
            </div>
            <div class="hottel">
                <div class="hot_pic">
                    <img src="images/hot_tel.jpg" alt="">
                </div>
                <div class="hot_tel">
                    <p class="hot_time">客服热线(9:00-6:00)</p>
                    <p class="hot_num">400-000-0000</p>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- 头部 end -->
<!-- 首页导航 -->
<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
    $(function () {
//页面加载成功之后执行
        $.get("categoryServlet", function (data) {
           // alert(data)
            if (data.code==200){
                var list = data.data;
                var lis="";
                //拼接第一项
               lis ='<li class="nav-active"><a href="index.jsp">首页</a></li>';
                //拼接中间项
                for (var i=0;i<list.length;i++){
                    var li = '<li><a href="route_list.jsp?cid='+list[i].cid+'">' + list[i].cname+  '</a></li>';
                    lis += li;
                }

                //拼接最后一项
                lis +='<li><a href="favoriterank.html">收藏排行榜</a></li>';
                //显示在ul标签里面
                $("#nav").html(lis);
            }
        }, "json");
    })
</script>
<div class="navitem">
    <ul class="nav" id="nav">
        <%--<li class="nav-active"><a href="index.jsp">首页</a></li>
        <li><a href="route_list.html">门票</a></li>
        <li><a href="route_list.html">酒店</a></li>
        <li><a href="route_list.html">香港车票</a></li>
        <li><a href="route_list.html">出境游</a></li>
        <li><a href="route_list.html">国内游</a></li>
        <li><a href="route_list.html">港澳游</a></li>
        <li><a href="route_list.html">抱团定制</a></li>
        <li><a href="route_list.html">全球自由行</a></li>
        <li><a href="favoriterank.html">收藏排行榜</a></li>--%>
    </ul>
</div>

</body>
</html>
