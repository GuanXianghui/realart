<%@ page import="com.realart.dao.QrCodeDao" %>
<%@ page import="com.realart.entities.QrCode" %>
<%@ page import="java.util.List" %>
<%@ page import="com.realart.interfaces.QrCodeInterface" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminHeader.jsp" %>
    <%
        //外层
        outLayer = "序列号模块";
        //内层
        inLayer = "序列号维护";
        //当前页数
        String pageStr = StringUtils.trimToEmpty(request.getParameter("pageNum"));
        //当前页数
        int pageNum;
        try {
            pageNum = Integer.parseInt(pageStr);
        } catch (Exception e) {
            pageNum = 1;
        }
        //当前状态
        String stateStr = StringUtils.trimToEmpty(request.getParameter("state"));
        //状态
        int state;
        try {
            state = Integer.parseInt(stateStr);
        } catch (Exception e) {
            state = 0;
        }
        //二维码页面大小
        int pageSize = Integer.parseInt(PropertyUtil.getInstance().getProperty(BaseInterface.QR_CODE_PAGE_SIZE));
        /**
         * 根据状态查二维码量
         * 如果state>0带上作为条件
         */
        int count = QrCodeDao.countQrCodesByState(state);
        //是否为空
        boolean isEmpty = count == 0;
        //总页数
        int pageCount = (count - 1) / pageSize + 1;
        //删除最后一条，可能会少掉一页
        if(pageNum > pageCount){
            pageNum = pageCount;
        }
        //二维码列表
        List<QrCode> qrCodes = QrCodeDao.queryQrCodesByStateAndPayNum(state, pageNum);
    %>
<html>
<head>
    <title>序列号维护</title>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/qrCode.js"></script>
    <!-- jQuery 颜色选择器 Spectrum -->
    <script type="text/javascript" src="<%=baseUrl%>scripts/spectrum.js"></script>
    <link rel="stylesheet" href="css/spectrum.css" type="text/css" media="screen"/>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript" src="scripts/facebox.js"></script>
    <script type="text/javascript">
        //当前页数
        var pageNum = <%=pageNum%>;
        //选择logo
        var chooseLogo = 0;
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
            <h3>生成序列号并生成二维码</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <span style="display: none;">
                    <form name="uploadQrCodeLogoForm" method="post" autocomplete="off"
                          action="uploadQrCodeLogo.do?token=<%=token%>" enctype="multipart/form-data">
                        <input type="file" name="qrLogo" id="upload_qr_code_logo"
                               onchange="document.forms['uploadQrCodeLogoForm'].submit()">
                    </form>
                    <form name="deleteQrCodeLogoForm" method="post" action="deleteQrCodeLogo.do?token=<%=token%>">
                        <input type="hidden" name="qrLogo" id="delete_qr_code_logo">
                    </form>
                </span>
                <form name="generateQrCodeForm" method="post" action="generateQrCode.do?token=<%=token%>">
                    <table>
                        <tr>
                            <td>二维码排错率</td>
                            <td>
                                <select class="text-input small-input" name="antiError" id="antiError">
                                    <option value="L">低(7%)</option>
                                    <option value="M" selected="selected">中(15%)</option>
                                    <option value="Q">稍高(25%)</option>
                                    <option value="H">高(30%)</option>
                                </select>(排错率越高可存储的信息越少，但对二维码清晰度的要求越小)
                            </td>
                        </tr>
                        <tr>
                            <td>二维码尺寸</td>
                            <td>
                                <select class="text-input small-input" name="size" id="size">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3" selected="selected">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                    <option value="24">24</option>
                                    <option value="25">25</option>
                                    <option value="26">26</option>
                                    <option value="27">27</option>
                                    <option value="28">28</option>
                                    <option value="29">29</option>
                                    <option value="30">30</option>
                                    <option value="31">31</option>
                                    <option value="32">32</option>
                                    <option value="33">33</option>
                                    <option value="34">34</option>
                                    <option value="35">35</option>
                                    <option value="36">36</option>
                                    <option value="37">37</option>
                                    <option value="38">38</option>
                                    <option value="39">39</option>
                                    <option value="40">40</option>
                                </select>(取值范围1-40，值越大尺寸越大，可存储的信息越大)
                            </td>
                        </tr>
                        <tr>
                            <td>背景颜色</td>
                            <td><input type='text' id="bgColor" name="bgColor"/></td>
                        </tr>
                        <tr>
                            <td>前景颜色</td>
                            <td><input type='text' id="frontColor" name="frontColor"/></td>
                        </tr>
                        <tr>
                            <td>形态</td>
                            <td>
                                <select class="text-input small-input" name="type" id="type">
                                    <option value="1" selected="selected">液态</option>
                                    <option value="2">直角</option>
                                    <option value="3">圆形</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <input type="hidden" name="qrLogo" id="qrLogo">
                            <td>插入logo(可选)</td>
                            <td>
                                <%
                                    File file = new File(ServletActionContext.getServletContext().getRealPath("images/qr_logo/") + "/");
                                    String[] files = file.list();
                                    for(int i=0;i<files.length;i++){
                                        String tempFile = files[i];
                                %>
                                <img onclick="chooseQrCodeLogo(this, <%=i+1%>)" class="choose_logo"
                                     src="images/qr_logo/<%=tempFile%>" width="50" style="cursor: pointer;">
                                <%
                                    }
                                %>
                                <input class="button" type="button" onclick="uploadQrCodeLogo();" value="上传" />
                                <input class="button" type="button" onclick="deleteQrCodeLogo();" value="删除" />
                            </td>
                        </tr>
                        <tr>
                            <td>logo边缘(可选)</td>
                            <td>
                                <select class="text-input small-input" name="logoBorderType" id="logoBorderType">
                                    <option value="1" selected="selected">无边框</option>
                                    <option value="2">直角</option>
                                    <option value="3">圆角</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>logo边缘颜色(可选)</td>
                                <td><input type='text' id="logoBorderColor" name="logoBorderColor"/></td>
                        </tr>
                        <tr>
                            <td>相关信息</td>
                            <td><textarea class="text-input large-input" name="info"></textarea></td>
                        </tr>
                        <tr>
                            <td>生成序列号</td>
                            <td>
                                <input class="button" type="button" onclick="preViewQrCode();" value="预览" />
                                <input class="button" type="button" onclick="generateQrCode();" value="生成" />
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>

    <a id="showBigImgA" class="shortcut-button" href="#bigImgDiv" rel="modal" style="display: none;"></a>
    <div id="bigImgDiv" style="display: none;" align="center">
        <img id="bigImg" width="450">
    </div>

    <div class="content-box">
        <div class="content-box-header">
            <h3>序列号</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <form>
                    <table id="cwr_table">
                        <thead>
                        <tr>
                            <th>序列号</th>
                            <th>二维码</th>
                            <th>绑定艺术品</th>
                            <th>相关信息</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <td colspan="5">
                                <div class="pagination">
                                    <a href="javascript: jump2page(1)" title="首页">&laquo; 首页</a>
                                    <%
                                        if(pageNum > 1){
                                    %>
                                    <a href="javascript: jump2page(<%=pageNum-1%>)" title="上一页">&laquo; 上一页</a>
                                    <%
                                        }
                                    %>
                                    <%
                                        //显示前2页，本页，后2页
                                        for(int i=pageNum-2;i<pageNum+3;i++){
                                            if(i >= 1 && i <= pageCount){
                                    %>
                                    <a href="javascript: jump2page(<%=i%>)" class="number<%=(i==pageNum)?" current":""%>" title="<%=i%>"><%=i%></a>
                                    <%
                                            }
                                        }
                                    %>
                                    <%
                                        if(pageNum < pageCount){
                                    %>
                                    <a href="javascript: jump2page(<%=pageNum+1%>)" title="下一页">下一页 &raquo;</a>
                                    <%
                                        }
                                    %>
                                    <a href="javascript: jump2page(<%=pageCount%>)" title="尾页">尾页 &raquo;</a>
                                </div>
                                <div class="clear"></div>
                            </td>
                        </tr>
                        </tfoot>
                        <tbody>
                        <%
                            //判是否为空
                            if (isEmpty) {
                        %>
                        <tr>
                            <td colspan="5">
                                没找到序列号
                            </td>
                        </tr>
                        <%
                        } else {//非空
                            for(int i=0;i<qrCodes.size();i++)
                            {
                        %>
                        <tr>
                            <td>
                                <%=qrCodes.get(i).getUuid()%>
                            </td>
                            <td>
                                <img style="cursor: pointer;" src="/<%=qrCodes.get(i).getImgPath()%>" width="50"
                                     onclick="showBigImg('/<%=qrCodes.get(i).getImgPath()%>')">
                            </td>
                            <td>
                                <%
                                    if(qrCodes.get(i).getState() == QrCodeInterface.STATE_NOT_USE){
                                %>
                                未绑定艺术品
                                <%
                                } else {
                                %>
                                <input class="button" type="button" onclick="showArt(<%=qrCodes.get(i).getArtId()%>)" value="已绑定艺术品[id=<%=qrCodes.get(i).getArtId()%>]">
                                <%
                                    }
                                %>
                            </td>
                            <td>
                                <textarea class="text-input large-input"><%=qrCodes.get(i).getInfo()%></textarea>
                            </td>
                            <td>
                                <input class="button" type="button" onclick="refreshStyle(<%=qrCodes.get(i).getId()%>);" value="刷新样式">
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        </tbody>
                    </table>
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