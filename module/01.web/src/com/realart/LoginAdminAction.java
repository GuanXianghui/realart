package com.realart;

import com.realart.dao.UserDao;
import com.realart.entities.User;
import com.realart.interfaces.UserInterface;
import com.realart.utils.BaseUtil;
import org.apache.commons.lang.StringUtils;

/**
 * ����Ա��¼
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class LoginAdminAction extends BaseAction implements UserInterface{
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

        //��ͼƬ��֤���Ƿ���ȷ
        if(!checkSecurityCode()){
            message = "ͼƬ��֤���������";
            return ERROR;
        }

        try{
            //У�����Ա�û�
            BaseUtil.checkAdminUser(name, password);
        } catch (Exception e){
            message = e.getMessage();
            return ERROR;
        }

        //ˢ��session�����е�user
        refreshSessionUser(null);
        //ˢ��session�����еĹ���Ա�û���
        refreshSessionAdminUser(name);

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
