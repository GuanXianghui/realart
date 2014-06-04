package com.realart;

import com.realart.dao.UserDao;
import com.realart.entities.User;
import com.realart.entities.UserToken;
import com.realart.interfaces.BaseInterface;
import com.realart.utils.*;
import org.apache.commons.lang.StringUtils;

/**
 * ��������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class ForgetPasswordAction extends BaseAction implements BaseInterface {
    private String name;
    private String email;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],email:[" + email + "]");
        //���ֶ�Ϊ��
        if(StringUtils.isBlank(name) || StringUtils.isBlank(email)){
            message = "�����������";
            write(getErrorJsonResp());
            return null;
        }

        //��ͼƬ��֤���Ƿ���ȷ
        if(!checkSecurityCode()){
            message = "ͼƬ��֤���������";
            write(getErrorJsonResp());
            return null;
        }

        //�����ִ��� ����Ա���� ���� ��ͨ�û�
        if(!BaseUtil.isAdminName(name) && !UserDao.isNameExist(name)){
            message = "���û���������";
            write(getErrorJsonResp());
            return null;
        }

        /**
         * �û�id
         * ����Ա�û� �û�id����Ϊ0
         * �����û� �û�id��userId
         */
        int userId = 0;

        //�й���Ա�û�
        if(BaseUtil.isAdminName(name)){
            //�������������
            if(!StringUtils.equals(email, PropertyUtil.getInstance().getProperty(EMAIL_NAME))){
                message = "���䲻ƥ��";
                write(getErrorJsonResp());
                return null;
            }
            //����������ȷ
            userId = 0;
        } else {//�������û�
            //�����������û�
            User user = UserDao.getUserByName(name);
            if(StringUtils.isBlank(user.getEmail())){
                message = "���û�δ�������䣬�ݲ�����������";
                write(getErrorJsonResp());
                return null;
            }
            if(!StringUtils.equals(email, user.getEmail())){
                message = "���䲻ƥ��";
                write(getErrorJsonResp());
                return null;
            }
            //����������ȷ
            userId = user.getId();
        }

        //�����û�TOKEN
        UserToken userToken = BaseUtil.createUserToken(userId, date, time ,getIp());
        //���������ʼ�title
        String forgetPasswordEmailTitle = "��������-����������";
        //���������ʼ�����
        String forgetPasswordEmailContent = "<a href=\"http://www.jdzzyw.com/resetPassword.jsp?name=" + name +
                "&userToken=" + userToken.getToken() + "\">�����������</a>";
        //�����ʼ�
        EmailUtils.sendEmail(forgetPasswordEmailTitle, forgetPasswordEmailContent, email);
        //���ؽ��
        message = "�������������ʼ��ɹ���������ʼ���";
        String resp = "{isSuccess:true,message:'" + message + "',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
        write(resp);
        return null;
    }

    /**
     * �������json�����
     * @return
     */
    private String getErrorJsonResp(){
        //���ؽ��
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
