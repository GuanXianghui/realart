package com.realart;

import com.realart.dao.ArtDao;
import com.realart.dao.QrCodeDao;
import com.realart.entities.Art;
import com.realart.entities.QrCode;
import com.realart.interfaces.ArtInterface;
import com.realart.interfaces.QrCodeInterface;
import com.realart.utils.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.Date;

/**
 * 作品备案
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UploadArtAction extends BaseAction implements ArtInterface {
    private String qr;
    private String name;
    private File photo;
    private File photo0;
    private File photo1;
    private File photo2;
    private File photo3;
    private File photo4;
    private String gongyi;
    private String type;
    private String length;
    private String width;
    private String height;
    private String buildDate;
    private String title;
    private String introduction;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],photo:[" + photo + "],photo0:[" + photo0 + "],photo1:[" +
                photo1 + "],photo2:[" + photo2 + "],photo3:[" + photo3 + "],photo4:[" + photo4 + "]," +
                "gongyi:[" + gongyi + "],type:[" + type + "],length:[" + length + "],width:[" + width + "]," +
                "height:[" + height + "],buildDate:[" + buildDate + "],title:[" + title + "],introduction:" +
                "[" + introduction + "],qr:[" + qr + "]");
        //判字段为空
        if(StringUtils.isBlank(name) || StringUtils.isBlank(gongyi) || StringUtils.isBlank(type) ||
                StringUtils.isBlank(length) || StringUtils.isBlank(width) || StringUtils.isBlank(height) ||
                StringUtils.isBlank(buildDate) || StringUtils.isBlank(title) || StringUtils.isBlank(qr)){
            message = "请输入必输项";
            return ERROR;
        }
        //判图片为空
        if(null == photo || null == photo0)
        {
            message = "请上传必须的图片!";
            return ERROR;
        }

        QrCode qrCode = QrCodeDao.getQrCodeByUuid(qr);
        if(qrCode == null || qrCode.getState() != QrCodeInterface.STATE_NOT_USE){
            message = "序列号已失效!";
            return ERROR;
        }

        //创建作品
        Art art = new Art(getUser().getId(), name, NEED_CHECK, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY,
                StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, gongyi, type,
                Float.parseFloat(length), Float.parseFloat(width), Float.parseFloat(height), buildDate, title,
                introduction, DEFAULT_LOCATION_BIT, date, time, getIp());
        ArtDao.insertArt(art);

        //创建图片
        String photoRoute = createPhoto(photo, -1);
        //创建图片
        String photoRoute0 = createPhoto(photo0, 0);
        //创建图片
        String photoRoute1 = createPhoto(photo1, 1);
        //创建图片
        String photoRoute2 = createPhoto(photo2, 2);
        //创建图片
        String photoRoute3 = createPhoto(photo3, 3);
        //创建图片
        String photoRoute4 = createPhoto(photo4, 4);
        //更新用户信息
        art.setPhoto(photoRoute);
        art.setPhoto0(photoRoute0);
        art.setPhoto1(photoRoute1);
        art.setPhoto2(photoRoute2);
        art.setPhoto3(photoRoute3);
        art.setPhoto4(photoRoute4);
        ArtDao.updatePhotos(art);

        //绑定二维码 更新二维码
        qrCode.setArtId(art.getId());
        qrCode.setState(QrCodeInterface.STATE_USED);
        QrCodeDao.updateQrCode(qrCode);

        message = "作品备案成功！";
        return SUCCESS;
    }

    /**
     * 创建图片
     * @param photo
     * @param index
     * @return
     */
    private String createPhoto(File photo, int index){
        if(null == photo){
            return StringUtils.EMPTY;
        }
        //新的图片名称
        String photoName = getUser().getId() + "_" + new Date().getTime() + (index>=0?"_"+index:"") + ".jpg";
        //服务器上的路径
        String photoPath = ServletActionContext.getServletContext().getRealPath("images/art") + "/" + photoName;
        //页面引用位置 相对路径
        String photoPagePath = "images/art/" + photoName;
        //服务器上的路径对应的文件
        File imageFile = new File(photoPath);
        //拷贝文件
        FileUtil.copy(photo, imageFile);
        return photoPagePath;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public File getPhoto0() {
        return photo0;
    }

    public void setPhoto0(File photo0) {
        this.photo0 = photo0;
    }

    public File getPhoto1() {
        return photo1;
    }

    public void setPhoto1(File photo1) {
        this.photo1 = photo1;
    }

    public File getPhoto2() {
        return photo2;
    }

    public void setPhoto2(File photo2) {
        this.photo2 = photo2;
    }

    public File getPhoto3() {
        return photo3;
    }

    public void setPhoto3(File photo3) {
        this.photo3 = photo3;
    }

    public File getPhoto4() {
        return photo4;
    }

    public void setPhoto4(File photo4) {
        this.photo4 = photo4;
    }

    public String getGongyi() {
        return gongyi;
    }

    public void setGongyi(String gongyi) {
        this.gongyi = gongyi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
