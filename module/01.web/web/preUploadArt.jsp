<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="artistHeader.jsp" %>
<%
    //外层
    outLayer = "备案模块";
    //内层
    inLayer = "作品备案";
%>
<html>
<head>
    <title>作品备案</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
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
            <%@ include file="artistLayers.jsp" %>
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
                <h3>作品备案</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <form>
                        <table id="guide_table">
                            <tr>
                                <td style="text-align: center;">
                                    序列号:
                                    <input class="text-input small-input" type="text" id="qr_code">
                                    <input class="button" type="button" value="输入" onclick="location.href='qr/' + $('#qr_code').val()">
                                </td>
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