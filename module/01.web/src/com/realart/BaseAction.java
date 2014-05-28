package com.realart;

import com.opensymphony.xwork2.ActionSupport;
import com.realart.entities.User;
import com.realart.interfaces.BaseInterface;
import com.realart.utils.DateUtil;
import com.realart.utils.IPAddressUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Date;

/**
 * ����Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 12:44
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    /**
     * ��־������
     */
    Logger logger = Logger.getLogger(BaseAction.class);

    /**
     * ��ǰʱ��
     */
    String date;
    String time;
    String defaultDateTime;

    /**
     * token�� һ������ֵ�����������StrutsFilter
     */
    String token;

    /**
     * ��Ϣ
     */
    String message;

    /**
     * request��response
     */
    HttpServletRequest request;
    HttpServletResponse response;

    /**
     * ���캯��
     */
    public BaseAction() {
        this.date = DateUtil.getNowDate();
        this.time = DateUtil.getNowTime();
        this.defaultDateTime = DateUtil.getDefaultDateTime(new Date());
    }

    /**
     * ��ȡip
     * @return
     */
    public String getIp() {
        return IPAddressUtil.getIPAddress(request);
    }

    /**
     * ��ȡsession
     * @return
     */
    public HttpSession getSession() {
        return request.getSession();
    }

    /**
     * ��ȡapplication
     * @return
     */
    public ServletContext getApplication() {
        return request.getSession().getServletContext();
    }

    /**
     * ��ȡUser
     * @return
     */
    public User getUser() {
        return (User) getSession().getAttribute(BaseInterface.USER_KEY);
    }

    /**
     * ˢ��session�����е�user
     * @param user
     */
    public void refreshSessionUser(User user) {
        getSession().setAttribute(BaseInterface.USER_KEY, user);
    }

    /**
     * ˢ��session�����еĹ���Ա�û��� todo
     * @param adminUserName
     */
    public void refreshSessionAdminUser(String adminUserName) {
        getSession().setAttribute(BaseInterface.IS_ADMIN_USER, Boolean.TRUE);
        getSession().setAttribute(BaseInterface.ADMIN_USER_NAME, adminUserName);
    }

    /**
     * ���session�����еĹ���Ա�û� todo
     */
    public void clearSessionAdminUser(){
        getSession().setAttribute(BaseInterface.IS_ADMIN_USER, Boolean.FALSE);
        getSession().setAttribute(BaseInterface.ADMIN_USER_NAME, null);
    }

    /**
     * ajaxд�����
     * @param resp
     */
    public void write(String resp) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(resp);
        writer.flush();
        writer.close();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
}