<%@ page import="com.realart.utils.BaseUtil" %>
<%@ page import="com.realart.dao.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<%
    boolean isError = false;
    String errorMessage = StringUtils.EMPTY;
    String name = request.getParameter("name");
    String userToken = request.getParameter("userToken");
    if(StringUtils.isBlank(name) || StringUtils.isBlank(userToken)){
        isError = true;
        errorMessage = "你的操作有误！";
    } else {
        //判名字存在 管理员名字 或者 普通用户
        if(!BaseUtil.isAdminName(name) && !UserDao.isNameExist(name)){
            isError = true;
            errorMessage = "该用户名不存在！";
        } else {
            /**
             * 用户id
             * 管理员用户 用户id设置为0
             * 其他用户 用户id即userId
             */
            int userId = 0;
            //判管理员用户
            if(BaseUtil.isAdminName(name)){
                userId = 0;
            } else {//判其他用户
                //根据姓名查用户
                user = UserDao.getUserByName(name);
                userId = user.getId();
            }
            if(!BaseUtil.checkUserToken(userId, userToken)){
                isError = true;
                errorMessage = "您的访问已经失效！";
            }
        }
    }
%>
<html>
<head>
    <title>真艺网</title>
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/md5.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/resetPassword.js"></script>
</head>
<body id="login" onkeypress="keyPress(event)">
<div id="login-wrapper" class="png_bg">
    <div id="login-top">
        <h1>真艺网</h1>
        <img id="logo" src="images/realart_logo.png" width="60" alt="真艺网" />
    </div>
    <div id="login-content">
        <form name="resetPasswordForm" action="resetPassword.do" method="post">
            <input type="hidden" name="token" value="<%=token%>">
            <input type="hidden" name="name" value="<%=name%>">
            <input type="hidden" name="userToken" value="<%=userToken%>">
            <div id="message_id" class="notification information png_bg" style="display: none;">
                <a href="#" class="close">
                    <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
                </a>
                <div id="message_id_content"> 提示信息！ </div>
            </div>
            <%
                if(isError){
            %>
            <div align="center">
                <input class="button" type="button" onclick="location.href='forgetPassword.jsp'" value="返回忘记密码页面" />
            </div>
            <%
                } else {
            %>
            <p>
                <label>新密码</label>
                <input class="text-input" name="password" type="password" id="password"/>
            </p>
            <div class="clear"></div>
            <p>
                <label>确认密码</label>
                <input class="text-input" name="confirmPassword" type="password" id="confirm_password"/>
            </p>
            <div class="clear"></div>
            <p>
                <label>验证码</label>
                <input class="text-input medium-input" name="securityCode" type="text" id="securityCode"/>
                <img src="securityCodeImage.do" onclick="changeSecurityCode(this)"
                     style="cursor:pointer; height: 25px;" alt="看不清，换一张"/>
            </p>
            <div class="clear"></div>
            <div class="clear"></div>
            <p style="text-align: center">
                <input class="button" type="button" onclick="resetPassword();" value="重置密码" />
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
    <%
        if(isError){
    %>
    showInformation("<%=errorMessage%>");
    <%
        }
    %>
    if(message != EMPTY){
        showInformation(message);
    }
</script>