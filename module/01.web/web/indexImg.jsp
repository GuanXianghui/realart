<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
    <%
        //外层
        outLayer = "首页模块";
        //内层
        inLayer = "首页图片管理";
    %>
<html>
<head>
    <title>首页图片管理</title>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/indexImg.js"></script>
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
    <div class="column-left">
        <div class="content-box">
            <div class="content-box-header">
                <h3>底图信息</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <table>
                        <tr style="border: 1px solid white;">
                            <td id="before_upload_bg_img_td" style="text-align: center;">
                                <%
                                    if(StringUtils.isBlank(indexBgImg)){
                                %>
                                暂无底图
                                <%
                                }else {
                                %>
                                <img src="<%=indexBgImg%>" width="108px"/>
                                <%
                                    }
                                %>
                                <input class="button" type="button" onclick="beforeUploadBgImg();" value="修改底图">
                            </td>
                            <td id="upload_bg_img_td" style="display: none;">
                                <form name="uploadBgImgForm" method="post" autocomplete="off"
                                      action="<%=baseUrl%>uploadBgImg.do" enctype="multipart/form-data">
                                    <input type="file" name="bgImg">
                                    <input class="button" type="button" onclick="uploadBgImg();" value="上传">
                                    <input class="button" type="button" onclick="cancelUploadBgImg();" value="取消">
                                </form>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <div class="content-box">
            <div class="content-box-header">
                <h3>真艺网图信息</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <table>
                        <tr style="border: 1px solid white;">
                            <td id="before_upload_realart_img_td" style="text-align: center;">
                                <%
                                    if(StringUtils.isBlank(indexRealartImg)){
                                %>
                                暂无真艺网图
                                <%
                                }else {
                                %>
                                <img src="<%=indexRealartImg%>" width="<%=indexRealartWidth%>" height="<%=indexRealartHeight%>"/>
                                <%
                                    }
                                %>
                                <input class="button" type="button" onclick="beforeUploadRealartImg();" value="修改真艺网图">
                            </td>
                            <td id="upload_realart_img_td" style="display: none;">
                                <form name="uploadRealartImgForm" method="post" autocomplete="off"
                                      action="<%=baseUrl%>uploadRealartImg.do" enctype="multipart/form-data">
                                    <table>
                                        <tr>
                                            <td>真艺网图</td>
                                            <td><input type="file" name="realartImg"></td>
                                        </tr>
                                        <tr>
                                            <td>宽度</td>
                                            <td>
                                                <input class="text-input medium-input" type="text"
                                                       id="index_realart_width"
                                                       name="indexRealartWidth"
                                                       value="<%=StringUtils.isNotBlank(indexRealartWidth)?indexRealartWidth:""%>"
                                                        >
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>高度</td>
                                            <td><input class="text-input medium-input" type="text"
                                                       id="index_realart_height"
                                                       name="indexRealartHeight"
                                                       value="<%=StringUtils.isNotBlank(indexRealartHeight)?indexRealartHeight:""%>"
                                                    >
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <input class="button" type="button" onclick="uploadRealartImg();" value="上传">
                                                <input class="button" type="button" onclick="cancelUploadRealartImg();" value="取消">
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="column-right">
        <div class="content-box">
            <div class="content-box-header">
                <h3>logo信息</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <table>
                        <tr style="border: 1px solid white;">
                            <td id="before_upload_logo_img_td" style="text-align: center;">
                                <%
                                    if(StringUtils.isBlank(indexLogoImg)){
                                %>
                                暂无Logo
                                <%
                                }else {
                                %>
                                <img src="<%=indexLogoImg%>" width="<%=indexLogoWidth%>" height="<%=indexLogoHeight%>"/>
                                <%
                                    }
                                %>
                                <input class="button" type="button" onclick="beforeUploadLogoImg();" value="修改Logo">
                            </td>
                            <td id="upload_logo_img_td" style="display: none;">
                                <form name="uploadLogoImgForm" method="post" autocomplete="off"
                                      action="<%=baseUrl%>uploadLogoImg.do" enctype="multipart/form-data">
                                    <table>
                                        <tr>
                                            <td>logo</td>
                                            <td><input type="file" name="logoImg"></td>
                                        </tr>
                                        <tr>
                                            <td>宽度</td>
                                            <td><input class="text-input medium-input" type="text"
                                                       id="index_logo_width"
                                                       name="indexLogoWidth"
                                                       value="<%=StringUtils.isNotBlank(indexLogoWidth)?indexLogoWidth:""%>"
                                                    >
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>高度</td>
                                            <td><input class="text-input medium-input" type="text"
                                                       id="index_logo_height"
                                                       name="indexLogoHeight"
                                                       value="<%=StringUtils.isNotBlank(indexLogoHeight)?indexLogoHeight:""%>"
                                                    >
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <input class="button" type="button" onclick="uploadLogoImg();" value="上传">
                                                <input class="button" type="button" onclick="cancelUploadLogoImg();" value="取消">
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </td>
                        </tr>
                    </table>
                </div>
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