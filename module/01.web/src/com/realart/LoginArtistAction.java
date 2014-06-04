package com.realart;

import com.realart.dao.UserDao;
import com.realart.entities.User;
import com.realart.interfaces.UserInterface;
import org.apache.commons.lang.StringUtils;

/**
 * �����ҵ�½
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class LoginArtistAction extends BaseAction implements UserInterface{
    private String name;
    private String password;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],password:[" + password + "]");
        //���ֶ�Ϊ��
        if(StringUtils.isBlank(name) || StringUtils.isBlank(password)){
            message = "�����������";
            return ERROR;
        }

        //�����������û����Ͳ��û�
        User user = UserDao.getUserByName(name);
        if(user == null){
            message = "�����ڸ��û���:[" + name + "]";
            return ERROR;
        }

        //�������Ƿ�һ��
        if(!StringUtils.equals(password, user.getPassword())){
            message = "���������������";
            return ERROR;
        }

        //ˢ��session�����е�user
        refreshSessionUser(user);
        //���session�����еĹ���Ա�û�
        clearSessionAdminUser();

        message = "��¼�ɹ���";
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
