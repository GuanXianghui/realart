package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.BaseUtil;
import com.realart.utils.ParamUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.File;

/**
 * ��ҳ���ذ�׿����
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
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("title:[" + title + "],logo:[" + logo + "],btn:[" + btn + "],alt:[" + alt + "]");
        //��ҳ ���ذ�׿Ӧ�ù���
        String apkGuide= ParamUtil.getInstance().getValueByName(HomeParamInterface.APK_GUIDE);
        JSONObject json = JSONObject.fromObject(apkGuide);
        //��û�д��µ�ͼƬ�����򱣳�ԭ����ֵ
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

        ParamUtil.getInstance().updateParam(HomeParamInterface.APK_GUIDE, apkGuide, "��׿Ӧ������ģ��");

        //ˢ�»���
        ParamUtil.refresh();

        message = "��ҳ���ذ�׿����ɹ���";
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
