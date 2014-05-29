package com.realart;

import com.realart.interfaces.QrCodeInterface;
import com.realart.utils.FileUtil;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.Date;

/**
 * 上传二维码logo
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UploadQrCodeLogoAction extends BaseAction implements QrCodeInterface{
    private File qrLogo;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("qrLogo:[" + qrLogo + "]");
        if(null == qrLogo){
            message = "服务器未接受到logo";
            return ERROR;
        }
        if(0 == qrLogo.length()){
            message = "logo大小不能为空";
            return ERROR;
        }

        //二维码logo名称
        String qrCodePhotoName = new Date().getTime() + ".jpg";
        //服务器上的路径
        String qrCodePhotoPath = ServletActionContext.getServletContext().getRealPath("images/qr_logo/") + "/" + qrCodePhotoName;
        //拷贝文件
        FileUtil.copy(qrLogo, new File(qrCodePhotoPath));

        message = "上传二维码logo成功！";
        return SUCCESS;
    }

    public File getQrLogo() {
        return qrLogo;
    }

    public void setQrLogo(File qrLogo) {
        this.qrLogo = qrLogo;
    }
}
