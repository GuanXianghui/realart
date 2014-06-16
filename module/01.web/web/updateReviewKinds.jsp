<%@ page import="com.realart.interfaces.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="reviewHeader.jsp" %>
<%
    //外层
    outLayer = "评论模块";
    //内层
    inLayer = "评论分类修改";
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
    <script type="text/javascript" src="scripts/md5.js"></script>
    <script type="text/javascript" src="scripts/updateReviewKinds.js"></script>
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
                <h3>评论分类新增</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <form onsubmit="return false;">
                        <table>
                            <tr>
                                <td>
                                    新增分类：
                                    <input id="create_review_kind" class="text-input between-medium-large-input"
                                           type="text" value="">
                                </td>
                                <td>
                                    <input class="button" type="button" value="新增" onclick="createReviewKind()">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>

        <div class="content-box">
            <div class="content-box-header">
                <h3>评论分类修改</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <form>
                        <table>
                            <%
                                for(int i=0;i<reviewKindArray.length;i++){
                            %>
                            <tr>
                                <td class="leftTd">
                                    <input id="kind<%=i+1%>" class="text-input between-medium-large-input"
                                           type="text" value="<%=reviewKindArray[i]%>">
                                </td>
                                <td class="rightTd">
                                    <input class="button" type="button" value="修改" onclick="updateReviewKind('kind<%=i+1%>')">
                                    <input class="button" type="button" value="删除" onclick="deleteReviewKind('kind<%=i+1%>')">
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </form>
                    <form name="updateReviewKindsForm" method="post" action="updateReviewKinds.do" style="display: none;">
                        <input type="hidden" name="token" value="<%=token%>">
                        <textarea id="reviewKinds" name="reviewKinds">
                        </textarea>
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