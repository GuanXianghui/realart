<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
<%@ include file="homeWithOutCheckLogin.jsp" %>
    <%
        //外层
        outLayer = "首页模块";
        //内层
        inLayer = "首页八框内容管理";
    %>
<html>
<head>
    <title>首页图片管理</title>
    <script type="text/javascript" src="scripts/jquery-min.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/itemList.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript">
        <%
        for (int i = 0; i < 8; i++) {//一定是8个
            JSONObject itemImg = JSONObject.fromObject(itemcontentList.get(i).getImg());//内容展示类型+图片地址+图片个数
            int size = itemImg.getInt("size");//图片个数
        %>
        //图片个数
        var size<%=i+1%> = <%=size%>;
        <%
            }
        %>
        //图片上限
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

    <span style="display: none;">
        <form name="deleteItemListForm" action="deleteItemList.do" method="post">
            <input type="hidden" name="token" value="<%=token%>">
            <input type="hidden" name="id" id="delete_item_list_id">
            <input type="hidden" name="index" id="delete_item_list_index">
        </form>
    </span>

    <%
        for (int i = 0; i < 8; i++) {//一定是8个
            boolean dis = itemcontentList.get(i).getDis();//是否显示

            JSONObject itemImg = JSONObject.fromObject(itemcontentList.get(i).getImg());//内容展示类型+图片地址+图片个数
            int type = itemImg.getInt("type");//内容展示类型
            JSONArray imgUrl = JSONArray.fromObject(itemImg.get("url"));//图片地址
            int size = itemImg.getInt("size");//图片个数
            JSONArray btnTitle = JSONArray.fromObject(itemImg.get("btntitle"));//按钮文字 当内容展示类型为4才需要配置
            String bigTitle = itemImg.getString("title");//大标题 当内容展示类型为4才需要配置

            JSONArray itemUrl = JSONArray.fromObject(itemcontentList.get(i).getUrl());//链接地址

            JSONObject itemName = JSONObject.fromObject(itemcontentList.get(i).getName());//标题+提示+内容
            JSONArray title = JSONArray.fromObject(itemName.get("title"));//标题
            JSONArray alt = JSONArray.fromObject(itemName.get("alt"));//提示
            JSONArray content = JSONArray.fromObject(itemName.get("content"));//内容
    %>
    <div class="content-box">
        <div class="content-box-header">
            <h3>首页八框内容(<%=i+1%>/8)</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <form name="updateItemListForm<%=i+1%>" method="post" autocomplete="off"
                      action="updateItemList.do?token=<%=token%>" enctype="multipart/form-data">
                    <input name="id" type="hidden" value="<%=i+1%>">
                    <input name="size" id="size<%=i+1%>" type="hidden" value="<%=size%>">
                    <!-- 整体 -->
                    内容展示类型:
                    <select class="text-input little-small-input" name="type">
                        <option value="1"<%=type==1?" SELECTED":""%>>方式1</option>
                        <option value="2"<%=type==2?" SELECTED":""%>>方式2</option>
                        <option value="3"<%=type==3?" SELECTED":""%>>方式3</option>
                        <option value="4"<%=type==4?" SELECTED":""%>>方式4</option>
                    </select>
                    是否显示:
                    <select class="text-input little-small-input" name="dis">
                        <option value="1" <%=dis?" SELECTED":""%>>显示</option>
                        <option value="0" <%=!dis?" SELECTED":""%>>不显示</option>
                    </select>
                    大标题:
                    <input class="text-input small-input" type="text" name="bigTitle" value="<%=bigTitle%>">
                    <br>
                    注意：大标题和按钮是当内容展示类型为4才需要配置
                    <!-- 逐个 -->
                    <table id="item_list_table<%=i+1%>">
                        <thead>
                        <tr>
                            <th width="20%">内容</th>
                            <th width="20%">标题</th>
                            <th width="10%">提示</th>
                            <th width="10%">图片</th>
                            <th width="20%">链接</th>
                            <th width="10%">按钮</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <%
                            for(int j=0;j<size;j++){
                        %>
                        <tr>
                            <td>
                                <input class="text-input large-input" type="text" name="content<%=j+1%>" value="<%=content.getString(j)%>">
                            </td>
                            <td>
                                <input class="text-input large-input" type="text" name="title<%=j+1%>" value="<%=title.getString(j)%>">
                            </td>
                            <td>
                                <input class="text-input large-input" type="text" name="alt<%=j+1%>" value="<%=alt.getString(j)%>">
                            </td>
                            <td>
                                <img src="<%=imgUrl.getString(j)%>" width="100px">
                                <input type="file" name="imgUrl<%=j+1%>">
                            </td>
                            <td>
                                <input class="text-input large-input" type="text" name="itemUrl<%=j+1%>" value="<%=itemUrl.getString(j)%>">
                            </td>
                            <td>
                                <input class="text-input large-input" type="text" name="btnTitle<%=j+1%>" value="<%=btnTitle.getString(j)%>">
                            </td>
                            <td>
                                <input class="button" type="button" onclick="deleteItemList(<%=i+1%>, <%=j+1%>);" value="删除">
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                    <input class="button" type="button" onclick="addItemList(<%=i+1%>);" value="新增图片">
                    <input class="button" type="submit" value="保存">
                </form>
            </div>
        </div>
    </div>
    <%
        }
    %>

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