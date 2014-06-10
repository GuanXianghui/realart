<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
<%@ include file="homeWithOutCheckLogin.jsp" %>
    <%
        //外层
        outLayer = "首页模块";
        //内层
        inLayer = "首页在线服务信息管理";
        JSONObject onlineInfoObj = JSONObject.fromObject(onlineInfo);
        JSONArray qqInfo = JSONArray.fromObject(onlineInfoObj.get("qq"));
        JSONObject qq1 = JSONObject.fromObject(qqInfo.get(0));
        JSONObject qq2 = JSONObject.fromObject(qqInfo.get(1));
        JSONObject emailInfo =JSONObject.fromObject(onlineInfoObj.get("email"));
        JSONObject companyInfo =JSONObject.fromObject(onlineInfoObj.get("company"));
        JSONObject liuYanInfo =JSONObject.fromObject(onlineInfoObj.get("liuyan"));
    %>
<html>
<head>
    <title>首页图片管理</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/onlineInfo.js"></script>
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
            <h3>首页友情链接标题管理</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <form name="updateOnlineInfoForm" action="updateOnlineInfo.do" method="post">
                    <input type="hidden" name="token" value="<%=token%>">
                    <table>
                        <tr>
                            <td>QQ1</td>
                            <td>号码：<input class="text-input between-medium-large-input"
                                          type="text" name="qqNumber1" value="<%=qq1.getString("number")%>"></td>
                            <td>头衔：<input class="text-input between-medium-large-input"
                                          type="text" name="qqName1" value="<%=qq1.getString("name")%>"></td>
                            <td>提示：<input class="text-input between-medium-large-input"
                                          type="text" name="qqAlt1" value="<%=qq1.getString("alt")%>"></td>
                        </tr>
                        <tr>
                            <td>QQ2</td>
                            <td>号码：<input class="text-input between-medium-large-input"
                                          type="text" name="qqNumber2" value="<%=qq2.getString("number")%>"></td>
                            <td>头衔：<input class="text-input between-medium-large-input"
                                          type="text" name="qqName2" value="<%=qq2.getString("name")%>"></td>
                            <td>提示：<input class="text-input between-medium-large-input"
                                          type="text" name="qqAlt2" value="<%=qq2.getString("alt")%>"></td>
                        </tr>
                        <tr>
                            <td>留言按钮链接(这个不知道怎么用)</td>
                            <td colspan="3"><input class="text-input between-medium-large-input"
                                                        type="text" name="liuYanHref" value="<%=liuYanInfo.getString("href")%>"></td>
                        </tr>
                        <tr>
                            <td>邮箱</td>
                            <td colspan="3"><input class="text-input between-medium-large-input"
                                                        type="text" name="emailNumber" value="<%=emailInfo.getString("number")%>"></td>
                        </tr>
                        <tr>
                            <td>公司地标</td>
                            <td colspan="3"><input class="text-input between-medium-large-input"
                                                        type="text" name="companyHref" value="<%=companyInfo.getString("href")%>"></td>
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