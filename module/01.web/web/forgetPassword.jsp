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
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/forgetPassword.js"></script>
</head>
<body id="login" onkeypress="keyPress(event)">
<div id="login-wrapper" class="png_bg">
    <div id="login-top">
        <h1>真艺网</h1>
        <img id="logo" src="images/realart_logo.png" width="60" alt="真艺网" />
    </div>
    <div id="login-content">
        <form>
            <input type="hidden" name="token" value="<%=token%>">
            <div id="message_id" class="notification information png_bg" style="display: none;">
                <a href="#" class="close">
                    <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
                </a>
                <div id="message_id_content"> 提示信息！ </div>
            </div>
            <div id="init_div">
                <p>
                    <label>用户名</label>
                    <input class="text-input" name="name" type="text" id="name"/>
                </p>
                <div class="clear"></div>
                <p>
                    <label>邮箱</label>
                    <input class="text-input" name="email" type="text" id="email"/>
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
                    <input class="button" type="button" onclick="forgetPassword();" value="忘记密码" />
                </p>
            </div>
            <p style="text-align: center; display: none;" id="success_p">
                <input class="button" type="button" onclick="location.href='login.jsp';" value="返回登录页面" />
            </p>
        </form>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    showInformation("忘记密码后将发送邮件到您的邮箱中！");
    if(message != EMPTY){
        showInformation(message);
    }
</script>