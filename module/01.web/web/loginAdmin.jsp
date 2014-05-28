<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<html>
<head>
    <title>真艺网</title>
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/md5.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/loginAdmin.js"></script>
</head>
<body id="login" onkeypress="keyPress(event)">
<div id="login-wrapper" class="png_bg">
    <div id="login-top">
        <h1>真艺网</h1>
        <img id="logo" src="images/realart_logo.png" width="60" alt="真艺网" />
    </div>
    <div id="login-content">
        <form name="loginAdminForm" action="loginAdmin.do" method="post">
            <input type="hidden" name="token" value="<%=token%>">
            <div id="message_id" class="notification information png_bg" style="display: none;">
                <a href="#" class="close">
                    <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
                </a>
                <div id="message_id_content"> 提示信息！ </div>
            </div>
            <%
                if(isAdminLogin){
            %>
            <div align="center">
                <input style="width: 100%" class="button" type="button" value="您已登陆，点击直接进入主页"
                        onclick="location.href=baseUrl+'indexImg.jsp';">
            </div>
            <%
                } else {
            %>
            <p>
                <label>用户名</label>
                <input class="text-input" name="name" type="text" id="name" value="admin"/>
            </p>
            <div class="clear"></div>
            <p>
                <label>密码</label>
                <input class="text-input" name="password" type="password" id="password" value="admin"/>
            </p>
            <div class="clear"></div>
            <div class="clear"></div>
            <p>
                <input class="button" type="button" onclick="login();" value="Log In" />
            </p>
            <%
                }
            %>
        </form>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    if(message != EMPTY){
        showInformation(message);
    }
</script>