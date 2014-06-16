<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul id="main-nav">
    <li>
        <a class="nav-top-item no-submenu" href="reviewUser.jsp?name=<%=user.getName()%>">
            我的首页
        </a>
    </li>
    <li><a href="#" class="nav-top-item<%=outLayer.equals("用户模块")?" current":""%>"> 用户模块 </a>
        <ul>
            <li><a href="<%=baseUrl%>updateReviewInfo.jsp"<%=inLayer.equals("资料修改")?" class=\"current\"":""%>>资料修改</a></li>
            <li><a href="<%=baseUrl%>updateReviewPassword.jsp"<%=inLayer.equals("密码修改")?" class=\"current\"":""%>>密码修改</a></li>
        </ul>
    </li>
    <li><a href="#" class="nav-top-item<%=outLayer.equals("评论模块")?" current":""%>"> 评论模块 </a>
        <ul>
            <li><a href="<%=baseUrl%>createReview.jsp"<%=inLayer.equals("发表评论")?" class=\"current\"":""%>>发表评论</a></li>
            <li><a href="<%=baseUrl%>updateReviewKinds.jsp"<%=inLayer.equals("评论分类修改")?" class=\"current\"":""%>>评论分类修改</a></li>
            <li><a href="<%=baseUrl%>myReviews.jsp"<%=inLayer.equals("我的评论")?" class=\"current\"":""%>>我的评论</a></li>
        </ul>
    </li>
</ul>