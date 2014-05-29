package com.realart.entities;

import com.realart.interfaces.QrCodeInterface;
import org.apache.commons.lang.StringUtils;

/**
 * ��ά��ʵ��
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 11:18
 */
public class QrCode implements QrCodeInterface {
    int id;
    String uuid;
    String imgPath;
    int state;
    int artId;
    String info;
    String createDate;
    String createTime;
    String createIp;

    /**
     * ����ʱʹ��
     * @param uuid
     * @param imgPath
     * @param state
     * @param artId
     * @param info
     * @param createDate
     * @param createTime
     * @param createIp
     */
    public QrCode(String uuid, String imgPath, int state, int artId, String info, String createDate,
                  String createTime, String createIp) {
        this.uuid = uuid;
        this.imgPath = imgPath;
        this.state = state;
        this.artId = artId;
        this.info = info;
        this.createDate = createDate;
        this.createTime = createTime;
        this.createIp = createIp;
    }

    /**
     * ��ѯʱʹ��
     * @param id
     * @param uuid
     * @param imgPath
     * @param state
     * @param artId
     * @param info
     * @param createDate
     * @param createTime
     * @param createIp
     */
    public QrCode(int id, String uuid, String imgPath, int state, int artId, String info, String createDate,
                  String createTime, String createIp) {
        this.id = id;
        this.uuid = uuid;
        this.imgPath = imgPath;
        this.state = state;
        this.artId = artId;
        this.info = info;
        this.createDate = createDate;
        this.createTime = createTime;
        this.createIp = createIp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    /**
     * ����״̬����
     * @return
     */
    public String getStateDesc(){
        if(STATE_NOT_USE == state){
            return "δ��ʹ��";
        }
        if(STATE_USED == state){
            return "�ѱ�ʹ��";
        }
        return StringUtils.EMPTY;
    }
}
