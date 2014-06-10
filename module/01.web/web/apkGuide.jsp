<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
<%@ include file="homeWithOutCheckLogin.jsp" %>
    <%
        //外层
        outLayer = "首页模块";
        //内层
        inLayer = "首页下载安卓管理";
        //首页 下载安卓管理
        JSONObject json = JSONObject.fromObject(apkGuide);
    %>
<html>
<head>
    <title>首页图片管理</title>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/apkGuide.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript">
        //导航数量
        var guideCount = <%=json.size()%>;
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
            <h3>首页下载安卓管理</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <form name="updateApkGuideForm" method="post" autocomplete="off"
                      action="updateApkGuide.do?token=<%=token%>" enctype="multipart/form-data">
                    <table id="guide_table">
                        <thead>
                        <tr>
                            <th width="10%">字段</th>
                            <th>值</th>
                        </tr>
                        </thead>
                        <%
                            String title = (StringUtils.trimToEmpty((String) json.get("title")));
                            String logo = (StringUtils.trimToEmpty((String) json.get("logo")));
                            String btn = (StringUtils.trimToEmpty((String) json.get("btn")));
                            String alt = (StringUtils.trimToEmpty((String) json.get("alt")));
                        %>
                        <tr>
                            <td>
                                抬头描述
                            </td>
                            <td>
                                <input class="text-input large-input" type="text" name="title" value="<%=title%>">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                logo照片
                            </td>
                            <td>
                                <img src="<%=logo%>" width="200">
                                <input type="file" name="logo">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                按钮照片
                            </td>
                            <td>
                                <img src="<%=btn%>">
                                <input type="file" name="btn">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                提示文字
                            </td>
                            <td>
                                <input class="text-input large-input" type="text" name="alt" value="<%=alt%>">
                            </td>
                        </tr>
                    </table>
                    <input class="button" type="submit" value="保存">
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