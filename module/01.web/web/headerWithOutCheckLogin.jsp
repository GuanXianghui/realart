<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.realart.entities.User" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="com.realart.utils.PropertyUtil" %>
<%@ page import="com.realart.utils.TokenUtil" %>
<%@ page import="com.realart.utils.ParamUtil" %>
<%@ page import="com.realart.interfaces.ParamInterface" %>
<%@ page import="com.realart.interfaces.QrCodeInterface" %>
<%@ page import="com.realart.interfaces.BaseInterface" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //域名链接
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    //md5 key
    String md5Key = PropertyUtil.getInstance().getProperty(BaseInterface.MD5_KEY);
    //token串
    String token = TokenUtil.createToken(request);
    //session中获取user
    User user = (User)request.getSession().getAttribute(BaseInterface.USER_KEY);
    //是否已经登录
    boolean isLogin = user != null;
    //是否管理员已登录
    boolean isAdminLogin = Boolean.TRUE.equals(request.getSession().getAttribute(BaseInterface.IS_ADMIN_USER));
    //管理员用户名
    String adminUserName = (String)request.getSession().getAttribute(BaseInterface.ADMIN_USER_NAME);
    //消息
    String message = StringUtils.trimToEmpty((String)request.getAttribute("message"));
    if(StringUtils.isBlank(message)){
        message = StringUtils.trimToEmpty(request.getParameter("message"));
    }
    //跳转地址
    String jumpUrl = StringUtils.trimToEmpty(request.getParameter("jumpUrl"));
%>

<%
    /**
     * 启动参数
     */
    //首页 底图
    String indexBgImg = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_BG_IMG);
    //首页 logo
    String indexLogoImg = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_LOGO_IMG);
    //首页 logo 宽度
    String indexLogoWidth = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_LOGO_WIDTH);
    //首页 logo 高度
    String indexLogoHeight = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_LOGO_HEIGHT);
    //首页 真艺网
    String indexRealartImg = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_REALART_IMG);
    //首页 真艺网 宽度
    String indexRealartWidth = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_REALART_WIDTH);
    //首页 真艺网 高度
    String indexRealartHeight = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_REALART_HEIGHT);
    //首页 导航
    String indexGuide = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_GUIDE);
    //评论用户注册项
    String reviewUserRegistItems = ParamUtil.getInstance().getValueByName(ParamInterface.REVIEW_USER_REGIST_ITEMS);
    //艺术家注册项
    String artistUserRegistItems = ParamUtil.getInstance().getValueByName(ParamInterface.ARTIST_USER_REGIST_ITEMS);
    //审核失败原因
    String checkWrongReason = ParamUtil.getInstance().getValueByName(ParamInterface.CHECK_WRONG_REASON);
    //艺术品绑定二维码地址前缀
    String qrCodeUrlPrefix = QrCodeInterface.QR_CODE_URL_PREFIX;
%>
<%
    /**
     * 二维码相关
     */
    String qr = StringUtils.trimToEmpty(request.getParameter("qr"));
%>
<script type="text/javascript">
    //域名链接
    var baseUrl = "<%=baseUrl%>";
    //md5 key
    var md5Key = "<%=md5Key%>";
    //token穿
    var token = "<%=token%>";
    //是否已经登录
    var isLogin = <%=isLogin%>;
    //弹出消息框
    var message = '<%=message%>';
</script>
<!-- 图标 -->
<link rel="shortcut icon" type="image/x-icon" href="/images/realart_logo.png" />