<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
<%
    //外层
    outLayer = "注册模块";
    //内层
    inLayer = "批量注册艺术家";
%>
<html>
<head>
    <title>注册艺术家</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <script type="text/javascript" src="scripts/adminRegistArtistFetch.js"></script>
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
                <h3>批量注册艺术家</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <form name="adminRegistArtistFetchForm" method="post" autocomplete="off" action="adminRegistArtistFetch.do?token=<%=token%>"
                          enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td>EXCEL模版</td>
                                <td><input class="button" type="button" onclick="location.href='files/regist-artist-fetch/demo.xls'" value="下载" /></td>
                            </tr>
                            <tr>
                                <td>EXCEL文件</td>
                                <td><input type="file" id="file" name="file"></td>
                            </tr>
                            <tr>
                                <td>提交</td>
                                <td><input class="button" type="button" onclick="registArtistFetch();" value="OK 提交" /></td>
                            </tr>
                            <%
                                String resultFile = (String)request.getAttribute("resultFile");
                                if(StringUtils.isNotBlank(resultFile)){
                            %>
                            <tr>
                                <td>本次结果</td>
                                <td><input class="button" type="button" onclick="location.href='<%=resultFile%>'" value="下载" /></td>
                            </tr>
                            <%
                                }
                            %>
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