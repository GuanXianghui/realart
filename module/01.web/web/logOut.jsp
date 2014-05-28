<%@ page import="com.realart.interfaces.BaseInterface" %><%@
        page contentType="text/html;charset=UTF-8" language="java"
        %><%
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    request.getSession().setAttribute(BaseInterface.USER_KEY, null);
    request.getSession().setAttribute(BaseInterface.IS_ADMIN_USER, Boolean.FALSE);
    request.getSession().setAttribute(BaseInterface.ADMIN_USER_NAME, null);
    String resp = "{isSuccess:true,message:'退出成功！',isRedirect:true,redirectUrl:'" + baseUrl + "loginAdmin.jsp'}";
    response.getWriter().write(resp);
%>