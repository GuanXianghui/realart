package com.realart;

import com.realart.interfaces.QrCodeInterface;
import com.realart.utils.FileUtil;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.Date;

/**
 * �ϴ���ά��logo
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UploadQrCodeLogoAction extends BaseAction implements QrCodeInterface{
    private File qrLogo;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("qrLogo:[" + qrLogo + "]");
        if(null == qrLogo){
            message = "������δ���ܵ�logo";
            return ERROR;
        }
        if(0 == qrLogo.length()){
            message = "logo��С����Ϊ��";
            return ERROR;
        }

        //��ά��logo����
        String qrCodePhotoName = new Date().getTime() + ".jpg";
        //�������ϵ�·��
        String qrCodePhotoPath = ServletActionContext.getServletContext().getRealPath("images/qr_logo/") + "/" + qrCodePhotoName;
        //�����ļ�
        FileUtil.copy(qrLogo, new File(qrCodePhotoPath));

        message = "�ϴ���ά��logo�ɹ���";
        return SUCCESS;
    }

    public File getQrLogo() {
        return qrLogo;
    }

    public void setQrLogo(File qrLogo) {
        this.qrLogo = qrLogo;
    }
}
