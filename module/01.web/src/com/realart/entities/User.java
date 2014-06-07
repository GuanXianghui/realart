package com.realart.entities;

import com.realart.interfaces.UserInterface;
import org.apache.commons.lang.StringUtils;

/**
 * 用户实体
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
    String email;
    String info;
    int state;
    String reason;
    String artKinds;
    String registerDate;
    String registerTime;
    String registerIp;

    /**
     * 新增时使用
     * @param name
     * @param userType
     * @param password
     * @param certName
     * @param titlePhoto
     * @param headPhoto
     * @param email
     * @param info
     * @param state
     * @param reason
     * @param artKinds
     * @param registerDate
     * @param registerTime
     * @param registerIp
     */
    public User(String name, int userType, String password, String certName, String titlePhoto, String headPhoto, String email,
                String info, int state, String reason, String artKinds, String registerDate, String registerTime, String registerIp) {
        this.name = name;
        this.userType = userType;
        this.password = password;
        this.certName = certName;
        this.titlePhoto = titlePhoto;
        this.headPhoto = headPhoto;
        this.email = email;
        this.info = info;
        this.state = state;
        this.reason = reason;
        this.artKinds = artKinds;
        this.registerDate = registerDate;
        this.registerTime = registerTime;
        this.registerIp = registerIp;
    }

    /**
     * 查询时使用
     * @param id
     * @param name
     * @param userType
     * @param password
     * @param certName
     * @param titlePhoto
     * @param headPhoto
     * @param email
     * @param info
     * @param state
     * @param reason
     * @param artKinds
     * @param registerDate
     * @param registerTime
     * @param registerIp
     */
    public User(int id, String name, int userType, String password, String certName, String titlePhoto,
                String headPhoto,  String email, String info, int state, String reason, String artKinds, String registerDate, String registerTime,
                String registerIp) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.password = password;
        this.certName = certName;
        this.titlePhoto = titlePhoto;
        this.headPhoto = headPhoto;
        this.email = email;
        this.info = info;
        this.state = state;
        this.reason = reason;
        this.artKinds = artKinds;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getArtKinds() {
        return artKinds;
    }

    public void setArtKinds(String artKinds) {
        this.artKinds = artKinds;
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
     * 返回状态描述
     * @return
     */
    public String getStateDesc(){
        if(USER_STATE_NEED_CHECK == state){
            return "待审核";
        }
        if(USER_STATE_NORMAL == state){
            return "审核通过正常";
        }
        if(USER_STATE_CHECK_FAILED == state){
            return "审核失败";
        }
        if(USER_STATE_LOCK == state){
            return "锁定";
        }
        return StringUtils.EMPTY;
    }
}
