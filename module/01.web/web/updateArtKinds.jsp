<%@ page import="com.realart.interfaces.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="artistHeader.jsp" %>
<%
    //外层
    outLayer = "作品模块";
    //内层
    inLayer = "作品分类修改";
    //艺术品所有分类
    String artKinds = StringUtils.trimToEmpty(user.getArtKinds());
    String[] artKindArray = new String[0];
    if(StringUtils.isNotBlank(artKinds)){
        artKindArray = artKinds.split(SymbolInterface.SYMBOL_COMMA);
    }
%>
<html>
<head>
    <title>作品分类修改</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <script type="text/javascript" src="scripts/updateArtKinds.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript">
        //分类个数统计
        var kindCount = <%=artKindArray.length%>;
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
                <h3>作品分类新增</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <form onsubmit="return false;">
                        <table>
                            <tr>
                                <td>
                                    新增分类：
                                    <input id="create_art_kind" class="text-input between-medium-large-input"
                                           type="text" value="">
                                </td>
                                <td>
                                    <input class="button" type="button" value="新增" onclick="createArtKind()">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>

        <div class="content-box">
            <div class="content-box-header">
                <h3>作品分类修改</h3>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <form>
                        <table>
                            <%
                                for(int i=0;i<artKindArray.length;i++){
                            %>
                            <tr>
                                <td class="leftTd">
                                    <input id="kind<%=i+1%>" class="text-input between-medium-large-input"
                                           type="text" value="<%=artKindArray[i]%>">
                                </td>
                                <td class="rightTd">
                                    <input class="button" type="button" value="修改" onclick="updateArtKind('kind<%=i+1%>')">
                                    <input class="button" type="button" value="删除" onclick="deleteArtKind('kind<%=i+1%>')">
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </form>
                    <form name="updateArtKindsForm" method="post" action="updateArtKinds.do" style="display: none;">
                        <input type="hidden" name="token" value="<%=token%>">
                        <textarea id="artKinds" name="artKinds">
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