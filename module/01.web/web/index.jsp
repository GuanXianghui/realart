<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<%
    //导航json串转换成数组
    JSONArray json = JSONArray.fromObject(indexGuide);
%>
<html>
<head>
    <title>首页</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <style type="text/css">
        .a{
            background-color: gray;
            margin-bottom: 10px;
            width: 95%;
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
            padding: 5px;
        }
    </style>
</head>
<body>
<div align="center" style="background: url('<%=indexBgImg%>'); background-position: center;">
    <div style="height: 50px;"></div>
    <div><img src="<%=indexLogoImg%>" width="<%=indexLogoWidth%>" height="<%=indexLogoHeight%>" alt="真艺网"></div>
    <div><img src="<%=indexRealartImg%>" width="<%=indexRealartWidth%>" height="<%=indexRealartHeight%>" alt="真艺网"></div>
    <div style="height: 50px;"></div>
    <%
        for(int i=0;i<json.size();i++)
        {
            JSONObject temp = json.getJSONObject(i);
            String name = (StringUtils.trimToEmpty((String) temp.get("name")));
            String url = (StringUtils.trimToEmpty((String) temp.get("url")));
    %>
    <div class="a"><span onclick="location.href='<%=url%>'"><%=name%></span></div>
    <%
        }
    %>
    <div style="height: 50px;"></div>
    <div class="a">
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
</div>
</body>
</html>