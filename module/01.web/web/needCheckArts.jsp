<%@ page import="java.util.List" %>
<%@ page import="com.realart.utils.BaseUtil" %>
<%@ page import="com.realart.dao.ArtDao" %>
<%@ page import="com.realart.interfaces.ArtInterface" %>
<%@ page import="com.realart.entities.Art" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="artistHeader.jsp" %>
<%
    if(user.getState() != UserInterface.USER_STATE_NORMAL){
%>
<script type="text/javascript">
    alert("您的艺术家资料未审核通过");
    location.href="updateArtistInfo.jsp";
</script>
<%
        return;
    }
%>

<%
    //外层
    outLayer = "作品模块";
    //内层
    inLayer = "我的作品";

    //待审核的艺术品
    List<Art> arts = ArtDao.queryArtsByUserIdAndState(user.getId(), ArtInterface.NEED_CHECK);
    List<Art> arts2 = ArtDao.queryArtsByUserIdAndState(user.getId(), ArtInterface.CHECK_FAILED);
    arts.addAll(arts2);
    String artTypeDesc = "待审核艺术品";
    String artState = StringUtils.trimToEmpty(request.getParameter("artState"));
    if(!StringUtils.equals(artState, StringUtils.EMPTY + ArtInterface.NEED_CHECK)){
        List<Art> arts3 = ArtDao.queryArtsByUserIdAndState(user.getId(), ArtInterface.NORMAL);
        arts.addAll(arts3);
        artTypeDesc = "全部艺术品";
    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>我的作品</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/pinyin.js"></script>
    <script type="text/javascript" src="scripts/needCheckArts.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript">
        //艺术品集合json串
        var artJsonStr = "<%=BaseUtil.getJsonArrayFromArts(arts)%>";
        //选择艺术品Id
        var chooseArtId = 0;
        //艺术品所有分类
        var artKinds = "<%=user.getArtKinds()%>";
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
                Hello, [<%=adminUserName%>],真艺网欢迎您！
                <br/>
                <br/>
                <a href="javascript: logOut()" title="Sign Out">退出</a>
            </div>
            <%@ include file="artistLayers.jsp" %>
        </div>
    </div>
    <div id="main-content">

        <ul class="shortcut-buttons-set">
            <li>
                <a class="shortcut-button" href="needCheckArts.jsp">
                    <span>
                        <img src="images/icons/image_add_48.png" alt="icon" /><br />全部艺术品
                    </span>
                </a>
            </li>
            <li>
                <a class="shortcut-button" href="needCheckArts.jsp?artState=<%=ArtInterface.NEED_CHECK%>">
                    <span>
                        <img src="images/icons/paper_content_pencil_48.png" alt="icon" /><br />待审核艺术品
                    </span>
                </a>
            </li>
        </ul>

        <div class="clear"></div>

        <div id="message_id" class="notification information png_bg" style="display: none;">
            <a href="#" class="close">
                <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
            </a>

            <div id="message_id_content"> 提示信息！</div>
        </div>

        <div class="column-left">
            <div class="content-box">
                <div class="content-box-header">
                    <h3><%=artTypeDesc%></h3>
                </div>
                <div class="content-box-content">
                    <div class="tab-content default-tab">
                        <%
                            for(Art art : arts){
                        %>
                        <a href="javascript: chooseArt(<%=art.getId()%>)"><%=art.getName()%></a>&nbsp;&nbsp;&nbsp;
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <div class="column-right">
            <div class="content-box">
                <div class="content-box-header">
                    <h3>艺术品详情</h3>
                </div>
                <div class="content-box-content">
                    <div class="tab-content default-tab">
                        <form name="updateArtForm" method="post" autocomplete="off" action="updateArt.do?token=<%=token%>"
                              enctype="multipart/form-data">
                            <input type="hidden" name="artId" id="updateArtId">
                            <div align="center">
                                <table>
                                    <tr>
                                        <td>
                                            作品名称:
                                        </td>
                                        <td>
                                            <span id="name">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            状态:
                                        </td>
                                        <td>
                                            <span id="state">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            分类:
                                        </td>
                                        <td>
                                            <span id="kind">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            作品主图:
                                        </td>
                                        <td>
                                            <span id="photo">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            作者与作品合影:
                                        </td>
                                        <td>
                                            <span id="photo0">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            作品其他方位图片1:
                                        </td>
                                        <td>
                                            <span id="photo1">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            作品其他方位图片2:
                                        </td>
                                        <td>
                                            <span id="photo2">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            作品其他方位图片3:
                                        </td>
                                        <td>
                                            <span id="photo3">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            作品局部特征图片:
                                        </td>
                                        <td>
                                            <span id="photo4">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            主要工艺:
                                        </td>
                                        <td>
                                            <span id="gongyi">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            造型种类:
                                        </td>
                                        <td>
                                            <span id="type">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            长:
                                        </td>
                                        <td>
                                            <span id="length">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            宽:
                                        </td>
                                        <td>
                                            <span id="width">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            高:
                                        </td>
                                        <td>
                                            <span id="height">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            创作时间:
                                        </td>
                                        <td>
                                            <span id="build_date">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            题款:
                                        </td>
                                        <td>
                                            <span id="title">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            作品介绍:
                                        </td>
                                        <td>
                                            <span id="introduction">&lt;请选择待审核艺术品&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="text-align: center;">
                                            <input class="button" type="button" value="修改" onclick="updateArt()">
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </form>
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