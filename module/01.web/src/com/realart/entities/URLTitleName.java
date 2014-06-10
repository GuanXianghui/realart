package com.realart.entities;

/**
 * 启动参数实体
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-31 08:39
 */
public class URLTitleName {
    String name;
    String url;
    String img;
    int id;
    boolean dis;
    int type;

    /**
     * 查询时使用
     * @param id
     * @param name
     * @param url
     * @param img
     * @param dis
     * @param type
     */
    public URLTitleName(int id, String name, String url, String img, boolean dis, int type) {
        this.name = name;
        this.url = url;
        this.img = img;
        this.id = id;
        this.dis = dis;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public boolean getDis() {
        return dis;
    }

    public void setDis(boolean dis) {
        this.dis = dis;
    }
}
