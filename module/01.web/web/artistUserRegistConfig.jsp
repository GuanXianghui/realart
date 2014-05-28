<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
    <%
        //外层
        outLayer = "注册模块";
        //内层
        inLayer = "艺术家注册配置";
        //艺术家注册项 json串转换成数组
        JSONArray json = JSONArray.fromObject(artistUserRegistItems);
    %>
<html>
<head>
    <title>首页图片管理</title>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/artistUserRegistConfig.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript">
        //注册项数量
        var auriCount = <%=json.size()%>;
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
            <h3>评论用户注册项配置</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <span style="display: none;">
                    <form name="deleteAURIForm" action="deleteAURI.do" method="post">
                        <input type="hidden" name="token" value="<%=token%>">
                        <input type="hidden" name="auri" id="delete_auri">
                    </form>
                    <form name="saveAURIForm" action="saveAURI.do" method="post">
                        <input type="hidden" name="token" value="<%=token%>">
                        <textarea name="auri" id="save_auri"></textarea>
                    </form>
                </span>
                <form>
                    <table id="auri_table">
                        <thead>
                        <tr>
                            <th>注册项</th>
                            <th>是否必输</th>
                            <th>类型</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tr><td>专栏用户名</td><td>必输</td><td>字符串</td><td>不能修改</td></tr>
                        <tr><td>身份证姓名</td><td>必输</td><td>字符串</td><td>不能修改</td></tr>
                        <tr><td>密码</td><td>必输</td><td>字符串</td><td>不能修改</td></tr>
                        <tr><td>确认密码</td><td>必输</td><td>字符串</td><td>不能修改</td></tr>
                        <tr><td>专栏压题图</td><td>必输</td><td>图片/文件</td><td>不能修改</td></tr>
                        <tr><td>个人照片</td><td>必输</td><td>图片/文件</td><td>不能修改</td></tr>
                        <%
                            for(int i=0;i<json.size();i++)
                            {
                                JSONObject temp = json.getJSONObject(i);
                                String name = (StringUtils.trimToEmpty((String) temp.get("name")));
                                String need = (StringUtils.trimToEmpty((String) temp.get("need")));
                                String type = (StringUtils.trimToEmpty((String) temp.get("type")));
                        %>
                        <tr>
                            <td>
                                <input class="text-input large-input" type="text" id="auri_name_<%=i+1%>" value="<%=name%>">
                            </td>
                            <td>
                                <select class="text-input large-input" id="auri_need_<%=i+1%>">
                                    <option value="1"<%="1".equals(need)?" selected":""%>>必输</option>
                                    <option value="2"<%="2".equals(need)?" selected":""%>>非必输</option>
                                </select>
                            </td>
                            <td>
                                <select class="text-input large-input" id="auri_type_<%=i+1%>">
                                    <option value="1"<%="1".equals(type)?" selected":""%>>字符串</option>
                                    <option value="2"<%="2".equals(type)?" selected":""%>>图片/文件</option>
                                </select>
                            </td>
                            <td>
                                <input class="button" type="button" onclick="deleteAURI(<%=i+1%>);" value="删除">
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </form>
                <input class="button" type="button" onclick="addAURI();" value="新增注册项">
                <input class="button" type="button" onclick="saveAURI();" value="保存">
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