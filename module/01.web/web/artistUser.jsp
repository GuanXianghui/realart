<%@ page import="com.realart.dao.UserDao" %>
<%@ page import="com.realart.interfaces.UserInterface" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.realart.entities.Art" %>
<%@ page import="com.realart.dao.ArtDao" %>
<%@ page import="com.realart.interfaces.ArtInterface" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<%
    String name = StringUtils.trimToEmpty(request.getParameter("name"));
    if(StringUtils.isBlank(name)){
        response.sendRedirect("/index.jsp");
        return;
    }
    User artistUser = UserDao.getUserByName(name);
    if(artistUser == null){
        response.sendRedirect("/index.jsp");
        return;
    }

    if(artistUser.getState() != UserInterface.USER_STATE_NORMAL){
%>
<script type="text/javascript">
    alert("该艺术家资料未审核通过");
    location.href="index.jsp";
</script>
<%
        return;
    }

    List<Art> topArts = new ArrayList<Art>();
    List<Art> arts = ArtDao.queryArtsByUserIdAndState(artistUser.getId(), ArtInterface.NORMAL);
    for(Art art : arts){
        if(art.isSelfTop()){
            topArts.add(art);
        }
    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>艺术官网</title>
    <script type="text/javascript" src="/scripts/jquery-min.js"></script>
    <script type="text/javascript" src="/scripts/base.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <style type="text/css">
        body {
            font-family: Arial, Helvetica, sans-serif;
            color: #555;
            background: #ffffff url('images/b.png') top left repeat-y;
            font-size: 12px;
        }
        .leftTd{
            text-align : right;
            width : 100px;
        }
        .rightTd{
            width : 300px;
        }
    </style>
</head>
<body>
<div align="center" style="background: url('/<%=artistUser.getTitlePhoto()%>');
        vertical-align: middle;
        background-size: 100%;
        background-position: center;">
    <table>
        <tr>
            <td>
                <div><span style="font-size: 40px; font-weight: bold;"><%=artistUser.getCertName()%></span>艺术官网</div>
                <div><%=baseUrl%>a/<%=artistUser.getName()%></div>
            </td>
        </tr>
    </table>
</div>
<div style="background-color: rgb(212, 212, 204);" align="center">
    <div style="width: 600px; background-color: rgb(212, 212, 204);" align="left">
    <br>
    <br>
    <div>最新发布</div>
    <div>
        <%
            for(Art art : topArts){
        %>
        <a href="/showArt.jsp?id=<%=art.getId()%>"><img src="/<%=art.getPhoto()%>" alt="<%=art.getTitle()%>" width="100"></a>
        <%
            }
        %>
    </div>
        <br>
    <div>人物介绍</div>
    <div>
        <img src="/<%=artistUser.getHeadPhoto()%>" alt="<%=artistUser.getCertName()%>" width="100"><br>
        郭文连XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX郭文连XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX郭文连XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    </div>
        <br>
    <div>作品备案</div>
    <div>
        <table>
        <%
            for(int i=0;i<arts.size();i++){
                Art art = arts.get(i);
                if(i%4==0){
        %>
            <tr>
        <%
                }
        %>
            <td>
                <a href="/showArt.jsp?id=<%=art.getId()%>"><img src="/<%=art.getPhoto()%>" alt="<%=art.getTitle()%>" width="100"></a>
                <br>
                <%=art.getTitle()%>
            </td>
        <%
                if(i%4==3 || i+1==arts.size()){
         %>
            </tr>
        <%
                }
        %>
        <%
            }
        %>
        </table>
    </div>
        <br>
    <div>作品备案</div>
    </div>
</div>
<div align="center" style="background-color: gray;">
    <a href="#"><img width="40" src="/images/button/1.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/2.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/3.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/4.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/5.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/6.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/7.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/8.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/9.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/10.jpg" alt=""></a>
</div>
</body>
</html>