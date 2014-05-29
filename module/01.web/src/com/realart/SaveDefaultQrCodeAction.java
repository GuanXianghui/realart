package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.QrCodeInterface;
import com.realart.utils.ParamUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;

/**
 * 保存二维码默认配置
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class SaveDefaultQrCodeAction extends BaseAction implements QrCodeInterface{
    private String defaultQrCode;
    private String defaultInfo;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("defaultQrCode:[" + defaultQrCode + "],defaultInfo:[" + defaultInfo + "]");

        ParamUtil.getInstance().updateParam(ParamInterface.DEFAULT_QR_CODE, defaultQrCode, "默认二维码配置");
        ParamUtil.getInstance().updateParam(ParamInterface.DEFAULT_QR_CODE_INFO, defaultInfo, "默认二维码相关信息");
        ParamUtil.refresh();

        message = "保存二维码默认配置成功！";
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
