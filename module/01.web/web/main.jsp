<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>网站地图</title>
    <script type="text/javascript">
        function clickA(t){
            document.getElementById("url").innerHTML = t.href;
            document.getElementById("right").src = t.href;
        }
    </script>
</head>
<body>
<div style="float: right; width: 83%; height: 95%; border: 1px solid red;">
    <span id="url"></span><br>
    <iframe id="right" style="width: 99%; height: 95%;"></iframe>
</div>
<div align="center" style="width: 15%;">
    <div><a href="index.jsp" target="right" onclick="clickA(this);return false;">首页</a></div>
    <div><a href="reviewUsers.jsp" target="right" onclick="clickA(this)">所有评论会员页面</a></div>
    <div><a href="artistUsers.jsp" target="right" onclick="clickA(this)">所有艺术家页面</a></div>
    <hr>
    <div><a href="registReview.jsp" target="right" onclick="clickA(this)">评论会员注册</a></div>
    <div><a href="loginReview.jsp" target="right" onclick="clickA(this)">评论会员登录</a></div>
    <div><a href="reviewUser.jsp?name=gxx" target="right" onclick="clickA(this)">评论会员页面</a></div>
    <div><a href="/r/gxx" target="right" onclick="clickA(this)">评论会员页面</a></div>
    <div><a href="createReview.jsp" target="right" onclick="clickA(this)">新增评论</a></div>
    <div><a href="zdtj.jsp" target="right" onclick="clickA(this)">置顶推荐页面</a></div>
    <div><a href="mwgs.jsp" target="right" onclick="clickA(this)">美文共赏页面</a></div>
    <div><a href="bzyc.jsp" target="right" onclick="clickA(this)">本站原创页面</a></div>
    <div><a href="showReview.jsp?id=1" target="right" onclick="clickA(this)">查看评论</a></div>
    <hr>
    <div><a href="registArtist.jsp" target="right" onclick="clickA(this)">艺术家注册界面</a></div>
    <div><a href="loginArtist.jsp" target="right" onclick="clickA(this)">艺术家登陆界面</a></div>
    <div><a href="artistConsole.jsp" target="right" onclick="clickA(this)">艺术家控台</a></div>
    <div><a href="artistUser.jsp?name=lqy" target="right" onclick="clickA(this)">艺术家页面</a></div>
    <div><a href="/a/lqy" target="right" onclick="clickA(this)">艺术家页面</a></div>
    <hr>
    <div><a href="loginAdmin.jsp" target="right" onclick="clickA(this)">管理员登录</a></div>
    <div><a href="adminRegistArtist.jsp" target="right" onclick="clickA(this)">管理员注册艺术家界面</a></div>
    <div><a href="checkUpArtist.jsp" target="right" onclick="clickA(this)">管理员审核艺术家</a></div>
    <div><a href="checkUpArt.jsp" target="right" onclick="clickA(this)">管理员审核艺术品</a></div>
    <hr>
    <div><a href="login.jsp" target="right" onclick="clickA(this)">统一登录页面</a></div>
</div>
</body>
</html>