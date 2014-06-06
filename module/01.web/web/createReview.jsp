<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="reviewHeader.jsp" %>
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
    <script type="text/javascript" src="scripts/createReview.js"></script>
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
    <form name="createReviewForm" method="post" autocomplete="off" action="createReview.do?token=<%=token%>"
          enctype="multipart/form-data">

        <div align="center">
            <table>
                <tr>
                    <td class="leftTd">
                        归类:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="type" name="type"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("type"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        标题:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="text" id="title" name="title"
                               value="<%=StringUtils.trimToEmpty((String)request.getAttribute("title"))%>">
                    </td>
                </tr>
                <tr>
                    <td class="leftTd">
                        图片:
                    </td>
                    <td class="rightTd">
                        <input class="text-input between-medium-large-input" type="file" id="photo" name="photo">
                    </td>
                </tr>
                <textarea style="display: none;" id="content" name="content"></textarea>
                <tr>
                    <td colspan="2" width="500">
                        <script id="editor" type="text/plain"></script>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input class="button" type="button" value="OK 提交" onclick="createReview()">
                        <input class="button" type="button" value="重新填写" onclick="rewrite()">
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <br>
    <br>
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