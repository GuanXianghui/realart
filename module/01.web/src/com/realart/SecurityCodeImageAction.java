package com.realart;

import java.io.ByteArrayInputStream;

import com.realart.interfaces.BaseInterface;
import com.realart.utils.securitycode.SecurityCode;
import com.realart.utils.securitycode.SecurityImage;

/**
 * �ṩͼƬ��֤��
 * @version 1.0 2012/08/22
 * @author dongliyang
 */
public class SecurityCodeImageAction extends BaseAction implements BaseInterface {
    
    //ͼƬ��
    private ByteArrayInputStream imageStream;

    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    
    public String execute() throws Exception {
        //�������Hardģʽ�����Բ����ִ�Сд
//        String securityCode = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard, false).toLowerCase();
        
        //��ȡĬ���ѶȺͳ��ȵ���֤��
        String securityCode = SecurityCode.getSecurityCode();
        imageStream = SecurityImage.getImageAsInputStream(securityCode);
        //����session��
        getSession().setAttribute(SESSION_SECURITY_CODE, securityCode);
        return SUCCESS;
    }
}