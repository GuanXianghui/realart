<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="reviewHeader.jsp" %>
<%
    //外层
    outLayer = "用户模块";
    //内层
    inLayer = "密码修改";
%>
<html>
<head>
    <title>密码修改</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <script type="text/javascript" src="scripts/updateReviewPassword.js"></script>
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
                Hello, [<%=user.getName()%>],真艺网欢迎您！
                <br/>
                <br/>
                <a href="javascript: logOut()" title="Sign Out">退出</a>
            </div>
            <%@ include file="reviewLayers.jsp" %>
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
                <h3>密码修改</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <form name="updateReviewPasswordForm" method="post" action="updateReviewPassword.do">
                        <input type="hidden" name="token" value="<%=token%>">
                        <table id="guide_table">
                            <tr>
                                <td class="leftTd">
                                    当前密码:
                                </td>
                                <td>
                                    <input class="text-input large-input" type="password" id="old_password"
                                           name="oldPassword" value="">
                                </td>
                            </tr>
                            <tr>
                                <td class="leftTd">
                                    新密码:
                                </td>
                                <td>
                                    <input class="text-input large-input" type="password" id="new_password"
                                           name="newPassword" value="">
                                </td>
                            </tr>
                            <tr>
                                <td class="leftTd">
                                    确认密码:
                                </td>
                                <td>
                                    <input class="text-input large-input" type="password" id="confirm_password"
                                           name="confirmPassword" value="">
                                </td>
                            </tr>
                        </table>
                        <input class="button" type="button" value="OK 提交" onclick="updateReviewPassword()">
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