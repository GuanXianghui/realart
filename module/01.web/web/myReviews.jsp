<%@ page import="java.util.List" %>
<%@ page import="com.realart.utils.BaseUtil" %>
<%@ page import="com.realart.entities.Review" %>
<%@ page import="com.realart.dao.ReviewDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="reviewHeader.jsp" %>
<%
    //外层
    outLayer = "评论模块";
    //内层
    inLayer = "我的评论";

    //根据用户Id查询评论
    List<Review> reviews = ReviewDao.queryReviewsByUserId(user.getId());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>我的评论</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/pinyin.js"></script>
    <script type="text/javascript" src="scripts/myReviews.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <!-- ueditor控件 -->
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <%--<script type="text/javascript" charset="utf-8" src="<%=baseUrl%>ueditor/lang/zh-cn/zh-cn.js"></script>--%>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.parse.min.js"></script>
    <script type="text/javascript">
        //评论集合json串
        var reviewJsonStr = "<%=BaseUtil.getJsonArrayFromReviews(reviews).replaceAll("\\\"", "\\\\\\\"")%>";
        //选择评论Id
        var chooseReviewId = 0;
        //评论所有分类
        var reviewKinds = "<%=user.getArtKinds()%>";
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

        <div>
            <div class="content-box">
                <div class="content-box-header">
                    <h3>我的评论</h3>
                </div>
                <div class="content-box-content">
                    <div class="tab-content default-tab">
                        <%
                            for(Review review : reviews){
                        %>
                        <a href="javascript: chooseReview(<%=review.getId()%>)"><%=review.getTitle()%></a>&nbsp;&nbsp;&nbsp;
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="content-box">
                <div class="content-box-header">
                    <h3>评论详情</h3>
                </div>
                <div class="content-box-content">
                    <div class="tab-content default-tab">
                        <form name="updateReviewForm" method="post" autocomplete="off" action="updateReview.do?token=<%=token%>"
                              enctype="multipart/form-data">
                            <input type="hidden" name="reviewId" id="updateReviewId">
                            <div align="center">
                                <table>
                                    <tr>
                                        <td>
                                            标题:
                                        </td>
                                        <td>
                                            <span id="title">&lt;请选择评论&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            归类:
                                        </td>
                                        <td>
                                            <span id="type">&lt;请选择评论&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            图片:
                                        </td>
                                        <td>
                                            <span id="photo">&lt;请选择评论&gt;</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            正文:
                                        </td>
                                        <td>
                                            <div id="content" style="width: 90%;"></div>
                                        </td>
                                    </tr>
                                    <textarea style="display: none;" name="content" id="content_text"></textarea>
                                    <tr>
                                        <td colspan="2" style="text-align: center;">
                                            <input class="button" type="button" value="修改" onclick="updateReview()">
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