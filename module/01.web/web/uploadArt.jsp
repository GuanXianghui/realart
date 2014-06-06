<%@ page import="com.realart.dao.QrCodeDao" %>
<%@ page import="com.realart.entities.QrCode" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="artistHeader.jsp" %>
<%
    if(user.getState() != UserInterface.USER_STATE_NORMAL){
%>
<script type="text/javascript">
    alert("您的艺术家资料未审核通过");
    location.href="/artistConsole.jsp";
</script>
<%
        return;
    }
    //qr为空
    if(StringUtils.isBlank(qr)){
%>
<script type="text/javascript">
    alert("必须扫描二维码备案艺术品");
    location.href="/artistConsole.jsp";
</script>
<%
        return;
    }

    //二维码
    QrCode qrCode = QrCodeDao.getQrCodeByUuid(qr);
    //二维码为空 或者 状态不为未被使用
    if(qrCode == null || qrCode.getState() != QrCodeInterface.STATE_NOT_USE){
%>
<script type="text/javascript">
    alert("序列号已失效");
    location.href="/artistConsole.jsp";
</script>
<%
        return;
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>作品备案</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <script type="text/javascript" src="scripts/uploadArt.js"></script>
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
    <form name="uploadArtForm" method="post" autocomplete="off" action="uploadArt.do?token=<%=token%>&qr=<%=qr%>"
          enctype="multipart/form-data">
        <div align="center">
            <table>
                <tr>
                    <td class="leftTd">
                        序列号:
                    </td>
                    <td class="rightTd">
                        <%=qr%>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        二维码:
                    </td>
                    <td class="rightTd">
                        <img src="<%=qrCode.getImgPath()%>" width="100">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品名称:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="name" name="name"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("name"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品主图:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="file" id="photo" name="photo">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作者与作品合影:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="file" id="photo0" name="photo0">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品其他方位图片1:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="file" id="photo1" name="photo1">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品其他方位图片2:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="file" id="photo2" name="photo2">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品其他方位图片3:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="file" id="photo3" name="photo3">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品其他方位图片4:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="file" id="photo4" name="photo4">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        主要工艺:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="gongyi" name="gongyi"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("gongyi"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        造型种类:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="type" name="type"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("type"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        长:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="length" name="length"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("length"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        宽:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="width" name="width"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("width"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        高:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="height" name="height"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("height"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        创作时间:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="build_date" name="buildDate"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("buildDate"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        题款:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="title" name="title"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("title"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        作品介绍:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="introduction" name="introduction"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("introduction"))%>">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input class="button" type="button" value="OK 提交" onclick="uploadArt()">
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <br>
    <br>
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