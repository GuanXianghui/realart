package com.realart;

import com.realart.dao.UserDao;
import com.realart.entities.User;
import com.realart.interfaces.UserInterface;
import org.apache.commons.lang.StringUtils;

/**
 * 艺术家登陆
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class LoginArtistAction extends BaseAction implements UserInterface{
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

        //根据姓名和用户类型查用户
        User user = UserDao.getUserByName(name);
        if(user == null){
            message = "不存在该用户名:[" + name + "]";
            return ERROR;
        }

        //判密码是否一致
        if(!StringUtils.equals(password, user.getPassword())){
            message = "你的密码输入有误";
            return ERROR;
        }

        //刷新session缓存中的user
        refreshSessionUser(user);
        //清空session缓存中的管理员用户
        clearSessionAdminUser();

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
