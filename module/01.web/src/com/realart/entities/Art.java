package com.realart.entities;

import com.realart.interfaces.ArtInterface;
import org.apache.commons.lang.StringUtils;

/**
 * 艺术作品实体
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-17 23:36
 */
public class Art implements ArtInterface {
    int id;
    int userId;
    String name;
    int state;
    String reason;
    String photo;
    String photo0;
    String photo1;
    String photo2;
    String photo3;
    String photo4;
    String gongyi;
    String type;
    float length;
    float width;
    float height;
    String buildDate;
    String title;
    String introduction;
    String locationBit;
    String createDate;
    String createTime;
    String createIp;

    /**
     * 新增时使用
     * @param userId
     * @param name
     * @param state
     * @param reason
     * @param photo
     * @param photo0
     * @param photo1
     * @param photo2
     * @param photo3
     * @param photo4
     * @param gongyi
     * @param type
     * @param length
     * @param width
     * @param height
     * @param buildDate
     * @param title
     * @param introduction
     * @param locationBit
     * @param createDate
     * @param createTime
     * @param createIp
     */
    public Art(int userId, String name, int state, String reason, String photo, String photo0, String photo1, String photo2,
               String photo3, String photo4, String gongyi, String type, float length, float width, float height,
               String buildDate, String title, String introduction, String locationBit, String createDate,
               String createTime, String createIp) {
        this.userId = userId;
        this.name = name;
        this.state = state;
        this.reason = reason;
        this.photo = photo;
        this.photo0 = photo0;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.gongyi = gongyi;
        this.type = type;
        this.length = length;
        this.width = width;
        this.height = height;
        this.buildDate = buildDate;
        this.title = title;
        this.introduction = introduction;
        this.locationBit = locationBit;
        this.createDate = createDate;
        this.createTime = createTime;
        this.createIp = createIp;
    }

    /**
     * 查询时使用
     * @param id
     * @param userId
     * @param name
     * @param state
     * @param reason
     * @param photo
     * @param photo0
     * @param photo1
     * @param photo2
     * @param photo3
     * @param photo4
     * @param gongyi
     * @param type
     * @param length
     * @param width
     * @param height
     * @param buildDate
     * @param title
     * @param introduction
     * @param locationBit
     * @param createDate
     * @param createTime
     * @param createIp
     */
    public Art(int id, int userId, String name, int state, String reason, String photo, String photo0, String photo1, String photo2,
               String photo3, String photo4, String gongyi, String type, float length, float width, float height,
               String buildDate, String title, String introduction, String locationBit, String createDate,
               String createTime, String createIp) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.state = state;
        this.reason = reason;
        this.photo = photo;
        this.photo0 = photo0;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.gongyi = gongyi;
        this.type = type;
        this.length = length;
        this.width = width;
        this.height = height;
        this.buildDate = buildDate;
        this.title = title;
        this.introduction = introduction;
        this.locationBit = locationBit;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto0() {
        return photo0;
    }

    public void setPhoto0(String photo0) {
        this.photo0 = photo0;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
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

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
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

    public String getLocationBit() {
        return locationBit;
    }

    public void setLocationBit(String locationBit) {
        this.locationBit = locationBit;
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
     * 返回状态描述
     * @return
     */
    public String getStateDesc(){
        if(NEED_CHECK == state){
            return "待审核";
        }
        if(NORMAL == state){
            return "审核正常";
        }
        if(CHECK_FAILED == state){
            return "审核失败";
        }
        return StringUtils.EMPTY;
    }

    /**
     * 是否艺术官网自己置顶
     * @return
     */
    public boolean isSelfTop(){
        return locationBit.indexOf("1") == 0;
    }

    /**
     * 修改 是否艺术官网自己置顶
     * @param value
     */
    public void setSelfTop(boolean value){
        locationBit = (value?"1":"0") + locationBit.substring(1);
    }

    /**
     * 是否艺术家专栏的图
     * @return
     */
    public boolean isYszlTop(){
        return locationBit.substring(1).indexOf("1") == 0;
    }

    /**
     * 修改 是否艺术家专栏的图
     * @param value
     */
    public void setYszlTop(boolean value){
        locationBit = locationBit.substring(0, 1) + (value?"1":"0") + locationBit.substring(2);
    }
}
