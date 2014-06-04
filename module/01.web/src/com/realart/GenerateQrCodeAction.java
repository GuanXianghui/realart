package com.realart;

import com.realart.dao.QrCodeDao;
import com.realart.entities.QrCode;
import com.realart.interfaces.QrCodeInterface;
import com.realart.utils.qrcode.TwoDimensionCode;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.awt.*;
import java.io.File;
import java.util.UUID;

/**
 * 生成序列号并产生二维码
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class GenerateQrCodeAction extends BaseAction implements QrCodeInterface{
    private String num;
    private String antiError;
    private String size;
    private String bgColor;
    private String frontColor;
    private String info;
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
                "frontColor:[" + frontColor + "],info:[" + info + "],type:[" + type + "]," +
                "qrLogo:[" + qrLogo + "],logoBorderType:[" + logoBorderType + "]," +
                "logoBorderColor:[" + logoBorderColor + "],num:[" + num + "]");
        //批量生成序列号并生成二维码
        int numInt = Integer.parseInt(num);
        for(int count=0;count<numInt;count++){
            //生成uuid
            String uuid = StringUtils.EMPTY;
            //如果uuid存在 继续产生下一个
            boolean isExist = true;
            while(isExist){
                uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
                QrCode qrCode = QrCodeDao.getQrCodeByUuid(uuid);
                if(null != qrCode){
                    continue;
                }
                isExist = false;
            }
            //新增二维码记录
            QrCode qrCode = new QrCode(uuid, StringUtils.EMPTY, STATE_NOT_USE, QR_CODE_NOT_USE_ART_ID,
                    info, date, time, getIp());
            QrCodeDao.insertQrCode(qrCode);
            qrCode = QrCodeDao.getQrCodeByUuid(uuid);

            //二维码图片名称
            String qrCodePhotoName = uuid + ".png";
            //服务器上的路径
            String qrCodePhotoPath = ServletActionContext.getServletContext().getRealPath("images/qr/") + "/" + qrCodePhotoName;
            //页面引用位置 相对路径
            String qrCodePhotoPagePath = "images/qr/" + qrCodePhotoName;
            //修改二维码图片路径
            qrCode.setImgPath(qrCodePhotoPagePath);
            QrCodeDao.updateQrCode(qrCode);

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
        }
        message = "批量生成" + num + "个序列号并产生二维码成功！";
        return SUCCESS;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
