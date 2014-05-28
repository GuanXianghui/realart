<%@ page import="com.realart.dao.UserDao" %>
<%@ page import="com.realart.interfaces.UserInterface" %>
<%@ page import="com.realart.entities.Review" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.realart.dao.ReviewDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<%
    String name = StringUtils.trimToEmpty(request.getParameter("name"));
    if(StringUtils.isBlank(name)){
        response.sendRedirect("/index.jsp");
        return;
    }
    User reviewUser = UserDao.getUserByNameAndUserType(name, UserInterface.USER_TYPE_REVIEW);
    if(reviewUser == null){
        response.sendRedirect("/index.jsp");
        return;
    }
    List<Review> topReviews = new ArrayList<Review>();
    List<Review> reviews = ReviewDao.queryReviewsByUserId(reviewUser.getId());
    for(Review review : reviews){
        if(review.isSelfTop()){
            topReviews.add(review);
        }
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>评论页面</title>
    <script type="text/javascript" src="/scripts/jquery-min.js"></script>
    <script type="text/javascript" src="/scripts/base.js"></script>
</head>
<body>
<div align="center" style="background: url('/<%=reviewUser.getTitlePhoto()%>');
        vertical-align: middle;
        background-size: 100%;
        background-position: center;">
    <table>
        <tr>
            <td>
                <img src="/<%=reviewUser.getHeadPhoto()%>" height="100">
            </td>
            <td>
                <div><span style="font-size: 40px; font-weight: bold;"><%=reviewUser.getCertName()%></span>评论专栏</div>
                <div><%=baseUrl%>r/<%=reviewUser.getName()%></div>
            </td>
        </tr>
    </table>
</div>
<div>
    <div>陶瓷评论</div>
    <div>
        <%
            for(Review review : topReviews){
        %>
        <a href="/showReview.jsp?id=<%=review.getId()%>"><img src="/<%=review.getPhoto()%>" alt="<%=review.getTitle()%>" width="100"></a>
        <%
            }
        %>
    </div>
    <div>会员专栏</div>
    <div>
        <%
            for(Review review : reviews){
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