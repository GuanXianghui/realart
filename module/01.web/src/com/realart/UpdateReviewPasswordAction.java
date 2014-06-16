package com.realart;

import com.realart.dao.UserDao;
import com.realart.interfaces.UserInterface;
import org.apache.commons.lang.StringUtils;

/**
 * 修改评论用户密码
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateReviewPasswordAction extends BaseAction implements UserInterface{
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("oldPassword:[" + oldPassword + "],newPassword:[" + newPassword + "]," +
                "confirmPassword:[" + confirmPassword + "]");
        //判字段为空
        if(StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword) || StringUtils.isBlank(confirmPassword)){
            message = "请输入必输项";
            return ERROR;
        }

        //老密码一致性校验
        if(!StringUtils.equals(oldPassword, getUser().getPassword())){
            message = "当前密码输入有误！";
            return ERROR;
        }

        //新密码一致性校验
        if(!StringUtils.equals(newPassword, confirmPassword)){
            message = "新密码不一致！";
            return ERROR;
        }

        getUser().setPassword(newPassword);
        UserDao.updateUserPassword(getUser());
        //刷新session缓存中的user
        refreshSessionUser(getUser());

        message = "修改密码成功！";
        return SUCCESS;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
