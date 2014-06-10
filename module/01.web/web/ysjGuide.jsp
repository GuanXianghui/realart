<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
<%@ include file="homeWithOutCheckLogin.jsp" %>
    <%
        //外层
        outLayer = "首页模块";
        //内层
        inLayer = "首页艺术家展示管理";
        //艺术家显示更多
        JSONObject json = JSONObject.fromObject(ysjMore);
    %>
<html>
<head>
    <title>首页图片管理</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/contactGuide.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript">
        //导航数量
        var guideCount = <%=json.size()%>;
        //上线个数 暂定30
        var maxCount = 30;
    </script>
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
            <h3>首页艺术家展示管理</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <form name="updateYsjGuideForm" action="updateYsjGuide.do" method="post">
                    <input type="hidden" name="token" value="<%=token%>">
                    <table>
                        <tr>
                            <td width="10%">展示标题：</td>
                            <td><input class="text-input medium-input" type="text" name="title" value="<%=ysjTitle%>"></td>
                        </tr>
                        <tr>
                            <td>更多文字：</td>
                            <td><input class="text-input medium-input" type="text" name="name" value="<%=json.getString("name") %>"></td>
                        </tr>
                        <tr>
                            <td>更多链接：</td>
                            <td><input class="text-input medium-input" type="text" name="href" value="<%=json.getString("href") %>"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input class="button" type="submit" value="保存"></td>
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