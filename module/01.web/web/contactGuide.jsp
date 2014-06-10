<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
<%@ include file="homeWithOutCheckLogin.jsp" %>
    <%
        //外层
        outLayer = "首页模块";
        //内层
        inLayer = "首页联系方法管理";
        //导航json串转换成数组
        JSONArray json = JSONArray.fromObject(contactGuide);
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
            <h3>首面联系方法标题</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <form name="updateContactTitleForm" action="updateContactTitle.do" method="post">
                    <input type="hidden" name="token" value="<%=token%>">
                    首面联系方法标题：
                    <input class="text-input small-input" type="text" name="contactTitle" value="<%=contactTitle%>">
                    <input class="button" type="submit" value="保存">
                </form>
            </div>
        </div>
    </div>

    <div class="content-box">
        <div class="content-box-header">
            <h3>首页联系方法管理</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <span style="display: none;">
                    <form name="deleteContactGuideForm" action="deleteContactGuide.do" method="post">
                        <input type="hidden" name="token" value="<%=token%>">
                        <input type="hidden" name="index" id="delete_contact_guide_index">
                    </form>
                </span>
                <form name="updateContactGuideForm" method="post" autocomplete="off"
                      action="updateContactGuide.do?token=<%=token%>" enctype="multipart/form-data">
                    <input type="hidden" id="guideCount" name="guideCount" value="<%=json.size()%>">
                    <table id="guide_table">
                        <thead>
                        <tr>
                            <th width="20%">方式</th>
                            <th>文字</th>
                            <th>图片</th>
                            <th width="20%">操作</th>
                        </tr>
                        </thead>
                        <%
                            for(int i=0;i<json.size();i++)
                            {
                                JSONObject temp = json.getJSONObject(i);
                                String name = (StringUtils.trimToEmpty((String) temp.get("name")));
                                String text = (StringUtils.trimToEmpty((String) temp.get("text")));
                                String ico = (StringUtils.trimToEmpty((String) temp.get("ico")));
                        %>
                        <tr>
                            <td>
                                <input class="text-input large-input" type="text" name="name<%=i+1%>" value="<%=name%>">
                            </td>
                            <td>
                                <input class="text-input large-input" type="text" name="text<%=i+1%>" value="<%=text%>">
                            </td>
                            <td>
                                <img width="20" src="<%=ico%>">
                                <input type="file" name="ico<%=i+1%>">
                            </td>
                            <td>
                                <input class="button" type="button" onclick="deleteGuide(<%=i+1%>);" value="删除">
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                    <input class="button" type="button" onclick="addGuide();" value="新增联系方法">
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