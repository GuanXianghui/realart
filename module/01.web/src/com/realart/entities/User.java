package com.realart.entities;

import com.realart.interfaces.UserInterface;
import org.apache.commons.lang.StringUtils;

/**
 * �û�ʵ��
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 11:18
 */
public class User implements UserInterface {
    int id;
    String name;
    int userType;
    String password;
    String certName;
    String titlePhoto;
    String headPhoto;
    String info;
    int state;
    String reason;
    String registerDate;
    String registerTime;
    String registerIp;

    /**
     * ����ʱʹ��
     * @param name
     * @param userType
     * @param password
     * @param certName
     * @param titlePhoto
     * @param headPhoto
     * @param info
     * @param state
     * @param reason
     * @param registerDate
     * @param registerTime
     * @param registerIp
     */
    public User(String name, int userType, String password, String certName, String titlePhoto, String headPhoto,
                String info, int state, String reason, String registerDate, String registerTime, String registerIp) {
        this.name = name;
        this.userType = userType;
        this.password = password;
        this.certName = certName;
        this.titlePhoto = titlePhoto;
        this.headPhoto = headPhoto;
        this.info = info;
        this.state = state;
        this.reason = reason;
        this.registerDate = registerDate;
        this.registerTime = registerTime;
        this.registerIp = registerIp;
    }

    /**
     * ��ѯʱʹ��
     * @param id
     * @param name
     * @param userType
     * @param password
     * @param certName
     * @param titlePhoto
     * @param headPhoto
     * @param info
     * @param state
     * @param reason
     * @param registerDate
     * @param registerTime
     * @param registerIp
     */
    public User(int id, String name, int userType, String password, String certName, String titlePhoto,
                String headPhoto, String info, int state, String reason, String registerDate, String registerTime,
                String registerIp) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.password = password;
        this.certName = certName;
        this.titlePhoto = titlePhoto;
        this.headPhoto = headPhoto;
        this.info = info;
        this.state = state;
        this.reason = reason;
        this.registerDate = registerDate;
        this.registerTime = registerTime;
        this.registerIp = registerIp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getTitlePhoto() {
        return titlePhoto;
    }

    public void setTitlePhoto(String titlePhoto) {
        this.titlePhoto = titlePhoto;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    /**
     * ����״̬����
     * @return
     */
    public String getStateDesc(){
        if(USER_STATE_NEED_CHECK == state){
            return "�����";
        }
        if(USER_STATE_NORMAL == state){
            return "���ͨ������";
        }
        if(USER_STATE_CHECK_FAILED == state){
            return "���ʧ��";
        }
        if(USER_STATE_LOCK == state){
            return "����";
        }
        return StringUtils.EMPTY;
    }
}
