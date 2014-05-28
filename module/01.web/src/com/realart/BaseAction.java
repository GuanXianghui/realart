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
 * 基础Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 12:44
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    /**
     * 日志处理器
     */
    Logger logger = Logger.getLogger(BaseAction.class);

    /**
     * 当前时间
     */
    String date;
    String time;
    String defaultDateTime;

    /**
     * token串 一定会有值，否则过不了StrutsFilter
     */
    String token;

    /**
     * 消息
     */
    String message;

    /**
     * request，response
     */
    HttpServletRequest request;
    HttpServletResponse response;

    /**
     * 构造函数
     */
    public BaseAction() {
        this.date = DateUtil.getNowDate();
        this.time = DateUtil.getNowTime();
        this.defaultDateTime = DateUtil.getDefaultDateTime(new Date());
    }

    /**
     * 获取ip
     * @return
     */
    public String getIp() {
        return IPAddressUtil.getIPAddress(request);
    }

    /**
     * 获取session
     * @return
     */
    public HttpSession getSession() {
        return request.getSession();
    }

    /**
     * 获取application
     * @return
     */
    public ServletContext getApplication() {
        return request.getSession().getServletContext();
    }

    /**
     * 获取User
     * @return
     */
    public User getUser() {
        return (User) getSession().getAttribute(BaseInterface.USER_KEY);
    }

    /**
     * 刷新session缓存中的user
     * @param user
     */
    public void refreshSessionUser(User user) {
        getSession().setAttribute(BaseInterface.USER_KEY, user);
    }

    /**
     * 刷新session缓存中的管理员用户名 todo
     * @param adminUserName
     */
    public void refreshSessionAdminUser(String adminUserName) {
        getSession().setAttribute(BaseInterface.IS_ADMIN_USER, Boolean.TRUE);
        getSession().setAttribute(BaseInterface.ADMIN_USER_NAME, adminUserName);
    }

    /**
     * 清空session缓存中的管理员用户 todo
     */
    public void clearSessionAdminUser(){
        getSession().setAttribute(BaseInterface.IS_ADMIN_USER, Boolean.FALSE);
        getSession().setAttribute(BaseInterface.ADMIN_USER_NAME, null);
    }

    /**
     * ajax写出结果
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