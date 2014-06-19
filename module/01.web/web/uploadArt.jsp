<html>
<head>
    <%@ page import="com.realart.dao.QrCodeDao" %>
    <%@ page import="com.realart.entities.QrCode" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ include file="artistHeader.jsp" %>
    <%
        if(user.getState() != UserInterface.USER_STATE_NORMAL){
    %>
    <script type="text/javascript">
        alert("您的艺术家资料未审核通过");
        location.href="/updateArtistInfo.jsp";
    </script>
    <%
            return;
        }
        //qr为空
        if(StringUtils.isBlank(qr)){
    %>
    <script type="text/javascript">
        alert("必须扫描二维码备案艺术品");
        location.href="/updateArtistInfo.jsp";
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
        location.href="/updateArtistInfo.jsp";
    </script>
    <%
            return;
        }

        //外层
        outLayer = "备案模块";
        //内层
        inLayer = "作品备案";
    %>
    <title>资料修改</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <script type="text/javascript" src="scripts/uploadArt.js"></script>
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
            <%@ include file="artistLayers.jsp" %>
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
                <h3>作品备案</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <form name="uploadArtForm" method="post" autocomplete="off" action="uploadArt.do?token=<%=token%>&qr=<%=qr%>"
                          enctype="multipart/form-data">
                        <div align="center">
                            <table>
                                <tr>
                                    <td>
                                        序列号:
                                    </td>
                                    <td>
                                        <%=qr%>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        二维码:
                                    </td>
                                    <td>
                                        <img src="<%=qrCode.getImgPath()%>" width="100">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        作品名称:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="text" id="name" name="name"
                                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("name"))%>">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        作品主图:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="file" id="photo" name="photo">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        作者与作品合影:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="file" id="photo0" name="photo0">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        作品其他方位图片1:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="file" id="photo1" name="photo1">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        作品其他方位图片2:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="file" id="photo2" name="photo2">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        作品其他方位图片3:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="file" id="photo3" name="photo3">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        作品其他方位图片4:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="file" id="photo4" name="photo4">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        主要工艺:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="text" id="gongyi" name="gongyi"
                                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("gongyi"))%>">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        造型种类:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="text" id="type" name="type"
                                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("type"))%>">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        长:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="text" id="length" name="length"
                                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("length"))%>">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        宽:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="text" id="width" name="width"
                                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("width"))%>">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        高:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="text" id="height" name="height"
                                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("height"))%>">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        创作时间:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="text" id="build_date" name="buildDate"
                                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("buildDate"))%>">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        题款:
                                    </td>
                                    <td>
                                        <input class="text-input between-medium-large-input" type="text" id="title" name="title"
                                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("title"))%>">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        作品介绍:
                                    </td>
                                    <td>
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