<%@ page import="java.util.List" %>
<%@ page import="com.realart.utils.BaseUtil" %>
<%@ page import="com.realart.dao.ArtDao" %>
<%@ page import="com.realart.interfaces.ArtInterface" %>
<%@ page import="com.realart.entities.Art" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="artistHeader.jsp" %>
<%
    if(user.getState() != UserInterface.USER_STATE_NORMAL){
%>
<script type="text/javascript">
    alert("您的艺术家资料未审核通过");
    location.href="artistConsole.jsp";
</script>
<%
        return;
    }
%>

<%
    //待审核的艺术品
    List<Art> arts = ArtDao.queryArtsByUserIdAndState(user.getId(), ArtInterface.NEED_CHECK);
    List<Art> arts2 = ArtDao.queryArtsByUserIdAndState(user.getId(), ArtInterface.CHECK_FAILED);
    arts.addAll(arts2);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>待审核作品页面</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/pinyin.js"></script>
    <script type="text/javascript" src="scripts/needCheckArts.js"></script>
    <script type="text/javascript">
        //艺术品集合json串
        var artJsonStr = "<%=BaseUtil.getJsonArrayFromArts(arts)%>";
        //选择艺术品Id
        var chooseArtId = 0;
    </script>
</head>
<body>
<div align="center" style="background: url('images/realart_bg.jpg');
    vertical-align: middle;
    background-size: 100%;
    background-position: center;">
    <img src="images/realart_logo.png" height="100" alt="真艺网">
    <img src="images/realart.png" height="100" alt="真艺网">
</div>
<div>
    <div>待审核艺术品</div>
    <div>
        <%
            for(Art art : arts){
        %>
            <a href="javascript: chooseArt(<%=art.getId()%>)"><%=art.getName()%></a>&nbsp;&nbsp;&nbsp;
        <%
            }
        %>
    </div>
</div>
<hr>
<div>
    <div>艺术品详情</div>
    <div>
        <form name="updateArtForm" method="post" autocomplete="off" action="updateArt.do?token=<%=token%>"
              enctype="multipart/form-data">
            <input type="hidden" name="artId" id="updateArtId">
            <div>作品名称：<span id="name"></span></div>
            <div>状态：<span id="state"></span></div>
            <div>作品主图：<span id="photo"></span></div>
            <div>作者与作品合影：<span id="photo0"></span></div>
            <div>作品其他方位图片1：<span id="photo1"></span></div>
            <div>作品其他方位图片2：<span id="photo2"></span></div>
            <div>作品其他方位图片3：<span id="photo3"></span></div>
            <div>作品局部特征图片：<span id="photo4"></span></div>
            <div>主要工艺：<span id="gongyi"></span></div>
            <div>造型种类：<span id="type"></span></div>
            <div>长：<span id="length"></span></div>
            <div>宽：<span id="width"></span></div>
            <div>高：<span id="height"></span></div>
            <div>创作时间：<span id="build_date"></span></div>
            <div>题款：<span id="title"></span></div>
            <div>作品介绍：<span id="introduction"></span></div>
            <div><input type="button" value="修改" onclick="updateArt()"></div>
        </form>
    </div>
</div>
<div align="center" style="background-color: gray;">
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
</body>
</html>