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
</head>
<body>
<div align="center" style="background: url('images/realart_bg.jpg');
    vertical-align: middle;
    background-size: 100%;
    background-position: center;">
    <img src="images/realart_logo.png" height="100" alt="真艺网">
    <img src="images/realart.png" height="100" alt="真艺网">
</div>
<div>
    <form name="uploadArtForm" method="post" autocomplete="off" action="uploadArt.do?token=<%=token%>&qr=<%=qr%>"
          enctype="multipart/form-data">
        <div align="center">作品备案界面</div>
        <div>序列号:<%=qr%></div>
        <div>二维码:<img src="<%=qrCode.getImgPath()%>" width="100"></div>
        <div>作品名称<input type="text" id="name" name="name" value="<%=StringUtils.trimToEmpty((String)request.getAttribute("name"))%>"></div>
        <div>作品主图<input type="file" id="photo" name="photo"></div>
        <div>作者与作品合影<input type="file" id="photo0" name="photo0"></div>
        <div>作品其他方位图片1<input type="file" id="photo1" name="photo1"></div>
        <div>作品其他方位图片2<input type="file" id="photo2" name="photo2"></div>
        <div>作品其他方位图片3<input type="file" id="photo3" name="photo3"></div>
        <div>作品局部特征图片<input type="file" id="photo4" name="photo4"></div>
        <div>主要工艺<input type="text" id="gongyi" name="gongyi" value="<%=StringUtils.trimToEmpty((String)request.getAttribute("gongyi"))%>"></div>
        <div>造型种类<input type="text" id="type" name="type" value="<%=StringUtils.trimToEmpty((String)request.getAttribute("type"))%>"></div>
        <div>长<input type="text" id="length" name="length" value="<%=StringUtils.trimToEmpty((String)request.getAttribute("length"))%>"></div>
        <div>宽<input type="text" id="width" name="width" value="<%=StringUtils.trimToEmpty((String)request.getAttribute("width"))%>"></div>
        <div>高<input type="text" id="height" name="height" value="<%=StringUtils.trimToEmpty((String)request.getAttribute("height"))%>"></div>
        <div>创作时间<input type="text" id="build_date" name="buildDate" value="<%=StringUtils.trimToEmpty((String)request.getAttribute("buildDate"))%>"></div>
        <div>题款<input type="text" id="title" name="title" value="<%=StringUtils.trimToEmpty((String)request.getAttribute("title"))%>"></div>
        <div>作品介绍<input type="text" id="introduction" name="introduction" value="<%=StringUtils.trimToEmpty((String)request.getAttribute("introduction"))%>"></div>
        <div>
            <input type="button" value="OK 提交" onclick="uploadArt()">
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