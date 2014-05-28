package com.realart.interfaces;

/**
 * 基础接口
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface BaseInterface {
    /**
     * mysql数据库链接
     */
    public static final String MYSQL_CONNECTION = "mysql_connection";
    /**
     * md5 key
     */
    public static final String MD5_KEY = "md5_key";
    /**
     * session缓存中的token集合
     */
    public static final String SESSION_TOKEN_LIST = "session_token_list";
    /**
     * TOKEN键
     */
    public static final String TOKEN_KEY = "token";
    /**
     * 用户键
     */
    public static final String USER_KEY = "user";
    /**
     * 是否管理员用户
     */
    public static final String IS_ADMIN_USER = "isAdminUser";
    /**
     * 管理员用户名
     */
    public static final String ADMIN_USER_NAME = "adminUserName";
    /**
     * 管理员用户 逗号隔开
     */
    public static final String ADMIN_USER = "admin_user";
    /**
     * 管理员密码 逗号隔开
     */
    public static final String ADMIN_PASSWORD = "admin_password";
}
