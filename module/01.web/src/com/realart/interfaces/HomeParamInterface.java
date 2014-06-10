package com.realart.interfaces;

/**
 * 启动参数接口
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-11 10:20
 */
public interface HomeParamInterface extends BaseInterface {
    /**
     * 标题头
     */
    public static final String HEAD_GUIDE = "head_guide";
    /**
     * 联系人信息
     */
    public static final String CONTACT_GUIDE = "contact_guide";

    public static final String SLIDER_01 = "slider_01";
    
    public static final String YSJ_TITLE = "ysj_title";
    
    public static final String YSJ_MORE = "ysj_more";
    
    public static final String CONTACT_TITLE = "contact_title";
    
    public static final String FRIEND_TITLE = "friendsite_title";
    
    public static final String ONLINE_INFO = "online_info";
   
    public static final String APK_GUIDE = "apk_guide"; 
    
      
    public static final String COPYRIGHT_TITLE = "copyright_title";
    /**
     * 首页艺术家列表
     */
    public static final int HOME_YSJ_LIST = 0;
    /**
     * 首页友情链接列表
     */ 
    public static final int HOME_FRIENDSITE_LIST = 1;
    /**
     * 首页联系方法列表
     */ 
    public static final int HOME_CONTACT_LIST = 2;
    /**
     * 首页最新发布列表
     */ 
    public static final int HOME_NEWFB_LIST = 3;
    /**
     * 首页最新评论发布列表
     */ 
    public static final int HOME_NEWPL_LIST = 6;
    
    /**
     * 首页8框内容列表
     */ 
    public static final int ITEM_LIST = 4;
    
    public static final int ITEM_GUIDE = 5; 
}
