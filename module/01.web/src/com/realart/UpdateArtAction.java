package com.realart;

import com.realart.dao.ArtDao;
import com.realart.entities.Art;
import com.realart.interfaces.ArtInterface;
import com.realart.utils.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.Date;

/**
 * �޸���Ʒ
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateArtAction extends BaseAction implements ArtInterface {
    private String artId;
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
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],photo:[" + photo + "],photo0:[" + photo0 + "],photo1:[" +
                photo1 + "],photo2:[" + photo2 + "],photo3:[" + photo3 + "],photo4:[" + photo4 + "]," +
                "gongyi:[" + gongyi + "],type:[" + type + "],length:[" + length + "],width:[" + width + "]," +
                "height:[" + height + "],buildDate:[" + buildDate + "],title:[" + title + "],introduction:" +
                "[" + introduction + "],artId:[" + artId + "]");
        //���ֶ�Ϊ��
        if(StringUtils.isBlank(name) || StringUtils.isBlank(gongyi) || StringUtils.isBlank(type) ||
                StringUtils.isBlank(length) || StringUtils.isBlank(width) || StringUtils.isBlank(height) ||
                StringUtils.isBlank(buildDate) || StringUtils.isBlank(title) || StringUtils.isBlank(artId)){
            message = "�����������";
            return ERROR;
        }
        //��ͼƬΪ��
        if(null == photo || null == photo0)
        {
            message = "���ϴ������ͼƬ!";
            return ERROR;
        }

        Art art = ArtDao.getArtById(Integer.parseInt(artId));
        if(null == art || art.getState() == ArtInterface.NORMAL){
            message = "��Ĳ�������!";
            return ERROR;
        }

        art.setName(name);
        art.setState(NEED_CHECK);//��������ʧ���޸ĳɴ����
        art.setGongyi(gongyi);
        art.setType(type);
        art.setLength(Float.parseFloat(length));
        art.setWidth(Float.parseFloat(width));
        art.setHeight(Float.parseFloat(height));
        art.setBuildDate(buildDate);
        art.setTitle(title);
        art.setIntroduction(introduction);

        //����ͼƬ
        String photoRoute = createPhoto(photo, -1);
        //����ͼƬ
        String photoRoute0 = createPhoto(photo0, 0);
        //����ͼƬ
        String photoRoute1 = createPhoto(photo1, 1);
        //����ͼƬ
        String photoRoute2 = createPhoto(photo2, 2);
        //����ͼƬ
        String photoRoute3 = createPhoto(photo3, 3);
        //����ͼƬ
        String photoRoute4 = createPhoto(photo4, 4);
        //�����û���Ϣ
        art.setPhoto(photoRoute);
        art.setPhoto0(photoRoute0);
        art.setPhoto1(photoRoute1);
        art.setPhoto2(photoRoute2);
        art.setPhoto3(photoRoute3);
        art.setPhoto4(photoRoute4);
        ArtDao.updateInfo(art);
        ArtDao.updatePhotos(art);
        ArtDao.updateState(art);

        message = "�޸���Ʒ�ɹ���";
        return SUCCESS;
    }

    /**
     * ����ͼƬ
     * @param photo
     * @param index
     * @return
     */
    private String createPhoto(File photo, int index){
        if(null == photo){
            return StringUtils.EMPTY;
        }
        //�µ�ͼƬ����
        String photoName = getUser().getId() + "_" + new Date().getTime() + (index>=0?"_"+index:"") + ".jpg";
        //�������ϵ�·��
        String photoPath = ServletActionContext.getServletContext().getRealPath("images/art") + "/" + photoName;
        //ҳ������λ�� ���·��
        String photoPagePath = "images/art/" + photoName;
        //�������ϵ�·����Ӧ���ļ�
        File imageFile = new File(photoPath);
        //�����ļ�
        FileUtil.copy(photo, imageFile);
        return photoPagePath;
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

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }
}
