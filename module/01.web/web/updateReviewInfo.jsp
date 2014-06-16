<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="reviewHeader.jsp" %>
<%
    //外层
    outLayer = "用户模块";
    //内层
    inLayer = "资料修改";
    //评论用户注册项 json串转换成数组
    JSONArray json = JSONArray.fromObject(reviewUserRegistItems);
    //用户信息转json对象
    JSONObject userInfo = JSONObject.fromObject(user.getInfo());
%>
<html>
<head>
    <title>资料修改</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <script type="text/javascript" src="scripts/updateReviewInfo.js"></script>
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
                <h3>资料修改</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <form name="updateReviewInfoForm" method="post" autocomplete="off" action="updateReviewInfo.do?token=<%=token%>"
                          enctype="multipart/form-data">
                        <table id="guide_table">
                            <thead>
                            <tr>
                                <th width="10%">字段</th>
                                <th>值</th>
                            </tr>
                            </thead>
                            <tr>
                                <td>
                                    评论用户名
                                </td>
                                <td>
                                    <%=user.getName()%>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    身份证姓名
                                </td>
                                <td>
                                    <input class="text-input medium-input" type="text" id="cert_name"
                                           name="certName" value="<%=user.getCertName()%>">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    艺术家压题图
                                </td>
                                <td>
                                    <%
                                        if(StringUtils.isNotBlank(user.getTitlePhoto())){
                                    %>
                                    <img src="/<%=user.getTitlePhoto()%>" height="100">
                                    <%
                                        }
                                    %>
                                    <input type="file" id="title_photo" name="titlePhoto">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    个人照片
                                </td>
                                <td>
                                    <%
                                        if(StringUtils.isNotBlank(user.getHeadPhoto())){
                                    %>
                                    <img src="/<%=user.getHeadPhoto()%>" height="100">
                                    <%
                                        }
                                    %>
                                    <input type="file" id="head_photo" name="headPhoto">
                                </td>
                            </tr>
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
                                    <%=name%>:
                                </td>
                                <td>
                                    <%
                                        if(Integer.parseInt(type) == UserInterface.COLUMN_TYPE_STRING){
                                    %>
                                    <input class="text-input medium-input" type="text" id="item<%=i+1%>"
                                           name="item<%=i+1%>" value="<%=StringUtils.trimToEmpty((String)userInfo.get(name))%>">
                                    <%
                                    } else {
                                    %>
                                    <%
                                        if(StringUtils.isNotBlank(userInfo.getString(name))){
                                    %>
                                    <img src="/<%=userInfo.getString(name)%>" height="100">
                                    <%
                                        }
                                    %>
                                    <input type="file" id="item<%=i+1%>" name="item<%=i+1%>">
                                    <%
                                        }
                                    %>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                        <input class="button" type="button" value="OK 提交" onclick="updateReviewInfo()">
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