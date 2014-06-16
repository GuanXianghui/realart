package com.realart;

import com.realart.dao.UserDao;
import com.realart.interfaces.UserInterface;
import org.apache.commons.lang.StringUtils;

/**
 * �޸������û�����
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
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("oldPassword:[" + oldPassword + "],newPassword:[" + newPassword + "]," +
                "confirmPassword:[" + confirmPassword + "]");
        //���ֶ�Ϊ��
        if(StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword) || StringUtils.isBlank(confirmPassword)){
            message = "�����������";
            return ERROR;
        }

        //������һ����У��
        if(!StringUtils.equals(oldPassword, getUser().getPassword())){
            message = "��ǰ������������";
            return ERROR;
        }

        //������һ����У��
        if(!StringUtils.equals(newPassword, confirmPassword)){
            message = "�����벻һ�£�";
            return ERROR;
        }

        getUser().setPassword(newPassword);
        UserDao.updateUserPassword(getUser());
        //ˢ��session�����е�user
        refreshSessionUser(getUser());

        message = "�޸�����ɹ���";
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
