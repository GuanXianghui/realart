<%@ page import="com.realart.dao.UserDao" %>
<%@ page import="com.realart.interfaces.UserInterface" %>
<%@ page import="java.util.List" %>
<%@ page import="com.realart.utils.BaseUtil" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
<%
    //外层
    outLayer = "审核模块";
    //内层
    inLayer = "审核艺术家";

    //审核失败原因数组
    String[] reasons = new String[]{};
    if(StringUtils.isNotBlank(checkWrongReason)){
        reasons = checkWrongReason.split("\\|");
    }

    //修改用户状态
    String userId = request.getParameter("userId");
    String state = request.getParameter("state");
    String reason = request.getParameter("reason");
    if(StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(state)){
        User artistUser = UserDao.getUserById(Integer.parseInt(userId));
        artistUser.setState(Integer.parseInt(state));
        for(int i=0;i<reasons.length;i++){
            if(StringUtils.equals(StringUtils.EMPTY+(i+1), reason)){
                artistUser.setReason(reasons[i]);
            }
        }
        UserDao.updateUserState(artistUser);
%>
<script type="text/javascript">
    message = "审核艺术家成功";
</script>
<%
    }

    //待审核的艺术家
    List<User> artistUsers = UserDao.queryUsersByUserTypeAndState(UserInterface.USER_TYPE_ARTIST, UserInterface.USER_STATE_NEED_CHECK);
    //艺术家注册项 json串转换成数组
    JSONArray json = JSONArray.fromObject(artistUserRegistItems);
%>
<html>
<head>
    <title>审核艺术家</title>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/pinyin.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/checkUpArtist.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript">
        //用户集合json串
        var userJsonStr = "<%=BaseUtil.getJsonArrayFromUsers(artistUsers)%>";
        //选择用户Id
        var chooseUserId = 0;
        //输入项个数
        var itemCount = <%=json.size()%>;
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
        <div class="column-left">
            <div class="content-box">
                <div class="content-box-header">
                    <h3>待审核艺术家</h3>
                </div>
                <div class="content-box-content">
                    <div class="tab-content default-tab">
                        <div id="detail_div"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="column-right">
            <div class="content-box">
                <div class="content-box-header">
                    <h3>艺术家信息</h3>
                </div>
                <div class="content-box-content">
                    <div class="tab-content default-tab">
                        <form>
                            <table>
                                <tr>
                                    <td>艺术家用户名</td>
                                    <td><span id="name"></span></td>
                                </tr>
                                <tr>
                                    <td>身份证姓名</td>
                                    <td><span id="cert_name"></span></td>
                                </tr>
                                <tr>
                                    <td>艺术家压题图</td>
                                    <td><span id="title_photo"></span></td>
                                </tr>
                                <tr>
                                    <td>个人照片</td>
                                    <td><span id="head_photo"></span></td>
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
                                    <td id="item_name<%=i+1%>" name="<%=type%>"><%=name%></td>
                                    <td><span id="item<%=i+1%>"></span></td>
                                </tr>
                                <%
                                    }
                                %>
                                <tr>
                                    <td>失败原因</td>
                                    <td>
                                        <select class="text-input medium-input" id="reason">
                                            <%
                                                int count = 0;
                                                for(String temp : reasons){
                                                    count ++;
                                            %>
                                            <option value="<%=count%>"><%=temp%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <input class="button" type="button" onclick="checkSuccess();" value="审核通过">
                                        <input class="button" type="button" onclick="checkFailed();" value="审核不通过">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
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