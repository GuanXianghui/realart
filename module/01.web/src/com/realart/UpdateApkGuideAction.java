package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.BaseUtil;
import com.realart.utils.ParamUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.File;

/**
 * 首页下载安卓管理
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateApkGuideAction extends BaseAction implements UserInterface{
    private String title;
    private File logo;
    private File btn;
    private String alt;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("title:[" + title + "],logo:[" + logo + "],btn:[" + btn + "],alt:[" + alt + "]");
        //首页 下载安卓应用管理
        String apkGuide= ParamUtil.getInstance().getValueByName(HomeParamInterface.APK_GUIDE);
        JSONObject json = JSONObject.fromObject(apkGuide);
        //当没有传新的图片上来则保持原来的值
        String newLogo = (StringUtils.trimToEmpty((String) json.get("logo")));
        String newBtn = (StringUtils.trimToEmpty((String) json.get("btn")));

        if(null != logo){
            newLogo = BaseUtil.uploadImg(logo, "images/upload");
        }
        if(null != btn){
            newBtn = BaseUtil.uploadImg(btn, "images/upload");
        }

        apkGuide = "{" +
                "\"title\":\"" + title + "\"" +
                ",\"logo\":\"" + newLogo + "\"" +
                ",\"btn\":\"" + newBtn + "\"" +
                ",\"alt\":\"" + alt + "\"" +
                "}";

        ParamUtil.getInstance().updateParam(HomeParamInterface.APK_GUIDE, apkGuide, "安卓应用下载模块");

        //刷新缓存
        ParamUtil.refresh();

        message = "首页下载安卓管理成功！";
        return SUCCESS;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public File getLogo() {
        return logo;
    }

    public void setLogo(File logo) {
        this.logo = logo;
    }

    public File getBtn() {
        return btn;
    }

    public void setBtn(File btn) {
        this.btn = btn;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
