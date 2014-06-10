<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
<%@ include file="homeWithOutCheckLogin.jsp" %>
    <%
        //外层
        outLayer = "首页模块";
        //内层
        inLayer = "首页真艺展厅管理";
        //json串转换成数组
        JSONArray json = JSONArray.fromObject(slider01);
    %>
<html>
<head>
    <title>首页图片管理</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/realArtRoom.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript">
        //数量
        var realArtCount = <%=json.size()%>;
        //个数 暂定30
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
            <h3>首页真艺展厅</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <span style="display: none;">
                    <form name="deleteRealArtRoomForm" action="deleteRealArtRoom.do" method="post">
                        <input type="hidden" name="token" value="<%=token%>">
                        <input type="hidden" name="index" id="delete_real_art_room_index">
                    </form>
                </span>
                <form name="updateRealArtRoomForm" method="post" autocomplete="off"
                      action="updateRealArtRoom.do?token=<%=token%>" enctype="multipart/form-data">
                    <input type="hidden" id="realArtCount" name="realArtCount" value="<%=json.size()%>">
                    <table id="real_art_room_table">
                        <thead>
                        <tr>
                            <th width="20%">提示文字</th>
                            <th width="10%">图片</th>
                            <th width="40%">链接</th>
                            <th width="15%">是否显示</th>
                            <th width="15%">操作</th>
                        </tr>
                        </thead>
                        <%
                            for(int i=0;i<json.size();i++)
                            {
                                JSONObject temp = json.getJSONObject(i);
                                String title = (StringUtils.trimToEmpty((String) temp.get("title")));
                                String url = (StringUtils.trimToEmpty((String) temp.get("url")));
                                String href = (StringUtils.trimToEmpty((String) temp.get("href")));
                                String type = (StringUtils.trimToEmpty((String) temp.get("type")));
                        %>
                        <tr>
                            <td>
                                <input class="text-input large-input" type="text" name="title<%=i+1%>" value="<%=title%>">
                            </td>
                            <td>
                                <img width="100" src="<%=url%>">
                                <input type="file" name="url<%=i+1%>">
                            </td>
                            <td>
                                <input class="text-input large-input" type="text" name="href<%=i+1%>" value="<%=href%>">
                            </td>
                            <td>
                                <select class="text-input large-input" name="type<%=i+1%>">
                                    <option value="1" <%=(StringUtils.equals(type, "1")?" SELECTED":"")%>>显示</option>
                                    <option value="0" <%=(StringUtils.equals(type, "0")?" SELECTED":"")%>>不显示</option>
                                </select>
                            </td>
                            <td>
                                <input class="button" type="button" onclick="deleteRealArt(<%=i+1%>);" value="删除">
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                    <input class="button" type="button" onclick="addRealArt();" value="新增真艺展品">
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