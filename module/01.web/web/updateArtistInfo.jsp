<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="artistHeader.jsp" %>
<%
    //艺术家注册项 json串转换成数组
    JSONArray json = JSONArray.fromObject(artistUserRegistItems);
    //用户信息转json对象
    JSONObject userInfo = JSONObject.fromObject(user.getInfo());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>艺术家资料修改</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <script type="text/javascript" src="scripts/updateArtistInfo.js"></script>
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
    <form name="updateArtistInfoForm" method="post" autocomplete="off" action="updateArtistInfo.do?token=<%=token%>"
          enctype="multipart/form-data">
        <div align="center">艺术家资料修改界面</div>
        <div>艺术家用户名:<%=user.getName()%></div>
        <div>身份证姓名<input type="text" id="cert_name" name="certName" value="<%=user.getCertName()%>"></div>
        <div>艺术家压题图<img src="/<%=user.getTitlePhoto()%>" height="100"><input type="file" id="title_photo" name="titlePhoto"></div>
        <div>个人照片&nbsp;&nbsp;<img src="/<%=user.getHeadPhoto()%>" height="100"><input type="file" id="head_photo" name="headPhoto"></div>
        <%
            for(int i=0;i<json.size();i++)
            {
                JSONObject temp = json.getJSONObject(i);
                String name = (StringUtils.trimToEmpty((String) temp.get("name")));
                String need = (StringUtils.trimToEmpty((String) temp.get("need")));
                String type = (StringUtils.trimToEmpty((String) temp.get("type")));
        %>
        <div>
            <%=name%>
            <%
                if(Integer.parseInt(type) == UserInterface.COLUMN_TYPE_STRING){
            %>
            <input type="text" id="item<%=i+1%>" name="item<%=i+1%>" value="<%=StringUtils.trimToEmpty((String)userInfo.get(name))%>">
            <%
                } else {
            %>
            <img src="<%=userInfo.get(name)%>" height="100">
            <input type="file" id="item<%=i+1%>" name="item<%=i+1%>">
            <%
                }
            %>
        </div>
        <%
            }
        %>
        <div>
            <textarea style="width: 100%;">真艺网专栏开通须知</textarea>
        </div>
        <div>
            <input type="checkbox" id="checkbox">我已阅读并同意遵守
            <input type="button" value="OK 提交" onclick="updateArtistInfo()">
        </div>
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