<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul id="main-nav">
    <li><a href="#" class="nav-top-item<%=outLayer.equals("首页模块")?" current":""%>"> 首页模块 </a>
        <ul>
            <li><a href="<%=baseUrl%>indexImg.jsp"<%=inLayer.equals("首页图片管理")?" class=\"current\"":""%>>首页图片管理</a></li>
            <li><a href="<%=baseUrl%>indexGuide.jsp"<%=inLayer.equals("首页导航管理")?" class=\"current\"":""%>>首页导航管理</a></li>
        </ul>
    </li>
    <li><a href="#" class="nav-top-item<%=outLayer.equals("注册模块")?" current":""%>"> 注册模块 </a>
        <ul>
            <li><a href="<%=baseUrl%>reviewUserRegistConfig.jsp"<%=inLayer.equals("评论会员注册配置")?" class=\"current\"":""%>>评论会员注册配置</a></li>
            <li><a href="<%=baseUrl%>artistUserRegistConfig.jsp"<%=inLayer.equals("艺术家注册配置")?" class=\"current\"":""%>>艺术家注册配置</a></li>
            <li><a href="<%=baseUrl%>adminRegistArtist.jsp"<%=inLayer.equals("注册艺术家")?" class=\"current\"":""%>>注册艺术家</a></li>
        </ul>
    </li>
    <li><a href="#" class="nav-top-item<%=outLayer.equals("审核模块")?" current":""%>"> 审核模块 </a>
        <ul>
            <li><a href="<%=baseUrl%>checkWrongReason.jsp"<%=inLayer.equals("审核失败原因维护")?" class=\"current\"":""%>>审核失败原因维护</a></li>
            <li><a href="<%=baseUrl%>checkUpArtist.jsp"<%=inLayer.equals("审核艺术家")?" class=\"current\"":""%>>审核艺术家</a></li>
            <li><a href="<%=baseUrl%>checkUpArt.jsp"<%=inLayer.equals("审核艺术品")?" class=\"current\"":""%>>审核艺术品</a></li>
        </ul>
    </li>
    <li><a href="#" class="nav-top-item<%=outLayer.equals("序列号模块")?" current":""%>"> 序列号模块 </a>
        <ul>
            <li><a href="<%=baseUrl%>qrCode.jsp"<%=inLayer.equals("序列号维护")?" class=\"current\"":""%>>序列号维护</a></li>
        </ul>
    </li>
</ul>