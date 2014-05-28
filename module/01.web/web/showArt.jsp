<%@ page import="com.realart.dao.ArtDao" %>
<%@ page import="com.realart.entities.Art" %>
<%@ page import="com.realart.dao.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<%
    int id;
    try{
        id = Integer.parseInt(request.getParameter("id"));
    } catch (Exception e){
        response.sendRedirect("index.jsp");
        return;
    }
    Art art = ArtDao.getArtById(id);
    if(art == null){
        response.sendRedirect("index.jsp");
        return;
    }
    if(user != null && user.getId() == art.getUserId() && StringUtils.isNotBlank(request.getParameter("setTop"))){
        int setTop;
        try{
            setTop = Integer.parseInt(request.getParameter("setTop"));
        } catch (Exception e){
            response.sendRedirect("index.jsp");
            return;
        }
        if(1 == setTop){
            art.setSelfTop(true);
        } else if(0 == setTop){
            art.setSelfTop(false);
        } else {
            response.sendRedirect("index.jsp");
            return;
        }
        //更改置顶
        ArtDao.updateLocationBit(art);
    }

    User artistUser = UserDao.getUserById(art.getUserId());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>评论页面</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/showArt.js"></script>
    <script type="text/javascript">
        //艺术品id
        var artId = <%=art.getId()%>;
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
    <div>作品名称:<%=art.getName()%>
        <%
            if(isAdminLogin){
        %>
        <input type="button" value="<%=art.isYszlTop()?"下艺术官网图":"上艺术官网图"%>" onclick="<%=art.isYszlTop()?"yszlTop(0)":"yszlTop(1)"%>">
        <form name="updateArtLocationBitForm" action="updateArtLocationBit.do" method="post">
            <input type="hidden" name="token" value="<%=token%>">
            <input id="updateArtLocationBitArtId" name="artId" type="hidden">
            <input id="updateArtLocationBitType" name="type" type="hidden">
            <input id="updateArtLocationBitValue" name="value" type="hidden">
        </form>
        <%
            }
        %>
    </div>
    <div>状态:<%=art.getStateDesc()%></div>
    <div>艺术家:<%=artistUser.getCertName()%></div>
    <div>
        作品主图:<img src="<%=art.getPhoto()%>" width="25%">
        <%
            if(user != null && art.getUserId() == user.getId() && StringUtils.isNotBlank(art.getPhoto())){
                if(art.isSelfTop()){
        %>
        <input type="button" value="取消置顶" onclick="location.href='showArt.jsp?id=<%=id%>&setTop=0'">
        <%
                } else {
        %>
        <input type="button" value="置顶" onclick="location.href='showArt.jsp?id=<%=id%>&setTop=1'">
        <%
                }
            }
        %>
    </div>
    <div>
        作者与作品合影:<img src="<%=art.getPhoto0()%>" width="25%">
    </div>
    <div>
        作品其他方位图片1:<img src="<%=art.getPhoto1()%>" width="25%">
    </div>
    <div>
        作品其他方位图片2:<img src="<%=art.getPhoto2()%>" width="25%">
    </div>
    <div>
        作品其他方位图片3:<img src="<%=art.getPhoto3()%>" width="25%">
    </div>
    <div>
        作品局部特征图片:<img src="<%=art.getPhoto4()%>" width="25%">
    </div>
    <div>
        主要工艺:<%=art.getGongyi()%>
    </div>
    <div>
        造型种类:<%=art.getType()%>
    </div>
    <div>
        长:<%=art.getLength()%>
    </div>
    <div>
        宽:<%=art.getWidth()%>
    </div>
    <div>
        高:<%=art.getHeight()%>
    </div>
    <div>
        创作时间:<%=art.getBuildDate()%>
    </div>
    <div>
        题款:<%=art.getTitle()%>
    </div>
    <div>
        作品介绍:<%=art.getIntroduction()%>
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