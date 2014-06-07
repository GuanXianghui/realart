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
    location.href="artistConsole.jsp";
</script>
<%
        return;
    }
%>

<%
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
    <title>待审核作品页面</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/pinyin.js"></script>
    <script type="text/javascript" src="scripts/needCheckArts.js"></script>
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
        <div>
            <%=artTypeDesc%>
            &lt;<a href="needCheckArts.jsp">全部艺术品</a>&gt;
            &lt;<a href="needCheckArts.jsp?artState=<%=ArtInterface.NEED_CHECK%>">待审核艺术品</a>&gt;
        </div>
        <br>
        <div>
            <%
                for(Art art : arts){
            %>
                <a href="javascript: chooseArt(<%=art.getId()%>)"><%=art.getName()%></a>&nbsp;&nbsp;&nbsp;
            <%
                }
            %>
        </div>
    </div>
    <hr>
    <div style="width: 600px; background-color: rgb(212, 212, 204);" align="left">
        <br>
        <br>
        <div>艺术品详情</div>
        <div>
            <form name="updateArtForm" method="post" autocomplete="off" action="updateArt.do?token=<%=token%>"
                  enctype="multipart/form-data">
                <input type="hidden" name="artId" id="updateArtId">
                <div align="center">
                    <table>
                        <tr>
                            <td class="leftTd">
                                作品名称:
                            </td>
                            <td class="rightTd">
                                <span id="name">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                状态:
                            </td>
                            <td class="rightTd">
                                <span id="state">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                分类:
                            </td>
                            <td class="rightTd">
                                <span id="kind">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                作品主图:
                            </td>
                            <td class="rightTd">
                                <span id="photo">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                作者与作品合影:
                            </td>
                            <td class="rightTd">
                                <span id="photo0">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                作品其他方位图片1:
                            </td>
                            <td class="rightTd">
                                <span id="photo1">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                作品其他方位图片2:
                            </td>
                            <td class="rightTd">
                                <span id="photo2">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                作品其他方位图片3:
                            </td>
                            <td class="rightTd">
                                <span id="photo3">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                作品局部特征图片:
                            </td>
                            <td class="rightTd">
                                <span id="photo4">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                主要工艺:
                            </td>
                            <td class="rightTd">
                                <span id="gongyi">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                造型种类:
                            </td>
                            <td class="rightTd">
                                <span id="type">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                长:
                            </td>
                            <td class="rightTd">
                                <span id="length">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                宽:
                            </td>
                            <td class="rightTd">
                                <span id="width">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                高:
                            </td>
                            <td class="rightTd">
                                <span id="height">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                创作时间:
                            </td>
                            <td class="rightTd">
                                <span id="build_date">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                题款:
                            </td>
                            <td class="rightTd">
                                <span id="title">&lt;请选择待审核艺术品&gt;</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="leftTd">
                                作品介绍:
                            </td>
                            <td class="rightTd">
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