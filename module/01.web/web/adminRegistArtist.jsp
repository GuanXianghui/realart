<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
<%
    //外层
    outLayer = "注册模块";
    //内层
    inLayer = "注册艺术家";
%>
<html>
<head>
    <title>注册艺术家</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <script type="text/javascript" src="scripts/adminRegistArtist.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
</head>
<body>
<div id="body-wrapper">
    <div id="sidebar">
        <div id="sidebar-wrapper">
            <h1 id="sidebar-title"><a href="#">真艺网</a></h1>
            <div align="center">
                <img id="logo" src="images/realart_logo.png" width="50" alt="Simpla Admin logo"/>
            </div>
            <div id="profile-links">
                Hello, [<%=adminUserName%>],真艺网欢迎您！
                <br/>
                <br/>
                <a href="javascript: logOut()" title="Sign Out">退出</a>
            </div>
            <%@ include file="layers.jsp" %>
        </div>
    </div>
    <div id="main-content">
        <div id="message_id" class="notification information png_bg" style="display: none;">
            <a href="#" class="close">
                <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
            </a>

            <div id="message_id_content"> 提示信息！</div>
        </div>
        <div class="content-box">
            <div class="content-box-header">
                <h3>注册艺术家</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <form name="adminRegistArtistForm" method="post" action="adminRegistArtist.do">
                        <input type="hidden" name="token" value="<%=token%>">
                        <table>
                            <tr>
                                <td>艺术家用户名</td>
                                <td><input class="text-input small-input" type="text" name="name" id="name"
                                           value="<%=StringUtils.trimToEmpty((String)request.getAttribute("name"))%>" /></td>
                            </tr>
                            <tr>
                                <td>密码</td>
                                <td><input class="text-input small-input" type="password" name="password" id="password" /></td>
                            </tr>
                            <tr>
                                <td>确认密码</td>
                                <td><input class="text-input small-input" type="password" name="confirmPassword" id="confirm_password" /></td>
                            </tr>
                            <tr>
                                <td>提交</td>
                                <td><input class="button" type="button" onclick="registArtist();" value="OK 提交" /></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div id="footer">
            <small>
                &#169; Copyright 2014 Realart
            </small>
        </div>
    </div>
</div>
</body>
</html>