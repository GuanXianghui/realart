<%@ page import="com.realart.dao.UserDao" %>
<%@ page import="com.realart.interfaces.UserInterface" %>
<%@ page import="java.util.List" %>
<%@ page import="com.realart.utils.BaseUtil" %>
<%@ page import="com.realart.dao.ArtDao" %>
<%@ page import="com.realart.interfaces.ArtInterface" %>
<%@ page import="com.realart.entities.Art" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
<%
    //外层
    outLayer = "审核模块";
    //内层
    inLayer = "审核艺术品";

    //审核失败原因数组
    String[] reasons = new String[]{};
    if(StringUtils.isNotBlank(checkWrongReason)){
        reasons = checkWrongReason.split("\\|");
    }

    //修改艺术品状态
    String artId = request.getParameter("artId");
    String state = request.getParameter("state");
    String reason = request.getParameter("reason");
    if(StringUtils.isNotBlank(artId) && StringUtils.isNotBlank(state)){
        Art art = ArtDao.getArtById(Integer.parseInt(artId));
        art.setState(Integer.parseInt(state));
        for(int i=0;i<reasons.length;i++){
            if(StringUtils.equals(StringUtils.EMPTY+(i+1), reason)){
                art.setReason(reasons[i]);
            }
        }
        ArtDao.updateState(art);
%>
<script type="text/javascript">
    message = "审核艺术品成功";
</script>
<%
    }

    //已审核通过的艺术家
    List<User> artistUsers = UserDao.queryUsersByUserTypeAndState(UserInterface.USER_TYPE_ARTIST, UserInterface.USER_STATE_NORMAL);
    //待审核的艺术品
    List<Art> arts = ArtDao.queryArtsByUserIdAndState(-1, ArtInterface.NEED_CHECK);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>评论页面</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/pinyin.js"></script>
    <script type="text/javascript" src="scripts/checkUpArt.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript">
        //用户集合json串
        var userJsonStr = "<%=BaseUtil.getJsonArrayFromUsers(artistUsers)%>";
        //艺术品集合json串
        var artJsonStr = "<%=BaseUtil.getJsonArrayFromArts(arts)%>";
        //选择艺术品Id
        var chooseArtId = 0;
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
                    <h3>待审核艺术品</h3>
                </div>
                <div class="content-box-content">
                    <div class="tab-content default-tab">
                        <%
                            int artCount = 0;
                            for(Art art : arts){
                                artCount ++;
                        %>
                        <a href="javascript: chooseArt(<%=art.getId()%>)"><%=art.getName()%></a>&nbsp;&nbsp;&nbsp;
                        <%
                            }
                            if(artCount == 0){
                                out.print("暂无待审核艺术品");
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <div class="column-right">
            <div class="content-box">
                <div class="content-box-header">
                    <h3>艺术品信息</h3>
                </div>
                <div class="content-box-content">
                    <div class="tab-content default-tab">
                        <form>
                            <table>

                                <tr>
                                    <td>艺术家用户名</td>
                                    <td><span id="userName"></span></td>
                                </tr>
                                <tr>
                                    <td>作品名称</td>
                                    <td><span id="name"></span></td>
                                </tr>
                                <tr>
                                    <td>状态</td>
                                    <td><span id="state"></span></td>
                                </tr>
                                <tr>
                                    <td>作品主图</td>
                                    <td><span id="photo"></span></td>
                                </tr>
                                <tr>
                                    <td>作者与作品合影</td>
                                    <td><span id="photo0"></span></td>
                                </tr>
                                <tr>
                                    <td>作品其他方位图片1</td>
                                    <td><span id="photo1"></span></td>
                                </tr>
                                <tr>
                                    <td>作品其他方位图片2</td>
                                    <td><span id="photo2"></span></td>
                                </tr>
                                <tr>
                                    <td>作品其他方位图片3</td>
                                    <td><span id="photo3"></span></td>
                                </tr>
                                <tr>
                                    <td>作品局部特征图片</td>
                                    <td><span id="photo4"></span></td>
                                </tr>
                                <tr>
                                    <td>主要工艺</td>
                                    <td><span id="gongyi"></span></td>
                                </tr>
                                <tr>
                                    <td>造型种类</td>
                                    <td><span id="type"></span></td>
                                </tr>
                                <tr>
                                    <td>长</td>
                                    <td><span id="length"></span></td>
                                </tr>
                                <tr>
                                    <td>宽</td>
                                    <td><span id="width"></span></td>
                                </tr>
                                <tr>
                                    <td>高</td>
                                    <td><span id="height"></span></td>
                                </tr>
                                <tr>
                                    <td>创作时间</td>
                                    <td><span id="build_date"></span></td>
                                </tr>
                                <tr>
                                    <td>题款</td>
                                    <td><span id="title"></span></td>
                                </tr>
                                <tr>
                                    <td>作品介绍</td>
                                    <td><span id="introduction"></span></td>
                                </tr>
                                <tr>
                                    <td>失败原因</td>
                                    <td>
                                        <select class="text-input medium-input" id="reason">
                                            <%
                                                int count = 0;
                                                for(String temp : reasons){
                                                    count ++;
                                            %>
                                            <option value="<%=count%>"><%=temp%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <input class="button" type="button" onclick="checkSuccess();" value="审核通过">
                                        <input class="button" type="button" onclick="checkFailed();" value="审核不通过">
                                    </td>
                                </tr>
                            </table>
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