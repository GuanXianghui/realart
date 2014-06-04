package com.realart;

import com.realart.dao.QrCodeDao;
import com.realart.dao.UserDao;
import com.realart.entities.QrCode;
import com.realart.entities.User;
import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.QrCodeInterface;
import com.realart.utils.BaseUtil;
import com.realart.utils.ParamUtil;
import com.realart.utils.PropertyUtil;
import org.apache.commons.lang.StringUtils;

/**
 * 删除二维码
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class ResetPasswordAction extends BaseAction implements QrCodeInterface{
    private String name;
    private String userToken;
    private String password;
    private String confirmPassword;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],userToken:[" + userToken + "],password:[" + password + "]," +
                "confirmPassword:[" + confirmPassword + "]");
        //判字段为空
        if(StringUtils.isBlank(name) || StringUtils.isBlank(userToken) || StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPassword)){
            message = "请输入必输项";
            return ERROR;
        }

        //判密码一致
        if(!StringUtils.equals(password, confirmPassword)){
            message = "两次密码不一致";
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

        /**
         * 用户id
         * 管理员用户 用户id设置为0
         * 其他用户 用户id即userId
         */
        int userId = 0;

        //判管理员用户
        if(BaseUtil.isAdminName(name)){
            userId = 0;
        } else {//判其他用户
            //根据姓名查用户
            User user = UserDao.getUserByName(name);
            userId = user.getId();
        }

        //判userToken有效
        if(!BaseUtil.checkUserToken(userId, userToken)){
            message = "您的访问已经失效";
            return ERROR;
        }

        //置userToken失效
        BaseUtil.disableUserToken(userId, userToken, date, time, getIp());

        //修改密码
        if(BaseUtil.isAdminName(name)){//判管理员用户
            ParamUtil.getInstance().updateParam(ParamInterface.ADMIN_PASSWORD, password, "管理员密码密文");
            ParamUtil.refresh();
        } else {//判其他用户
            //根据姓名查用户
            User user = UserDao.getUserByName(name);
            user.setPassword(password);
            UserDao.updateUserPassword(user);
        }
        message = "重置密码成功！";
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
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
