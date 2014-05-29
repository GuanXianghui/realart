package com.realart;

import com.realart.interfaces.QrCodeInterface;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;

/**
 * ɾ����ά��logo
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteQrCodeLogoAction extends BaseAction implements QrCodeInterface{
    private String qrLogo;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("qrLogo:[" + qrLogo + "]");

        //�������ϵ�·��
        String qrCodePhotoPath = ServletActionContext.getServletContext().getRealPath("images/qr_logo/") + "/";
        File[] files = new File(qrCodePhotoPath).listFiles();
        for(int i=0;i<files.length;i++){
            if(StringUtils.equals(StringUtils.EMPTY + (i+1), qrLogo)){
                logger.info(files[i].delete());
            }
        }

        message = "ɾ����ά��logo�ɹ���";
        return SUCCESS;
    }

    public String getQrLogo() {
        return qrLogo;
    }

    public void setQrLogo(String qrLogo) {
        this.qrLogo = qrLogo;
    }
}
