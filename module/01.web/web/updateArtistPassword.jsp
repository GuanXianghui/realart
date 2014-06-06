<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="artistHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>艺术家修改密码界面</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <script type="text/javascript" src="scripts/updateArtistPassword.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <style type="text/css">
        body {
            font-family: Arial, Helvetica, sans-serif;
            color: #555;
            background: #ffffff url('images/b.png') top left repeat-y;
            font-size: 12px;
        }
        .leftTd{
            text-align : right;
            width : 150px;
        }
        .rightTd{
            width : 300px;
        }
    </style>
</head>
<body>
<div align="center" style="background: url('images/realart_bg.jpg');
    vertical-align: middle;
    background-size: 100%;
    background-position: center;">
    <img src="images/realart_logo.png" height="100" alt="真艺网">
    <img src="images/realart.png" height="100" alt="真艺网">
</div>
<div style="background-color: rgb(212, 212, 204);" align="center">
    <div style="width: 600px; background-color: rgb(212, 212, 204);" align="left">
        <br>
        <br>
        <form name="updateArtistPasswordForm" method="post" action="updateArtistPassword.do">
            <input type="hidden" name="token" value="<%=token%>">
            <div align="center">
                <table>
                    <tr>
                        <td class="leftTd">
                            *当前密码:
                        </td>
                        <td class="rightTd">
                            <input class="text-input between-medium-large-input" type="password" id="old_password"
                                   name="oldPassword" value="">
                        </td>
                    </tr>
                    <tr>
                        <td class="leftTd">
                            *新密码:
                        </td>
                        <td class="rightTd">
                            <input class="text-input between-medium-large-input" type="password" id="new_password"
                                   name="newPassword" value="">
                        </td>
                    </tr>
                    <tr>
                        <td class="leftTd">
                            *确认密码:
                        </td>
                        <td class="rightTd">
                            <input class="text-input between-medium-large-input" type="password" id="confirm_password"
                                   name="confirmPassword" value="">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;" colspan="2">
                            <input class="button" type="button" value="OK 提交" onclick="updateArtistPassword()">
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
<div align="center" style="background-color: gray;">
    <a href="#"><img width="40" src="images/button/1.jpg" alt=""></a>
    <a href="#"><img width="40" src="images/button/2.jpg" alt=""></a>
    <a href="#"><img width="40" src="images/button/3.jpg" alt=""></a>
    <a href="#"><img width="40" src="images/button/4.jpg" alt=""></a>
    <a href="#"><img width="40" src="images/button/5.jpg" alt=""></a>
    <a href="#"><img width="40" src="images/button/6.jpg" alt=""></a>
    <a href="#"><img width="40" src="images/button/7.jpg" alt=""></a>
    <a href="#"><img width="40" src="images/button/8.jpg" alt=""></a>
    <a href="#"><img width="40" src="images/button/9.jpg" alt=""></a>
    <a href="#"><img width="40" src="images/button/10.jpg" alt=""></a>
</div>
</body>
</html>