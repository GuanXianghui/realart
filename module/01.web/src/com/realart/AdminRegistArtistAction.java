package com.realart;

import com.realart.dao.UserDao;
import com.realart.entities.User;
import com.realart.interfaces.UserInterface;
import com.realart.utils.BaseUtil;
import org.apache.commons.lang.StringUtils;

/**
 * ����Աע��������
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
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],password:[" + password + "],confirmPassword:[" + confirmPassword + "]");
        //���ֶ�Ϊ��
        if(StringUtils.isBlank(name) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)){
            message = "�����������";
            return ERROR;
        }
        //�������Ƿ�һ��
        if(!StringUtils.equals(password, confirmPassword)){
            message = "������������";
            return ERROR;
        }
        //�и����ֺ��û������Ƿ��Ѵ���
        boolean isExist = UserDao.isNameExist(name);
        if(isExist || BaseUtil.isAdminName(name)){
            message = "���û����ѱ��ã�������û�����";
            return ERROR;
        }

        //�����û�
        User user = new User(name, USER_TYPE_ARTIST, password, StringUtils.EMPTY, StringUtils.EMPTY,
                StringUtils.EMPTY, StringUtils.EMPTY, "{}", USER_STATE_NEED_CHECK, StringUtils.EMPTY, date, time, getIp());
        UserDao.insertUser(user);

        //ˢ��session�����е�user
        refreshSessionUser(user);
        //���session�����еĹ���Ա�û�
        clearSessionAdminUser();

        message = "�����û��ɹ���";
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
