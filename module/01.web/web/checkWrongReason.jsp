<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
    <%
        //外层
        outLayer = "审核模块";
        //内层
        inLayer = "审核失败原因维护";
        //审核失败原因数组
        String[] reasons = new String[]{};
        if(StringUtils.isNotBlank(checkWrongReason)){
            reasons = checkWrongReason.split("\\|");
        }
    %>
<html>
<head>
    <title>首页图片管理</title>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/checkWrongReason.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript">
        //当前原因个数
        var cwrCount = <%=reasons.length%>;
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
            <h3>审核失败原因维护</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <span style="display: none;">
                    <form name="deleteCWRForm" action="deleteCWR.do" method="post">
                        <input type="hidden" name="token" value="<%=token%>">
                        <input type="hidden" name="cwr" id="delete_cwr">
                    </form>
                    <form name="saveCWRForm" action="saveCWR.do" method="post">
                        <input type="hidden" name="token" value="<%=token%>">
                        <textarea name="cwr" id="save_cwr"></textarea>
                    </form>
                </span>
                <form>
                    <table id="cwr_table">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>失败原因</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <%
                            for(int i=0;i<reasons.length;i++)
                            {
                        %>
                        <tr>
                            <td>
                                <%=i+1%>
                            </td>
                            <td>
                                <input class="text-input large-input" type="text" id="cwr_<%=i+1%>" value="<%=reasons[i]%>">
                            </td>
                            <td>
                                <input class="button" type="button" onclick="deleteCWR(<%=i+1%>);" value="删除">
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </form>
                <input class="button" type="button" onclick="addCWR();" value="新增失败原因">
                <input class="button" type="button" onclick="saveCWR();" value="保存">
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