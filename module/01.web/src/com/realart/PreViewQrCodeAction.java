package com.realart;

import com.realart.interfaces.QrCodeInterface;
import com.realart.utils.TokenUtil;
import com.realart.utils.TwoDimensionCode;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.awt.*;
import java.io.File;
import java.util.UUID;

/**
 * 预览二维码
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class PreViewQrCodeAction extends BaseAction implements QrCodeInterface{
    private String antiError;
    private String size;
    private String bgColor;
    private String frontColor;
    private String type;
    private String qrLogo;
    private String logoBorderType;
    private String logoBorderColor;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("antiError:[" + antiError + "],size:[" + size + "],bgColor:[" + bgColor + "]," +
                "frontColor:[" + frontColor + "],type:[" + type + "],qrLogo:[" + qrLogo + "]," +
                "logoBorderType:[" + logoBorderType + "],logoBorderColor:[" + logoBorderColor + "]");
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        //二维码图片名称
        String qrCodePhotoName = uuid + ".png";
        //服务器上的路径
        String qrCodePhotoPath = ServletActionContext.getServletContext().getRealPath("images/qr_pre/") + "/" + qrCodePhotoName;
        //页面引用位置 相对路径
        String previewImg = "images/qr_pre/" + qrCodePhotoName;

        //生成二维码
        TwoDimensionCode handler = new TwoDimensionCode();
        String content = QR_CODE_URL_PREFIX + uuid;
        Color bgColor2 = new Color(Integer.parseInt(bgColor.replaceAll("#", ""),16));
        Color frontColor2 = new Color(Integer.parseInt(frontColor.replaceAll("#", ""),16));

        //获取logo 如果获取不到 则为null
        File qrLogoFile = null;
        File file = new File(ServletActionContext.getServletContext().getRealPath("images/qr_logo/") + "/");
        File[] files = file.listFiles();
        for(int i=0;i<files.length;i++){
            if(StringUtils.equals(StringUtils.EMPTY + (i+1), qrLogo)){
                qrLogoFile = files[i];
            }
        }
        Color logoBorderColor2 = new Color(Integer.parseInt(logoBorderColor.replaceAll("#", ""),16));

        //生成二维码(QRCode)图片的定制方法
        handler.customQrCode(content, antiError.toCharArray()[0], Integer.parseInt(size), bgColor2, frontColor2,
                type, qrLogoFile, logoBorderType, logoBorderColor2, qrCodePhotoPath);

        message = "预览二维码成功！";

        //返回结果
        String resp = "{isSuccess:true,message:'" + message + "',previewImg:'" + previewImg + "'," +
                "hasNewToken:true,token:'" + TokenUtil.createToken(request) + "'}";
        write(resp);
        return null;
    }

    public String getAntiError() {
        return antiError;
    }

    public void setAntiError(String antiError) {
        this.antiError = antiError;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getFrontColor() {
        return frontColor;
    }

    public void setFrontColor(String frontColor) {
        this.frontColor = frontColor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQrLogo() {
        return qrLogo;
    }

    public void setQrLogo(String qrLogo) {
        this.qrLogo = qrLogo;
    }

    public String getLogoBorderType() {
        return logoBorderType;
    }

    public void setLogoBorderType(String logoBorderType) {
        this.logoBorderType = logoBorderType;
    }

    public String getLogoBorderColor() {
        return logoBorderColor;
    }

    public void setLogoBorderColor(String logoBorderColor) {
        this.logoBorderColor = logoBorderColor;
    }
}
