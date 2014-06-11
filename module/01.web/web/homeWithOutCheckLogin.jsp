<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.realart.interfaces.BaseInterface" %>
<%@ page import="com.realart.entities.User" %>
<%@ page import="com.realart.interfaces.HomeParamInterface" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="com.realart.utils.PropertyUtil" %>
<%@ page import="com.realart.utils.TokenUtil" %>
<%@ page import="com.realart.utils.ysjScrollParamUtil"%>
<%@ page import="com.realart.utils.ParamUtil"%>
<%@ page import="com.realart.entities.URLTitleName"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * 启动参数
     */
    String contactGuide=ParamUtil.getInstance().getValueByName(HomeParamInterface.CONTACT_GUIDE);
    String headGuide=ParamUtil.getInstance().getValueByName(HomeParamInterface.HEAD_GUIDE);
    String slider01=ParamUtil.getInstance().getValueByName(HomeParamInterface.SLIDER_01);
    //首页 左右滚动标题
    String ysjTitle = ParamUtil.getInstance().getValueByName(HomeParamInterface.YSJ_TITLE);
    //首页 左右滚动更多
    String ysjMore = ParamUtil.getInstance().getValueByName(HomeParamInterface.YSJ_MORE);
    //首页 在线服务
    String  onlineInfo = ParamUtil.getInstance().getValueByName(HomeParamInterface.ONLINE_INFO);
    //首页 左右滚动标题图片列表
    List<URLTitleName> yisujiaList =ysjScrollParamUtil.getInstance().getValueByID(HomeParamInterface.HOME_YSJ_LIST);
    //首页友情链接
    List<URLTitleName> friendsitelist =ysjScrollParamUtil.getInstance().getValueByID(HomeParamInterface.HOME_FRIENDSITE_LIST);
    //首页版权说明
    String copyright = ParamUtil.getInstance().getValueByName(HomeParamInterface.COPYRIGHT_TITLE);
    //首页联系我们标题
    String contactTitle = ParamUtil.getInstance().getValueByName(HomeParamInterface.CONTACT_TITLE);
    //首页 8框显项目标题
    List<URLTitleName> itemList =ysjScrollParamUtil.getInstance().getValueByID(HomeParamInterface.ITEM_GUIDE);
    //首页 8框内容
    List<URLTitleName> itemcontentList =ysjScrollParamUtil.getInstance().getValueByID(HomeParamInterface.ITEM_LIST);
    //首页 下载安卓应用管理
    String apkGuide= ParamUtil.getInstance().getValueByName(HomeParamInterface.APK_GUIDE);
    //首页 导航
    String friendsiteTitle = ParamUtil.getInstance().getValueByName(HomeParamInterface.FRIEND_TITLE);
%>
