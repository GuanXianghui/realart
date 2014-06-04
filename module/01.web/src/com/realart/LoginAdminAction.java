package com.realart;

import com.realart.dao.UserDao;
import com.realart.entities.User;
import com.realart.interfaces.UserInterface;
import com.realart.utils.BaseUtil;
import org.apache.commons.lang.StringUtils;

/**
 * 管理员登录
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class LoginAdminAction extends BaseAction implements UserInterface{
    private String name;
    private String password;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],password:[" + password + "]");
        //判字段为空
        if(StringUtils.isBlank(name) || StringUtils.isBlank(password)){
            message = "请输入必输项";
            return ERROR;
        }

        //判图片验证码是否正确
        if(!checkSecurityCode()){
            message = "图片验证码输入错误";
            return ERROR;
        }

        try{
            //校验管理员用户
            BaseUtil.checkAdminUser(name, password);
        } catch (Exception e){
            message = e.getMessage();
            return ERROR;
        }

        //刷新session缓存中的user
        refreshSessionUser(null);
        //刷新session缓存中的管理员用户名
        refreshSessionAdminUser(name);

        message = "登录成功！";
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
}
