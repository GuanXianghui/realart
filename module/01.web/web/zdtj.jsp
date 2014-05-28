<%@ page import="com.realart.dao.UserDao" %>
<%@ page import="com.realart.entities.Review" %>
<%@ page import="com.realart.dao.ReviewDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>评论页面</title>
    <script type="text/javascript" src="/scripts/jquery-min.js"></script>
    <script type="text/javascript" src="/scripts/base.js"></script>
</head>
<body>
<div align="center" style="background: url('images/realart_bg.jpg');
    vertical-align: middle;
    background-size: 100%;
    background-position: center;">
    <img src="images/realart_logo.png" height="100" alt="真艺网">
    <img src="images/realart.png" height="100" alt="真艺网">
</div>
<div>
    <div>陶瓷评论</div>
    <div>
        <%
            for(Review review : ReviewDao.queryReviewsByType("zdtjTop")){
        %>
        <a href="/showReview.jsp?id=<%=review.getId()%>"><img src="/<%=review.getPhoto()%>" alt="<%=review.getTitle()%>" width="100"></a>
        <%
            }
        %>
    </div>
    <div>置顶推荐</div>
    <div>
        <%
            for(Review review : ReviewDao.queryReviewsByType("zdtj")){
                User reviewUser = UserDao.getUserById(review.getUserId());
        %>
        <div>
            <div style="float: right"><%=reviewUser.getCertName()%></div>
            <div><a href="/showReview.jsp?id=<%=review.getId()%>"><%=review.getTitle()%></a></div>
        </div>
        <%
            }
        %>
    </div>
</div>
<div align="center" style="background-color: gray;">
    <a href="#"><img width="40" src="/images/button/1.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/2.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/3.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/4.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/5.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/6.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/7.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/8.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/9.jpg" alt=""></a>
    <a href="#"><img width="40" src="/images/button/10.jpg" alt=""></a>
</div>
</body>
</html>