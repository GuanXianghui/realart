package com.realart;

import com.realart.dao.UserDao;
import com.realart.entities.User;
import com.realart.interfaces.UserInterface;
import com.realart.utils.BaseUtil;
import org.apache.commons.lang.StringUtils;

/**
 * 管理员注册艺术家
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class AdminRegistArtistAction extends BaseAction implements UserInterface{
    private String name;
    private String password;
    private String confirmPassword;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],password:[" + password + "],confirmPassword:[" + confirmPassword + "]");
        //判字段为空
        if(StringUtils.isBlank(name) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)){
            message = "请输入必输项";
            return ERROR;
        }
        //判密码是否一致
        if(!StringUtils.equals(password, confirmPassword)){
            message = "密码输入有误";
            return ERROR;
        }
        //判该名字和用户类型是否已存在
        boolean isExist = UserDao.isNameExist(name);
        if(isExist || BaseUtil.isAdminName(name)){
            message = "该用户名已被用，请更换用户名！";
            return ERROR;
        }

        //创建用户
        User user = new User(name, USER_TYPE_ARTIST, password, StringUtils.EMPTY, StringUtils.EMPTY,
                StringUtils.EMPTY, StringUtils.EMPTY, "{}", USER_STATE_NEED_CHECK, StringUtils.EMPTY, date, time, getIp());
        UserDao.insertUser(user);

        //刷新session缓存中的user
        refreshSessionUser(user);
        //清空session缓存中的管理员用户
        clearSessionAdminUser();

        message = "创建用户成功！";
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
