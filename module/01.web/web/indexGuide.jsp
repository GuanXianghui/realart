<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
    <%
        //外层
        outLayer = "首页模块";
        //内层
        inLayer = "首页导航管理";
        //导航json串转换成数组
        JSONArray json = JSONArray.fromObject(indexGuide);
    %>
<html>
<head>
    <title>首页图片管理</title>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/indexGuide.js"></script>
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
            <h3>首页导航</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <span style="display: none;">
                    <form name="deleteGuideForm" action="deleteGuide.do" method="post">
                        <input type="hidden" name="token" value="<%=token%>">
                        <input type="hidden" name="index" id="delete_guide_index">
                    </form>
                    <form name="saveGuideForm" action="saveGuide.do" method="post">
                        <input type="hidden" name="token" value="<%=token%>">
                        <textarea name="guide" id="save_guide"></textarea>
                    </form>
                </span>
                <form>
                    <table id="guide_table">
                        <thead>
                        <tr>
                            <th width="20%">导航名称</th>
                            <th>导航链接</th>
                            <th width="20%">操作</th>
                        </tr>
                        </thead>
                        <%
                            for(int i=0;i<json.size();i++)
                            {
                                JSONObject temp = json.getJSONObject(i);
                                String name = (StringUtils.trimToEmpty((String) temp.get("name")));
                                String url = (StringUtils.trimToEmpty((String) temp.get("url")));
                        %>
                        <tr>
                            <td>
                                <input class="text-input large-input" type="text" id="guide_name_<%=i+1%>" value="<%=name%>">
                            </td>
                            <td>
                                <input class="text-input large-input" type="text" id="guide_url_<%=i+1%>" value="<%=url%>">
                            </td>
                            <td>
                                <input class="button" type="button" onclick="deleteGuide(<%=i+1%>);" value="删除">
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </form>
                注意导航链接：真艺网页面可写相对路径如：置顶推荐页zdtj.jsp，外网地址可写全路径带上http如：百度首页http://www.baidu.com<br>
                <input class="button" type="button" onclick="addGuide();" value="新增导航">
                <input class="button" type="button" onclick="saveGuide();" value="保存">
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