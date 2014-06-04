package com.realart;

import com.realart.dao.UserDao;
import com.realart.entities.User;
import com.realart.entities.UserToken;
import com.realart.interfaces.BaseInterface;
import com.realart.utils.*;
import org.apache.commons.lang.StringUtils;

/**
 * 忘记密码
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class ForgetPasswordAction extends BaseAction implements BaseInterface {
    private String name;
    private String email;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],email:[" + email + "]");
        //判字段为空
        if(StringUtils.isBlank(name) || StringUtils.isBlank(email)){
            message = "请输入必输项";
            write(getErrorJsonResp());
            return null;
        }

        //判图片验证码是否正确
        if(!checkSecurityCode()){
            message = "图片验证码输入错误";
            write(getErrorJsonResp());
            return null;
        }

        //判名字存在 管理员名字 或者 普通用户
        if(!BaseUtil.isAdminName(name) && !UserDao.isNameExist(name)){
            message = "该用户名不存在";
            write(getErrorJsonResp());
            return null;
        }

        /**
         * 用户id
         * 管理员用户 用户id设置为0
         * 其他用户 用户id即userId
         */
        int userId = 0;

        //判管理员用户
        if(BaseUtil.isAdminName(name)){
            //判邮箱输入错误
            if(!StringUtils.equals(email, PropertyUtil.getInstance().getProperty(EMAIL_NAME))){
                message = "邮箱不匹配";
                write(getErrorJsonResp());
                return null;
            }
            //邮箱输入正确
            userId = 0;
        } else {//判其他用户
            //根据姓名查用户
            User user = UserDao.getUserByName(name);
            if(StringUtils.isBlank(user.getEmail())){
                message = "该用户未配置邮箱，暂不能重置密码";
                write(getErrorJsonResp());
                return null;
            }
            if(!StringUtils.equals(email, user.getEmail())){
                message = "邮箱不匹配";
                write(getErrorJsonResp());
                return null;
            }
            //邮箱输入正确
            userId = user.getId();
        }

        //创建用户TOKEN
        UserToken userToken = BaseUtil.createUserToken(userId, date, time ,getIp());
        //忘记密码邮件title
        String forgetPasswordEmailTitle = "重置密码-来自真艺网";
        //忘记密码邮件内容
        String forgetPasswordEmailContent = "<a href=\"http://www.jdzzyw.com/resetPassword.jsp?name=" + name +
                "&userToken=" + userToken.getToken() + "\">点击重置密码</a>";
        //发送邮件
        EmailUtils.sendEmail(forgetPasswordEmailTitle, forgetPasswordEmailContent, email);
        //返回结果
        message = "发送重置密码邮件成功，请查收邮件！";
        String resp = "{isSuccess:true,message:'" + message + "',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
        write(resp);
        return null;
    }

    /**
     * 犯或错误json结果串
     * @return
     */
    private String getErrorJsonResp(){
        //返回结果
        String resp = "{isSuccess:false,message:'" + message + "',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
        return resp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
