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
            width : 150px;
        }
        .rightTd{
            width : 300px;
        }
    </style>
</head>
<body>
<div align="center" style="background: url('images/realart_bg.jpg');
    vertical-align: middle;
    background-size: 100%;
    background-position: center;">
    <img src="images/realart_logo.png" height="100" alt="真艺网">
    <img src="images/realart.png" height="100" alt="真艺网">
</div>
<div style="background-color: rgb(212, 212, 204);" align="center">
    <div style="width: 600px; background-color: rgb(212, 212, 204);" align="left">
        <br>
        <br>
        <form name="updateArtistInfoForm" method="post" autocomplete="off" action="updateArtistInfo.do?token=<%=token%>"
              enctype="multipart/form-data">
            <div align="center">
                <table>
                    <tr>
                        <td class="leftTd">
                            艺术家用户名:
                        </td>
                        <td class="rightTd">
                            <%=user.getName()%>
                        </td>
                    </tr>
                    <tr>
                        <td class="leftTd">
                            身份证姓名:
                        </td>
                        <td class="rightTd">
                            <input class="text-input between-medium-large-input" type="text" id="cert_name"
                                   name="certName" value="<%=user.getCertName()%>">
                        </td>
                    </tr>
                    <tr>
                        <td class="leftTd">
                            艺术家压题图:
                        </td>
                        <td class="rightTd">
                            <img src="/<%=user.getTitlePhoto()%>" height="100">
                            <input class="text-input between-medium-large-input" type="file" id="title_photo" name="titlePhoto">
                        </td>
                    </tr>
                    <tr>
                        <td class="leftTd">
                            个人照片:
                        </td>
                        <td class="rightTd">
                            <img src="/<%=user.getHeadPhoto()%>" height="100">
                            <input class="text-input between-medium-large-input" type="file" id="head_photo" name="headPhoto">
                        </td>
                    </tr>
                    <%
                        for(int i=0;i<json.size();i++)
                        {
                            JSONObject temp = json.getJSONObject(i);
                            String name = (StringUtils.trimToEmpty((String) temp.get("name")));
                            String need = (StringUtils.trimToEmpty((String) temp.get("need")));
                            String type = (StringUtils.trimToEmpty((String) temp.get("type")));
                    %>
                    <tr>
                        <td class="leftTd">
                            <%=name%>:
                        </td>
                        <td class="rightTd">
                            <%
                                if(Integer.parseInt(type) == UserInterface.COLUMN_TYPE_STRING){
                            %>
                            <input class="text-input between-medium-large-input" type="text" id="item<%=i+1%>"
                                   name="item<%=i+1%>" value="<%=StringUtils.trimToEmpty((String)userInfo.get(name))%>">
                            <%
                            } else {
                            %>
                            <img src="<%=userInfo.get(name)%>" height="100">
                            <input class="text-input between-medium-large-input" type="file" id="item<%=i+1%>"
                                   name="item<%=i+1%>">
                            <%
                                }
                            %>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <textarea style="width: 100%;">真艺网专栏开通须知</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <input type="checkbox" id="checkbox">我已阅读并同意遵守
                            <input class="button" type="button" value="OK 提交" onclick="updateArtistInfo()">
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
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