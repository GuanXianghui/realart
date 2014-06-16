<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul id="main-nav">
    <li>
        <a class="nav-top-item no-submenu" href="artistUser.jsp?name=<%=user.getName()%>">
            我的官网
        </a>
    </li>
    <li>
        <a class="nav-top-item no-submenu" href="uploadArt.jsp">
            作品备案
        </a>
    </li>
    <li><a href="#" class="nav-top-item<%=outLayer.equals("用户模块")?" current":""%>"> 用户模块 </a>
        <ul>
            <li><a href="<%=baseUrl%>updateArtistInfo.jsp"<%=inLayer.equals("资料修改")?" class=\"current\"":""%>>资料修改</a></li>
            <li><a href="<%=baseUrl%>updateArtistPassword.jsp"<%=inLayer.equals("密码修改")?" class=\"current\"":""%>>密码修改</a></li>
        </ul>
    </li>
    <li><a href="#" class="nav-top-item<%=outLayer.equals("作品模块")?" current":""%>"> 作品模块 </a>
        <ul>
            <li><a href="<%=baseUrl%>updateArtKinds.jsp"<%=inLayer.equals("作品分类修改")?" class=\"current\"":""%>>作品分类修改</a></li>
            <li><a href="<%=baseUrl%>needCheckArts.jsp"<%=inLayer.equals("我的作品")?" class=\"current\"":""%>>我的作品</a></li>
        </ul>
    </li>
</ul>