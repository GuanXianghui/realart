package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.QrCodeInterface;
import com.realart.utils.ParamUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;

/**
 * �����ά��Ĭ������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class SaveDefaultQrCodeAction extends BaseAction implements QrCodeInterface{
    private String defaultQrCode;
    private String defaultInfo;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("defaultQrCode:[" + defaultQrCode + "],defaultInfo:[" + defaultInfo + "]");

        ParamUtil.getInstance().updateParam(ParamInterface.DEFAULT_QR_CODE, defaultQrCode, "Ĭ�϶�ά������");
        ParamUtil.getInstance().updateParam(ParamInterface.DEFAULT_QR_CODE_INFO, defaultInfo, "Ĭ�϶�ά�������Ϣ");
        ParamUtil.refresh();

        message = "�����ά��Ĭ�����óɹ���";
        return SUCCESS;
    }

    public String getDefaultQrCode() {
        return defaultQrCode;
    }

    public void setDefaultQrCode(String defaultQrCode) {
        this.defaultQrCode = defaultQrCode;
    }

    public String getDefaultInfo() {
        return defaultInfo;
    }

    public void setDefaultInfo(String defaultInfo) {
        this.defaultInfo = defaultInfo;
    }
}
