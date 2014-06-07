<%@ page import="com.realart.interfaces.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="artistHeader.jsp" %>
<%
    //艺术品所有分类
    String artKinds = StringUtils.trimToEmpty(user.getArtKinds());
    String[] artKindArray = new String[0];
    if(StringUtils.isNotBlank(artKinds)){
        artKindArray = artKinds.split(SymbolInterface.SYMBOL_COMMA);
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>艺术家分类维护界面</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/updateArtKinds.js"></script>
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
            width : 300px;
        }
        .rightTd{
            width : 150px;
        }
    </style>
    <script type="text/javascript">
        //分类个数统计
        var kindCount = <%=artKindArray.length%>;
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
<div style="background-color: rgb(212, 212, 204);" align="center">
    <div style="width: 600px; background-color: rgb(212, 212, 204);" align="left">
        <br>
        <br>
        <form>
            <div align="center">
                <table>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            艺术品分类维护
                        </td>
                    </tr>
                    <%
                        for(int i=0;i<artKindArray.length;i++){
                    %>
                    <tr>
                        <td class="leftTd">
                            <input id="kind<%=i+1%>" class="text-input between-medium-large-input"
                                   type="text" value="<%=artKindArray[i]%>">
                        </td>
                        <td class="rightTd">
                            <input class="button" type="button" value="修改" onclick="updateArtKind('kind<%=i+1%>')">
                            <input class="button" type="button" value="删除" onclick="deleteArtKind('kind<%=i+1%>')">
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <hr>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td class="leftTd">
                            新增分类：
                            <input id="create_art_kind" class="text-input between-medium-large-input"
                                   type="text" value="">
                        </td>
                        <td class="rightTd">
                            <input class="button" type="button" value="新增" onclick="createArtKind()">
                        </td>
                    </tr>
                </table>
                <input class="button" type="button" value="返回艺术家控台" onclick="location.href='artistConsole.jsp'">
            </div>
        </form>
        <form name="updateArtKindsForm" method="post" action="updateArtKinds.do" style="display: none;">
            <input type="hidden" name="token" value="<%=token%>">
            <textarea id="artKinds" name="artKinds">
            </textarea>
        </form>
        <br>
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