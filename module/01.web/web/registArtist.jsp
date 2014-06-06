<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<%
    //艺术家注册项 json串转换成数组
    JSONArray json = JSONArray.fromObject(artistUserRegistItems);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>艺术家注册</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <script type="text/javascript" src="scripts/registArtist.js"></script>
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
            width : 100px;
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
<div style="background-color: rgb(212, 212, 204);">
    <br>
    <br>
    <form name="registArtistForm" method="post" autocomplete="off" action="registArtist.do?token=<%=token%>"
          enctype="multipart/form-data">
        <div align="center">
            <table>
                <tr>
                    <td class="leftTd">
                        艺术家用户名:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="name" name="name"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("name"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        身份证姓名:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="cert_name"
                               name="certName" value="<%=StringUtils.trimToEmpty((String)request.getAttribute("certName"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        艺术家压题图:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="file" id="title_photo" name="titlePhoto">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        个人照片:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="file" id="head_photo" name="headPhoto">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        邮箱:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="email" name="email"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("email"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        密码:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="password" id="password" name="password">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        确认密码:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="password" id="confirm_password" name="confirmPassword">
                    </td>
                </tr>
                <%
                    for(int i=0;i<json.size();i++)
                    {
                        JSONObject temp = json.getJSONObject(i);
                        String name = (StringUtils.trimToEmpty((String) temp.get("name")));
                        String need = (StringUtils.trimToEmpty((String) temp.get("need")));
                        String type = (StringUtils.trimToEmpty((String) temp.get("type")));
                        type = StringUtils.equals("1", type)?"text":"file";
                %>
                <tr>
                    <td class="leftTd">
                        <%=name%>:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="<%=type%>" id="item<%=i+1%>" name="item<%=i+1%>">
                    </td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td colspan="2">
                        <textarea style="width: 100%;">真艺网专栏开通须知</textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" id="checkbox">我已阅读并同意遵守
                        <input class="button" type="button" value="OK 提交" onclick="registArtist()">
                    </td>
                </tr>
            </table>
        </div>
    </form>
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