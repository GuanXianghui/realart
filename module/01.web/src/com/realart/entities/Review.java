package com.realart.entities;

/**
 * 评论实体
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 21:26
 */
public class Review {
    int id;
    int userId;
    String title;
    String type;
    String photo;
    String content;
    String locationBit;
    String createDate;
    String createTime;
    String createIp;

    /**
     * 新增时使用
     *
     * @param userId
     * @param title
     * @param type
     * @param photo
     * @param content
     * @param locationBit
     * @param createDate
     * @param createTime
     * @param createIp
     */
    public Review(int userId, String title, String type, String photo, String content, String locationBit,
                  String createDate, String createTime, String createIp) {
        this.userId = userId;
        this.title = title;
        this.type = type;
        this.photo = photo;
        this.content = content;
        this.locationBit = locationBit;
        this.createDate = createDate;
        this.createTime = createTime;
        this.createIp = createIp;
    }

    /**
     * 查询时使用
     *
     * @param id
     * @param userId
     * @param title
     * @param type
     * @param photo
     * @param content
     * @param locationBit
     * @param createDate
     * @param createTime
     * @param createIp
     */
    public Review(int id, int userId, String title, String type, String photo, String content, String locationBit,
                  String createDate, String createTime, String createIp) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.type = type;
        this.photo = photo;
        this.content = content;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getLocationBit() {
        return locationBit;
    }

    public void setLocationBit(String locationBit) {
        this.locationBit = locationBit;
    }

    /**
     * 是否评论专栏自己置顶
     * @return
     */
    public boolean isSelfTop(){
        return locationBit.indexOf("1") == 0;
    }

    /**
     * 修改 是否评论专栏自己置顶
     * @param value
     */
    public void setSelfTop(boolean value){
        locationBit = (value?"1":"0") + locationBit.substring(1);
    }

    /**
     * 是否置顶推荐的评论
     * @return
     */
    public boolean isZdtj(){
        return locationBit.substring(1).indexOf("1") == 0;
    }

    /**
     * 修改 是否置顶推荐的评论
     * @param value
     */
    public void setZdtj(boolean value){
        locationBit = locationBit.substring(0, 1) + (value?"1":"0") + locationBit.substring(2);
    }

    /**
     * 是否置顶推荐的图
     * @return
     */
    public boolean isZdtjTop(){
        return locationBit.substring(2).indexOf("1") == 0;
    }

    /**
     * 修改 是否置顶推荐的图
     * @param value
     */
    public void setZdtjTop(boolean value){
        locationBit = locationBit.substring(0, 2) + (value?"1":"0") + locationBit.substring(3);
    }

    /**
     * 是否美文共赏的评论
     * @return
     */
    public boolean isMwgs(){
        return locationBit.substring(3).indexOf("1") == 0;
    }

    /**
     * 修改 是否美文共赏的评论
     * @param value
     */
    public void setMwgs(boolean value){
        locationBit = locationBit.substring(0, 3) + (value?"1":"0") + locationBit.substring(4);
    }

    /**
     * 是否美文共赏的图
     * @return
     */
    public boolean isMwgsTop(){
        return locationBit.substring(4).indexOf("1") == 0;
    }

    /**
     * 修改 是否美文共赏的图
     * @param value
     */
    public void setMwgsTop(boolean value){
        locationBit = locationBit.substring(0, 4) + (value?"1":"0") + locationBit.substring(5);
    }

    /**
     * 是否本站原创的评论
     * @return
     */
    public boolean isBzyc(){
        return locationBit.substring(5).indexOf("1") == 0;
    }

    /**
     * 修改 是否本站原创的评论
     * @param value
     */
    public void setBzyc(boolean value){
        locationBit = locationBit.substring(0, 5) + (value?"1":"0") + locationBit.substring(6);
    }

    /**
     * 是否本站原创的图
     * @return
     */
    public boolean isBzycTop(){
        return locationBit.substring(6).indexOf("1") == 0;
    }

    /**
     * 修改 是否本站原创的图
     * @param value
     */
    public void setBzycTop(boolean value){
        locationBit = locationBit.substring(0, 6) + (value?"1":"0") + locationBit.substring(7);
    }

    /**
     * 是否会员专栏的图
     * @return
     */
    public boolean isHyzlTop(){
        return locationBit.substring(7).indexOf("1") == 0;
    }

    /**
     * 修改 是否会员专栏的图
     * @param value
     */
    public void setHyzlTop(boolean value){
        locationBit = locationBit.substring(0, 7) + (value?"1":"0") + locationBit.substring(8);
    }
}
