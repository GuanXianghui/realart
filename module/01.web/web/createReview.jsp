<%@ page import="com.realart.interfaces.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="reviewHeader.jsp" %>
<%
    //外层
    outLayer = "评论模块";
    //内层
    inLayer = "发表评论";
    //艺术品所有分类
    String reviewKinds = StringUtils.trimToEmpty(user.getArtKinds());
    String[] reviewKindArray = new String[0];
    if(StringUtils.isNotBlank(reviewKinds)){
        reviewKindArray = reviewKinds.split(SymbolInterface.SYMBOL_COMMA);
    }
%>
<html>
<head>
    <title>评论分类修改</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <%--<script type="text/javascript" charset="utf-8" src="<%=baseUrl%>ueditor/lang/zh-cn/zh-cn.js"></script>--%>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.parse.min.js"></script>
    <script type="text/javascript" src="scripts/createReview.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript">
        //分类个数统计
        var kindCount = <%=reviewKindArray.length%>;
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
                Hello, [<%=user.getName()%>],真艺网欢迎您！
                <br/>
                <br/>
                <a href="javascript: logOut()" title="Sign Out">退出</a>
            </div>
            <%@ include file="reviewLayers.jsp" %>
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
                <h3>评论分类修改</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <form name="createReviewForm" method="post" autocomplete="off" action="createReview.do?token=<%=token%>"
                          enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td>
                                    归类:
                                </td>
                                <td>
                                    <select class="text-input medium-input" name="type" id="type">
                                        <option value="">暂不分类</option>
                                        <%
                                            for(String kind : reviewKindArray){
                                        %>
                                        <option value="<%=kind%>"<%=StringUtils.equals(kind, (String)request.getAttribute("type"))?" SELECTED":""%>><%=kind%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    标题:
                                </td>
                                <td>
                                    <input class="text-input between-medium-large-input" type="text" id="title" name="title"
                                           value="<%=StringUtils.trimToEmpty((String)request.getAttribute("title"))%>">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    图片:
                                </td>
                                <td>
                                    <input class="text-input between-medium-large-input" type="file" id="photo" name="photo">
                                </td>
                            </tr>
                            <textarea style="display: none;" id="content" name="content"></textarea>
                            <tr>
                                <td colspan="2">
                                    <script id="editor" type="text/plain"></script>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input class="button" type="button" value="评论" onclick="createReview()">
                                </td>
                            </tr>
                        </table>
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