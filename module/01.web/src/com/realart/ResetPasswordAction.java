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
 * ɾ����ά��
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
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],userToken:[" + userToken + "],password:[" + password + "]," +
                "confirmPassword:[" + confirmPassword + "]");
        //���ֶ�Ϊ��
        if(StringUtils.isBlank(name) || StringUtils.isBlank(userToken) || StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPassword)){
            message = "�����������";
            return ERROR;
        }

        //������һ��
        if(!StringUtils.equals(password, confirmPassword)){
            message = "�������벻һ��";
            return ERROR;
        }

        //��ͼƬ��֤���Ƿ���ȷ
        if(!checkSecurityCode()){
            message = "ͼƬ��֤���������";
            return ERROR;
        }

        //�����ִ��� ����Ա���� ���� ��ͨ�û�
        if(!BaseUtil.isAdminName(name) && !UserDao.isNameExist(name)){
            message = "���û���������";
            return ERROR;
        }

        /**
         * �û�id
         * ����Ա�û� �û�id����Ϊ0
         * �����û� �û�id��userId
         */
        int userId = 0;

        //�й���Ա�û�
        if(BaseUtil.isAdminName(name)){
            userId = 0;
        } else {//�������û�
            //�����������û�
            User user = UserDao.getUserByName(name);
            userId = user.getId();
        }

        //��userToken��Ч
        if(!BaseUtil.checkUserToken(userId, userToken)){
            message = "���ķ����Ѿ�ʧЧ";
            return ERROR;
        }

        //��userTokenʧЧ
        BaseUtil.disableUserToken(userId, userToken, date, time, getIp());

        //�޸�����
        if(BaseUtil.isAdminName(name)){//�й���Ա�û�
            ParamUtil.getInstance().updateParam(ParamInterface.ADMIN_PASSWORD, password, "����Ա��������");
            ParamUtil.refresh();
        } else {//�������û�
            //�����������û�
            User user = UserDao.getUserByName(name);
            user.setPassword(password);
            UserDao.updateUserPassword(user);
        }
        message = "��������ɹ���";
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
