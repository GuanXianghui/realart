<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
<%@ include file="homeWithOutCheckLogin.jsp" %>
    <%
        //外层
        outLayer = "首页模块";
        //内层
        inLayer = "首页艺术家管理";
    %>
<html>
<head>
    <title>首页图片管理</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/ysjParam.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript">
        //数量
        var ysjParamCount = <%=yisujiaList.size()%>;
        //个数 暂定30
        var maxCount = 30;
    </script>
</head>
<body>
<div id="body-wrapper">
<div id="sidebar">
    <div id="sidebar-wrapper">
        <h1 id="sidebar-title"><a href="#">真艺网</a></h1>
        <div align="center">
            <img id="logo" src="images/realart_logo.png" width="50" alt="Simpla Admin logo"/>
        </div>
        <div id="profile-links">
            Hello, [<%=adminUserName%>],真艺网欢迎您！
            <br/>
            <br/>
            <a href="javascript: logOut()" title="Sign Out">退出</a>
        </div>
        <%@ include file="layers.jsp" %>
    </div>
</div>
<div id="main-content">
    <div id="message_id" class="notification information png_bg" style="display: none;">
        <a href="#" class="close">
            <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
        </a>

        <div id="message_id_content"> 提示信息！</div>
    </div>

    <div class="content-box">
        <div class="content-box-header">
            <h3>首页友情链接</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <span style="display: none;">
                    <form name="deleteYsjParamForm" action="deleteYsjParam.do" method="post">
                        <input type="hidden" name="token" value="<%=token%>">
                        <input type="hidden" name="id" id="delete_ysj_param_id">
                    </form>
                </span>
                <form name="updateYsjParamForm" method="post" autocomplete="off"
                      action="updateYsjParam.do?token=<%=token%>" enctype="multipart/form-data">
                    <input type="hidden" id="ysjParamCount" name="ysjParamCount" value="<%=yisujiaList.size()%>">
                    <table id="ysj_param_table">
                        <thead>
                        <tr>
                            <th width="20%">提示文字</th>
                            <th width="40%">链接</th>
                            <th width="10%">图片</th>
                            <th width="15%">是否显示</th>
                            <th width="15%">操作</th>
                        </tr>
                        </thead>
                        <%
                            for(int i=0;i<yisujiaList.size();i++)
                            {
                                URLTitleName item = yisujiaList.get(i);
                        %>
                        <tr>
                            <td>
                                <input type="hidden" name="id<%=i+1%>" value="<%=item.getID()%>">
                                <input class="text-input large-input" type="text" name="name<%=i+1%>" value="<%=item.getName()%>">
                            </td>
                            <td>
                                <input class="text-input large-input" type="text" name="url<%=i+1%>" value="<%=item.getUrl()%>">
                            </td>
                            <td>
                                <img width="100" src="<%=item.getImg()%>">
                                <input type="file" name="img<%=i+1%>">
                            </td>
                            <td>
                                <select class="text-input large-input" name="dis<%=i+1%>">
                                    <option value="1" <%=item.getDis()?" SELECTED":""%>>显示</option>
                                    <option value="0" <%=!item.getDis()?" SELECTED":""%>>不显示</option>
                                </select>
                            </td>
                            <td>
                                <input class="button" type="button" onclick="deleteYsjParam(<%=item.getID()%>);" value="删除">
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                    <input class="button" type="button" onclick="addYsjParam();" value="新增友情链接">
                    <input class="button" type="submit" value="保存">
                </form>
            </div>
        </div>
    </div>
    <div class="clear"></div>
    <div id="footer">
        <small>
            &#169; Copyright 2014 Realart
        </small>
    </div>
</div>
</div>
</body>
</html>