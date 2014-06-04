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
public class LoginAction extends BaseAction implements UserInterface{
    private String name;
    private String password;
    private String jumpUrl;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],password:[" + password + "],jumpUrl:[" + jumpUrl + "]");
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

        //判名字存在 管理员名字 或者 普通用户
        if(!BaseUtil.isAdminName(name) && !UserDao.isNameExist(name)){
            message = "该用户名不存在";
            return ERROR;
        }

        //判管理员用户
        if(BaseUtil.isAdminName(name)){
            try{
                //校验管理员用户
                BaseUtil.checkAdminUser(name, password);
            } catch (Exception e){
                message = e.getMessage();
                return ERROR;
            }
            //管理员用户 登录成功
            //刷新session缓存中的user
            refreshSessionUser(null);
            //刷新session缓存中的管理员用户名
            refreshSessionAdminUser(name);
            message = "登录成功！";
            if(StringUtils.isNotBlank(jumpUrl)){
                response.sendRedirect(jumpUrl);
            } else {
                response.sendRedirect("indexImg.jsp");
            }
        } else {
            //根据姓名和用户类型查用户
            User user = UserDao.getUserByName(name);
            //判密码是否一致
            if(!StringUtils.equals(password, user.getPassword())){
                message = "你的密码输入有误";
                return ERROR;
            }
            //普通用户 登录成功
            //刷新session缓存中的user
            refreshSessionUser(user);
            //清空session缓存中的管理员用户
            clearSessionAdminUser();
            message = "登录成功！";
            if(StringUtils.isNotBlank(jumpUrl)){
                response.sendRedirect(jumpUrl);
            } else {
                if(user.getUserType() == USER_TYPE_ARTIST){
                    response.sendRedirect("artistConsole.jsp");
                } else if(user.getUserType() == USER_TYPE_REVIEW){
                    response.sendRedirect("createReview.jsp");
                }
            }
        }
        return null;
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

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
