<%@ page import="com.realart.entities.Review" %>
<%@ page import="com.realart.dao.ReviewDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<%
    int id;
    try{
        id = Integer.parseInt(request.getParameter("id"));
    } catch (Exception e){
        response.sendRedirect("index.jsp");
        return;
    }
    Review review = ReviewDao.getReviewById(id);
    if(review == null){
        response.sendRedirect("index.jsp");
        return;
    }
    if(user != null && user.getId() == review.getUserId() && StringUtils.isNotBlank(request.getParameter("setTop"))){
        int setTop;
        try{
            setTop = Integer.parseInt(request.getParameter("setTop"));
        } catch (Exception e){
            response.sendRedirect("index.jsp");
            return;
        }
        if(1 == setTop){
            review.setSelfTop(true);
        } else if(0 == setTop){
            review.setSelfTop(false);
        } else {
            response.sendRedirect("index.jsp");
            return;
        }
        //更改置顶
        ReviewDao.updateLocationBit(review);
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>评论页面</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <%--<script type="text/javascript" charset="utf-8" src="<%=baseUrl%>ueditor/lang/zh-cn/zh-cn.js"></script>--%>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.parse.min.js"></script>
    <script type="text/javascript" src="scripts/showReview.js"></script>
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
            width : 100px;
        }
        .rightTd{
            width : 300px;
        }
    </style>
    <script type="text/javascript">
        //评论id
        var reviewId = <%=review.getId()%>;
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
<div style="background-color: rgb(212, 212, 204);">
    <br>
    <br>
    <div align="center">
        <form>
            <table>
                <tr>
                    <td class="leftTd">
                        归类:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" disabled="disabled"
                               value="<%=review.getType()%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        标题:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" disabled="disabled"
                               value="<%=review.getTitle()%>">
                        <%
                            if(isAdminLogin){
                        %>
                        <input class="button" type="button" value="<%=review.isZdtj()?"下置顶":"上置顶"%>" onclick="<%=review.isZdtj()?"zdtj(0)":"zdtj(1)"%>">
                        <input class="button" type="button" value="<%=review.isZdtjTop()?"下置顶图":"上置顶图"%>" onclick="<%=review.isZdtjTop()?"zdtjTop(0)":"zdtjTop(1)"%>">
                        <input class="button" type="button" value="<%=review.isMwgs()?"下美文":"上美文"%>" onclick="<%=review.isMwgs()?"mwgs(0)":"mwgs(1)"%>">
                        <input class="button" type="button" value="<%=review.isMwgsTop()?"下美文图":"上美文图"%>" onclick="<%=review.isMwgsTop()?"mwgsTop(0)":"mwgsTop(1)"%>">
                        <input class="button" type="button" value="<%=review.isBzyc()?"下本站":"上本站"%>" onclick="<%=review.isBzyc()?"bzyc(0)":"bzyc(1)"%>">
                        <input class="button" type="button" value="<%=review.isBzycTop()?"下本站图":"上本站图"%>" onclick="<%=review.isBzycTop()?"bzycTop(0)":"bzycTop(1)"%>">
                        <input class="button" type="button" value="<%=review.isHyzlTop()?"下会员图":"上会员图"%>" onclick="<%=review.isHyzlTop()?"hyzlTop(0)":"hyzlTop(1)"%>">
                        <%
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        图片:
                    </td>
                    <td class="rightTd">
                        <img src="<%=review.getPhoto()%>" width="25%">
                        <%
                            if(user != null && review.getUserId() == user.getId() && StringUtils.isNotBlank(review.getPhoto())){
                                if(review.isSelfTop()){
                        %>
                        <input class="button" type="button" value="取消置顶" onclick="location.href='showReview.jsp?id=<%=id%>&setTop=0'">
                        <%
                        } else {
                        %>
                        <input class="button" type="button" value="置顶" onclick="location.href='showReview.jsp?id=<%=id%>&setTop=1'">
                        <%
                                }
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div id="editor" style="background-color: #ffffff;">
                            <%=review.getContent()%>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <br>
    <br>
    <form name="updateLocationBitForm" action="updateLocationBit.do" method="post">
        <input type="hidden" name="token" value="<%=token%>">
        <input id="updateLocationBitReviewId" name="reviewId" type="hidden">
        <input id="updateLocationBitType" name="type" type="hidden">
        <input id="updateLocationBitValue" name="value" type="hidden">
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