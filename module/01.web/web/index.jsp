<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<%@ include file="homeWithOutCheckLogin.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    JSONArray json = JSONArray.fromObject(indexGuide);
    JSONArray headjson = JSONArray.fromObject(headGuide);
    JSONArray contactjson = JSONArray.fromObject(contactGuide);
    JSONArray slider01json = JSONArray.fromObject(slider01);
    JSONObject apkList = JSONObject.fromObject(apkGuide);
    JSONObject moreList = JSONObject.fromObject(ysjMore);
    JSONObject Online_Info = JSONObject.fromObject(onlineInfo);
    JSONArray QQ_Info = JSONArray.fromObject(Online_Info.get("qq"));
    JSONObject Email_Info = JSONObject.fromObject(Online_Info.get("email"));
    JSONObject Company_Info = JSONObject.fromObject(Online_Info.get("company"));
    JSONObject Liuyan_Info = JSONObject.fromObject(Online_Info.get("liuyan"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><%=(String) headjson.getJSONObject(0).get("text")%>
    </title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
    <script src="scripts/jquery-1.9.1.min.js" type="text/javascript"></script>
    <meta name="KeyWords" content="<%=(String)headjson.getJSONObject(1).get("text")%>"/>
    <meta name="description" content="<%=(String)headjson.getJSONObject(2).get("text")%>"/>
    <link rel="stylesheet" type="text/css" href="css/style_index_1.css"/>
    <script src="js/zmd.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="js/base.min.css"/>
    <link rel="stylesheet" type="text/css" href="js/nine.css"/>
    <link rel="stylesheet" type="text/css" href="css/zmd1.css"/>
    <link rel="stylesheet" type="text/css" href="css/3d.css"/>
    <link href="css/online.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">


        function jump(str2) {
            // alert(str2);
            window.location.href = str2;
        }
    </script>
</head>
<body>

<div id="homehead1" style="background:url('<%=indexBgImg%>') no-repeat;">
    <div id="homehead2">
        <div id="logo"><img src="<%=indexLogoImg%>" alt="真艺网"/></div>
        <div id="title"
             style="font-size:34px;color:#00aabb;font-family:'微软雅黑'"><%=(String) headjson.getJSONObject(3).get("text")%>
        </div>
        <div class="rlsl">
            <ul>
                <%
                    if (isLogin || isAdminLogin) {
//              String site="";
//              if((user.getUserType()==0)||isAdminLogin) site="indexImg.jsp";
//              if(user.getUserType()==1) site="indexReview.jsp";
//              if(user.getUserType()==2) site="indexArt.jsp";

                %>
                <li id="top-menu8"><a href="registReview.jsp"> 管理平台</a></li>
                <%} else { %>
                <li id="top-menu8"><a href="login.jsp">登陆平台</a></li>
                <%} %>
                <li id="top-menu9"><a href="registArtist.jsp">注册艺术家</a></li>
                <li id="top-menu9"><a href="registReview.jsp"> 注册评论员</a></li>
                <li id="top-menu9"><a href="javascript: logOut()"> 退出登陆</a></li>

            </ul>
        </div>
    </div>
</div>
<div id="header_bk">
    <div id="header_btn3">
        <input type="text" value="" style="background:#00ffff;margin-top:8px;  width:140px;float:left"/>
        <input type="button" style="margin-top:8px; margin-left:8px; width:80px;float:left" value="站内搜索"/>
    </div>
    <div id="header_btn2"><input type="button"
                                 style="background:url(images/index/header_button-2.png) no-repeat;border-style:none;width:16px; height:35px"/>
    </div>

    <%
        for (int i = json.size() - 1; i > -1; i--) {
            JSONObject temp = json.getJSONObject(i);
            String name = (StringUtils.trimToEmpty((String) temp.get("name")));
            String url = (StringUtils.trimToEmpty((String) temp.get("url")));
    %>
    <div id="header_btn1"><input type="button" onclick="jump('<%=url%>')"
                                 onmouseover="this.style.background='url(images/index/header_button-3_select.png) no-repeat'"
                                 onmouseout="this.style.background='url(images/index/header_button-3.png) no-repeat'"
                                 style="background:url(images/index/header_button-3.png) no-repeat;cursor:hand;border-style:none;width:104px; height:32px;font-size:16px;font-family:'微软雅黑'"
                                 value="<%=name%>"/></div>
    <%}%>
    <div id="header_btn1"><input type="button"
                                 onmouseover="this.style.background='url(images/index/header_button-3_select.png) no-repeat'"
                                 onmouseout="this.style.background='url(images/index/header_button-3.png) no-repeat'"
                                 style="background:url(images/index/header_button-3.png) no-repeat;border-style:none;width:104px; height:32px;font-size:16px;font-family:'微软雅黑';"
                                 value="首页"/></div>
</div>
<div id="header_bk2"></div>


<div class="slider_01">

    <div class="layout" id="floor_1">
        <div class="carousel slide" id="carousel_news"
             data-unit-config="{'onclass':'active', 'offclass':'', 'pause_on_act':'click', 'interval':8000, 'position':'left'}">
            <div class="carousel-inner">
                <div class="carousel-pos">
                    <% for (int i = 0; i < itemcontentList.size(); i++) {

                        JSONObject itemtitle = JSONObject.fromObject(itemcontentList.get(i).getName());
                        JSONArray itemurl = JSONArray.fromObject(itemcontentList.get(i).getUrl());
                        JSONObject itemimg = JSONObject.fromObject(itemcontentList.get(i).getImg());
                        int imgtype = itemimg.getInt("type");
                        int imgsize = itemimg.getInt("size");
                        JSONArray lititle = JSONArray.fromObject(itemtitle.get("title"));
                        JSONArray lialt = JSONArray.fromObject(itemtitle.get("alt"));
                        JSONArray licontent = JSONArray.fromObject(itemtitle.get("content"));
                        JSONArray liimg = JSONArray.fromObject(itemimg.get("url"));
                        //  String name = (StringUtils.trimToEmpty((String) temp.get("name")));
                        //  String url = (StringUtils.trimToEmpty((String) temp.get("url")));


                        switch (imgtype) {
                            case 1:

                    %>
                    <div class="f_out style01">
                        <ul>
                            <% for (int j = 0; j < imgsize; j++) { %>
                            <li>
                                <a class="mtitle" href="<%=itemurl.getString(j)%>" target="_blank"
                                   title="<%=licontent.getString(j)%>"><%=licontent.getString(j)%>
                                </a>
                                <a target="_blank" href="<%=itemurl.getString(j)%>"><img alt="<%=lialt.getString(j)%>"
                                                                                         src="<%=liimg.getString(j)%>"
                                                                                         width="256" height="353"/></a>
                            </li>
                            <%} %>
                        </ul>
                    </div>
                    <%
                            break;
                        case 3:
                    %>

                    <div class="f_out style03">
                        <ul class="sm_pc fr">
                            <li class="active ">
                                <a href="#big_01" data-evt="hover" data-toggle="tab"><img
                                        title="<%=licontent.getString(0)%>" alt="<%=licontent.getString(0)%>"
                                        src="images/nine/grey.gif" data-original="<%=liimg.getString(0) %>" width="129"
                                        height="176"/></a>
                            </li>
                            <li class="mt01">
                                <a href="#big_02" data-evt="hover" data-toggle="tab"><img
                                        title="<%=licontent.getString(1)%>" alt="<%=licontent.getString(1)%>"
                                        src="images/nine/grey.gif" data-original="<%=liimg.getString(1)%>" width="129"
                                        height="176"/></a>
                            </li>
                        </ul>
                        <ul class="big_pc fl">
                            <li class="active" id="big_01">
                                <a class="mtitle" href="<%=itemurl.getString(2)%>" target="_blank"
                                   title="<%=licontent.getString(2)%>"><%=licontent.getString(2)%>
                                </a>
                                <a target="_blank" href="<%=itemurl.getString(2)%>"><img
                                        title="<%=lititle.getString(2)%>" alt="<%=lialt.getString(2)%>"
                                        src="images/nine/grey.gif" data-original="<%=liimg.getString(2)%>" width="637"
                                        height="353"/></a>
                            </li>
                            <li class="" id="big_02">
                                <a class="mtitle" href="<%=imgsize>3?itemurl.getString(3):""%>" target="_blank"
                                   title="<%=imgsize>3?licontent.getString(3):""%>"><%=imgsize > 3 ? licontent.getString(3) : ""%>
                                </a>
                                <a target="_blank" href="<%=imgsize>3?itemurl.getString(3):""%>"><img
                                        title="<%=imgsize>3?lititle.getString(3):""%>"
                                        alt="<%=imgsize>3?lialt.getString(3):""%>" src="images/nine/grey.gif"
                                        data-original="<%=imgsize>3?liimg.getString(3):""%>" width="637" height="353"/></a>
                            </li>
                        </ul>
                    </div>
                    <%
                            break;
                        case 2:
                    %>
                    <div class="f_out style01">
                        <ul>
                            <% for (int k = 0; k < imgsize; k++) { %>
                            <li class="<%=k==1?"li02":"li01" %>">
                                <a class="mtitle" href="<%=itemurl.getString(k)%>" target="_blank"
                                   title="<%=licontent.getString(k)%>"><%=licontent.getString(k)%>
                                </a>
                                <a target="_blank" href="<%=itemurl.getString(k)%>"><img
                                        title="<%=lititle.getString(k)%>" alt="<%=lialt.getString(k)%>"
                                        src="images/nine/grey.gif" data-original="<%=liimg.getString(k)%>"
                                        width="<%=k==1?356:204%>" height="353"/></a>
                            </li>
                            <%} %>
                        </ul>
                    </div>
                    <%
                            break;
                        case 4:
                            JSONArray libtn = JSONArray.fromObject(itemimg.get("btntitle"));
                            String libtnbigtitle = itemimg.getString("title");
                    %>

                    <div class="f_out style04">
                        <div class="fr s_listbox">
                            <p class="white_f"><%=libtnbigtitle%>
                            </p>
                            <ul>
                                <% for (int n = 0; n < imgsize; n++) { %>
                                <li class="<%=n>0?"":"active"%>"><a href="#foucs_0<%=n+1 %>" data-evt="hover"
                                                                    data-toggle="tab"><%=libtn.getString(n) %>
                                </a></li>
                                <%} %>

                            </ul>
                        </div>
                        <ul class="big_pc fl">
                            <% for (int h = 0; h < imgsize; h++) { %>
                            <li class="<%=h>0?"":"active" %>" id="foucs_0<%=h+1 %>">
                                <a class="mtitle" href="<%=itemurl.getString(h)%>" target="_blank"
                                   title="<%=licontent.getString(h)%>"><%=licontent.getString(h)%>
                                </a>
                                <a class="" target="_blank" href="<%=itemurl.getString(h)%>"><img
                                        title="<%=lititle.getString(h)%>" alt="<%=lialt.getString(h)%>"
                                        src="images/nine/grey.gif" data-original="<%=liimg.getString(h)%>" width="563"
                                        height="353"/></a>
                            </li>
                            <%} %>
                        </ul>
                    </div>
                    <%
                                    break;
                                default:
                                    break;
                            }
                        }
                    %>
                </div>
            </div>
            <div class="carousel-panel">
                <ul>
                    <%
                        for (int i = 0; i < itemList.size(); i++) {

                            String item = (StringUtils.trimToEmpty(itemList.get(i).getName()));

                            if (itemList.get(i).getDis()) {
                    %>
                    <li style="background: url(<%=itemList.get(i).getImg()%>) no-repeat 50% 30% #0090CD;"><%=item%>
                    </li>

                    <%
                            }
                        }
                    %>
                </ul>
            </div>
        </div>

    </div>
</div>

<div class="slider_02">
    <div id="body_top-21">
        <div style="margin-top:0px;float:left">
            <img src="images/index/radio.png" alt="" style="margin:6px auto auto 5px;"/>
        </div>
        <div style="margin:6px auto auto 5px;text-align:left;font-size:16px"><%=apkList.get("title")%>
        </div>
        <div id="lineblue"></div>
        <div style="padding:5px;float:top;" align="center"><img src="<%=apkList.get("logo") %>" style="width:200px"/>
        </div>
        <div style="margin:20px auto auto auto;"><input type="image" src="<%=apkList.get("btn")%>" onclick=""
                                                        alt="下载安卓应用"/></div>
    </div>

    <div id="body_top-m">

        <div id="slider3d" class="slide3d-wp">
            <ul>
                <%
                    int jk = 0;
                    for (int kk = 0; kk < slider01json.size(); kk++) {
                        JSONObject temp = slider01json.getJSONObject(kk);
                        String sliderTitle = (StringUtils.trimToEmpty((String) temp.get("title")));
                        String sliderUrl = (StringUtils.trimToEmpty((String) temp.get("url")));
                        String sliderType = (StringUtils.trimToEmpty((String) temp.get("type")));
                        String sliderHref = (StringUtils.trimToEmpty((String) temp.get("href")));
                        if (StringUtils.equals(StringUtils.trimToEmpty(sliderType), "1")) {
                            jk++;
                %>
                <li><a href="<%=sliderHref %>"><img src="<%=sliderUrl %>" alt="<%=sliderTitle %>"/></a></li>
                <%
                        }
                    }
                %>
            </ul>
        </div>
        <div class="nav3d-wp">
            <ul id="nav3d" class="nav3d">
                <%
                    for (int jjk = 0; jjk < jk; jjk++) {
                %>
                <li onclick="mySlider.pos(<%=jjk%>)">
                    <%=jjk + 1%>
                </li>
                <%} %>
            </ul>
        </div>

    </div>

    <div id="body_top-22">
        <div style="margin-top:0px;float:left">
            <img src="images/index/radio.png" alt="" style="margin:6px auto auto 5px;"/>
        </div>
        <div style="margin:7px auto auto 5px;text-align:left;font-size:16px"><%=contactTitle %>
        </div>
        <div id="lineblue"></div>
        <%
            for (int i = 0; i < contactjson.size(); i++) {
                JSONObject temp = contactjson.getJSONObject(i);
                String contactitem = (StringUtils.trimToEmpty((String) temp.get("text")));
                String ico = (StringUtils.trimToEmpty((String) temp.get("ico")));
        %>
        <div class="lianxi">
            <div style="margin-top:0px;float:left">
                <img src="<%=ico%>" alt="" style="margin:0px auto auto 10px;"/>
            </div>
            <div style="margin:8px auto auto 6px;text-align:left;float:left;font-size:16px"><%=contactitem %>
            </div>
        </div>
        <%}%>


    </div>


</div>
<div class="slider_title">
    <div style="margin-top:0px;float:left">
        <img src="images/index/radio.png" alt="" style="margin:6px auto auto 5px;"/>
    </div>
    <div style="margin:7px auto auto 10px;text-align:left;float:left;font-size:16px;color:#000"><%=ysjTitle %>
    </div>
    <div style="margin:7px auto auto 800px;color:#000;float:left;font-size:16px"><a
            href="<%=moreList.getString("href") %>"><%=moreList.getString("name") %>
    </a></div>
</div>
<div class="slider_03">

    <div class="rollBox">
        <div class="rollBoxLeft">
            <img onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()" onmouseout="ISL_StopDown()" class="img1"
                 src="images/shqm_left_pic.gif" width="20px" height="133px"/>
        </div>
        <div class="Cont" id="ISL_Cont">
            <div class="ScrCont">
                <div id="List1">
                    <!--begin -->
                    <%
                        for (int i = 0; i < yisujiaList.size(); i++) {

                            String ysjname = (StringUtils.trimToEmpty(yisujiaList.get(i).getName()));
                            String ysjimg = (StringUtils.trimToEmpty(yisujiaList.get(i).getImg()));
                            String ysjurl = (StringUtils.trimToEmpty(yisujiaList.get(i).getUrl()));
                            if (yisujiaList.get(i).getDis()) {
                    %>
                    <div class="pic">
                        <a href="<%=ysjurl %>"><img src="<%=ysjimg %>" width="130" height="95"/></a>
                        <a href="<%=ysjurl %>"><%=ysjname %>
                        </a>
                    </div>
                    <%
                            }
                        }
                    %>
                    <!--end -->
                </div>
                <div id="List2"></div>

            </div>
        </div>
        <div class="rollBoxLeft">
            <img onmousedown="ISL_GoUp()" onmouseup="ISL_StopUp()" onmouseout="ISL_StopUp()" class="img2"
                 src="images/shqm_right_pic.gif" width="20px" height="133px"/>
        </div>
    </div>
</div>
<div class="slider_title">
    <div style="margin-top:0px;float:left">
        <img src="images/index/radio.png" alt="" style="margin:6px auto auto 5px;"/>
    </div>
    <div style="margin:7px auto auto 10px;text-align:left;font-size:16px;color:#000"><%=friendsiteTitle %>
    </div>

</div>
<div class="slider_04">
    <%
        for (int i = 0; i < friendsitelist.size(); i++) {

            String imgalt = (StringUtils.trimToEmpty(friendsitelist.get(i).getName()));
            String srcimg = (StringUtils.trimToEmpty(friendsitelist.get(i).getImg()));
            String imgurl = (StringUtils.trimToEmpty(friendsitelist.get(i).getUrl()));
            if (friendsitelist.get(i).getDis()) {
    %>
    <div class="friendsite"><a href="<%=imgurl%>"><img src="<%=srcimg%>" alt="<%=imgalt %>" width="80px" height="34px"/></a>
    </div>
    <%
            }
        }
    %>
</div>

<div class="bottom_01">
    <div style="padding: 5px"><%=copyright %>
    </div>
</div>
<script type="text/javascript" src="js/3d.js"></script>
<script type="text/javascript" src="js/zmd1.js"></script>
<script type="text/javascript" src="js/jquery-edge.min.js"></script>
<script type="text/javascript" src="js/zzsc.js"></script>
<script type="text/javascript" src="js/index_patch.js"></script>

<div class="main">
    <div class="main_c">
        <div id="onService_panel">
            <div class="onService_panel_s">
                <div class="online_boxs">
                    <div class="boxs_t"><span class="boxs_t_l"></span><span class="boxs_t_m"></span><span
                            class="boxs_t_r"></span></div>
                    <div class="boxs_m_l">
                        <div class="boxs_m_r">
                            <div class="box_m_m">
                                <div id="onlineList">
                                    <em class="online_close" id="onlineClose" title="关闭"></em>

                                    <div class="online_open  " id="onlineOpen"></div>
                                    <div id="online_qq_icon" class="online_icon">
                                        <span class="pic"><img src="images/online_qq.png"/></span>
                                        <span class="name">在线客服</span>
                                    </div>

                                    <div id="online_email_icon" class="online_icon">
                                        <span class="pic"><a href="mailto:<%=Email_Info.getString("number")%>"><img
                                                src="images/online_email.png"/></a></span>
                                        <span class="name">发送邮件</span>
                                    </div>
                                    <div id="online_message_icon" class="online_icon">
                                        <span class="pic"><img src="images/online_message.png"/></span>
                                        <span class="name">在线留言</span>
                                    </div>
                                    <div id="online_address_icon" class="online_icon">
                                        <span class="pic"><a href="<%=Company_Info.getString("href")%>"><img
                                                src="images/online_address.png"/></a></span>
                                        <span class="name">企业地标</span>
                                    </div>

                                    <div id="onlineQQTab" class="online_tab">
                                        <div class="online_boxs">
                                            <div class="boxs_t"><span class="boxs_t_l"></span><span
                                                    class="boxs_t_m"></span><span class="boxs_t_r"></span></div>
                                            <div class="boxs_m_l">
                                                <div class="boxs_m_r">
                                                    <div class="box_m_m">
                                                        <div id="onlineQQ" class="online_tab_c">
                                                            <small class="sanjiao"></small>
                                                            <small class="tab_close"></small>
                                                            <dl>
                                                                <dt><%=JSONObject.fromObject(QQ_Info.get(0)).getString("name") %>
                                                                </dt>
                                                                <dd>
                                                                    <a target="_blank"
                                                                       href="http://wpa.qq.com/msgrd?v=3&uin=<%=JSONObject.fromObject(QQ_Info.get(0)).getString("number") %>&site=qq&menu=yes"><img
                                                                            border="0"
                                                                            src="http://wpa.qq.com/pa?p=2:<%=JSONObject.fromObject(QQ_Info.get(0)).getString("number") %>:51"
                                                                            alt="<%=JSONObject.fromObject(QQ_Info.get(0)).getString("alt") %>"
                                                                            title="<%=JSONObject.fromObject(QQ_Info.get(0)).getString("alt") %>"/></a>
                                                                </dd>
                                                                <dt><%=JSONObject.fromObject(QQ_Info.get(1)).getString("name") %>
                                                                </dt>
                                                                <dd>
                                                                    <a target="_blank"
                                                                       href="http://wpa.qq.com/msgrd?v=3&uin=<%=JSONObject.fromObject(QQ_Info.get(1)).getString("number") %>&site=qq&menu=yes"><img
                                                                            border="0"
                                                                            src="http://wpa.qq.com/pa?p=2:<%=JSONObject.fromObject(QQ_Info.get(1)).getString("number") %>:51"
                                                                            alt="<%=JSONObject.fromObject(QQ_Info.get(1)).getString("alt") %>"
                                                                            title="<%=JSONObject.fromObject(QQ_Info.get(1)).getString("alt") %>"/></a>
                                                                </dd>
                                                            </dl>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="boxs_b"><span class="boxs_b_l"></span><span
                                                    class="boxs_b_m"></span><span class="boxs_b_r"></span></div>
                                        </div>
                                    </div>
                                    <div id="onlineMessageTab" class="online_tab">
                                        <div class="online_boxs">
                                            <div class="boxs_t"><span class="boxs_t_l"></span><span
                                                    class="boxs_t_m"></span><span class="boxs_t_r"></span></div>
                                            <div class="boxs_m_l">
                                                <div class="boxs_m_r">
                                                    <div class="box_m_m">
                                                        <div id="onlineMessage" class="online_tab_c">
                                                            <small class="sanjiao"></small>
                                                            <small class="tab_close"></small>
                                                            <dl>
                                                                <dt><textarea onfocus="h_con()" onblur="s_con()"
                                                                              name="content2" id="content2"
                                                                              onkeyup="checkLen(this,200)"></textarea>
                                                                </dt>
                                                                <dd class="text_length">还可输入字符<b>200</b>（限制字符200）</dd>
                                                                <dd><label>您的姓名：</label><input type="text"
                                                                                               class="text_input"
                                                                                               name="name" id="name"
                                                                                               maxlength="20"/></dd>
                                                                <dd><label>您的邮箱：</label><input type="text"
                                                                                               class="text_input"
                                                                                               name="e_mail" id="e_mail"
                                                                                               maxlength="50"/></dd>
                                                                <dd>
                                                                    <label>您的电话：</label><input type="text"
                                                                                               class="text_input"
                                                                                               name="tel" id="tel"
                                                                                               maxlength="30"/>
                                                                    <input type="button" class="submitBut" value="提交"
                                                                           onclick="<%=Liuyan_Info.getString("href") %>"/>
                                                                </dd>
                                                            </dl>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="boxs_b"><span class="boxs_b_l"></span><span
                                                    class="boxs_b_m"></span><span class="boxs_b_r"></span></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="boxs_b"><span class="boxs_b_l"></span><span class="boxs_b_m"></span><span
                            class="boxs_b_r"></span></div>
                </div>
            </div>
        </div>
        <script language="javascript" src="js/online.js"></script>
    </div>
</div>
</body>
</html>
