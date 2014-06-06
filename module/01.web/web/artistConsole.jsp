<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="artistHeader.jsp" %>
<html>
<head>
    <title>艺术家控台</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <style type="text/css">
        .a2{
            background-color: rgb(212, 212, 204);
            width: 60%;
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
            padding: 0px;
            margin: 0px;
        }
        .a{
            background-color: gray;
            margin-bottom: 10px;
            width: 95%;
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
            padding: 5px;
        }
    </style>
</head>
<body>
<div align="center" style="background: url('images/realart_bg.jpg'); background-position: center;">
    <div style="height: 50px;"></div>
    <div><img src="images/realart_logo.png" height="100" alt="真艺网"></div>
    <div><img src="images/realart.png" height="100" alt="真艺网"></div>
    <div style="height: 50px;"></div>
    <div class="a2"><span onclick="location.href='artistUser.jsp?name=<%=user.getName()%>'">我的官网</span></div>
    <div class="a2"><span onclick="location.href='needCheckArts.jsp'">待审核作品</span></div>
    <div class="a2"><span onclick="location.href='uploadArt.jsp'">作品备案</span></div>
    <div class="a2"><span onclick="location.href='updateArtistInfo.jsp'">资料修改</span></div>
    <div class="a2"><span onclick="location.href='updateArtistPassword.jsp'">密码修改</span></div>
    <div style="height: 50px;"></div>
    <div class="a">
        <a href="#"><img width="40" src="images/button/1.jpg" alt=""></a>
        <a href="#"><img width="40" src="images/button/2.jpg" alt=""></a>
        <a href="#"><img width="40" src="images/button/3.jpg" alt=""></a>
        <a href="#"><img width="40" src="images/button/4.jpg" alt=""></a>
        <a href="#"><img width="40" src="images/button/5.jpg" alt=""></a>
        <a href="#"><img width="40" src="images/button/6.jpg" alt=""></a>
        <a href="#"><img width="40" src="images/button/7.jpg" alt=""></a>
        <a href="#"><img width="40" src="images/button/8.jpg" alt=""></a>
        <a href="#"><img width="40" src="images/button/9.jpg" alt=""></a>
        <a href="#"><img width="40" src="images/button/10.jpg" alt=""></a>
    </div>
</div>
</body>
</html>