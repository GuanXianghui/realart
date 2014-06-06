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
            width : 150px;
        }
        .rightTd{
            width : 300px;
        }
    </style>
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
<div style="background-color: rgb(212, 212, 204);" align="center">
    <div style="width: 600px; background-color: rgb(212, 212, 204);" align="left">
        <br>
        <br>
        <div align="center">
            <table>
                <tr>
                    <td class="leftTd">
                        作品名称:
                    </td>
                    <td class="rightTd">
                        <%=art.getName()%>
                        <%
                            if(isAdminLogin){
                        %>
                        <input class="button" type="button" value="<%=art.isYszlTop()?"下艺术官网图":"上艺术官网图"%>"
                               onclick="<%=art.isYszlTop()?"yszlTop(0)":"yszlTop(1)"%>">
                        <form name="updateArtLocationBitForm" action="updateArtLocationBit.do" method="post">
                            <input type="hidden" name="token" value="<%=token%>">
                            <input id="updateArtLocationBitArtId" name="artId" type="hidden">
                            <input id="updateArtLocationBitType" name="type" type="hidden">
                            <input id="updateArtLocationBitValue" name="value" type="hidden">
                        </form>
                        <%
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        状态:
                    </td>
                    <td class="rightTd">
                        <%=art.getStateDesc()%>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        艺术家:
                    </td>
                    <td class="rightTd">
                        <%=artistUser.getCertName()%>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品主图:
                    </td>
                    <td class="rightTd">
                        <img src="<%=art.getPhoto()%>" width="25%">
                        <%
                            if(user != null && art.getUserId() == user.getId() && StringUtils.isNotBlank(art.getPhoto())){
                                if(art.isSelfTop()){
                        %>
                        <input class="button" type="button" value="取消置顶" onclick="location.href='showArt.jsp?id=<%=id%>&setTop=0'">
                        <%
                        } else {
                        %>
                        <input class="button" type="button" value="置顶" onclick="location.href='showArt.jsp?id=<%=id%>&setTop=1'">
                        <%
                                }
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作者与作品合影:
                    </td>
                    <td class="rightTd">
                        <img src="<%=art.getPhoto0()%>" width="25%">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品其他方位图片1:
                    </td>
                    <td class="rightTd">
                        <img src="<%=art.getPhoto1()%>" width="25%">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品其他方位图片2:
                    </td>
                    <td class="rightTd">
                        <img src="<%=art.getPhoto2()%>" width="25%">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品其他方位图片3:
                    </td>
                    <td class="rightTd">
                        <img src="<%=art.getPhoto3()%>" width="25%">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品局部特征图片:
                    </td>
                    <td class="rightTd">
                        <img src="<%=art.getPhoto4()%>" width="25%">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        主要工艺:
                    </td>
                    <td class="rightTd">
                        <%=art.getGongyi()%>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        造型种类:
                    </td>
                    <td class="rightTd">
                        <%=art.getType()%>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        长:
                    </td>
                    <td class="rightTd">
                        <%=art.getLength()%>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        宽:
                    </td>
                    <td class="rightTd">
                        <%=art.getWidth()%>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        高:
                    </td>
                    <td class="rightTd">
                        <%=art.getHeight()%>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        创作时间:
                    </td>
                    <td class="rightTd">
                        <%=art.getBuildDate()%>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        题款:
                    </td>
                    <td class="rightTd">
                        <%=art.getTitle()%>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品介绍:
                    </td>
                    <td class="rightTd">
                        <%=art.getIntroduction()%>
                    </td>
                </tr>
            </table>
        </div>
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