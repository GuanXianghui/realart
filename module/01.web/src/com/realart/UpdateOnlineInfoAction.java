package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * 修改首页在线服务信息
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateOnlineInfoAction extends BaseAction implements UserInterface{
    private String qqNumber1;
    private String qqName1;
    private String qqAlt1;
    private String qqNumber2;
    private String qqName2;
    private String qqAlt2;
    private String liuYanHref;
    private String emailNumber;
    private String companyHref;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("qqNumber1:[" + qqNumber1 + "],qqName1:[" + qqName1 + "],qqAlt1:[" + qqAlt1 + "]," +
                "qqNumber2:[" + qqNumber2 + "],qqName2:[" + qqName2 + "],qqAlt2:[" + qqAlt2 + "]," +
                "liuYanHref:[" + liuYanHref + "],emailNumber:[" + emailNumber + "],companyHref:[" + companyHref+ "]");
        String onlineInfo = "{" +
                "\"qq\":[" +
                "{\"number\":\"" + qqNumber1 + "\",\"name\":\"" + qqName1 + "\",\"alt\":\"" + qqAlt1 + "\"}," +
                "{\"number\":\"" + qqNumber2 + "\",\"name\":\"" + qqName2 + "\",\"alt\":\"" + qqAlt2 + "\"}" +
                "]," +
                "\"liuyan\":{\"href\":\"" + liuYanHref + "\"}," +
                "\"email\":{\"number\":\"" + emailNumber + "\"}," +
                "\"company\":{\"href\":\"" + companyHref + "\"}" +
                "}";
        //首页联系方法管理
        ParamUtil.getInstance().updateParam(HomeParamInterface.ONLINE_INFO, onlineInfo, "首页在线服务信息");

        //刷新缓存
        ParamUtil.refresh();

        message = "修改首页在线服务信息成功！";
        return SUCCESS;
    }

    public String getQqNumber1() {
        return qqNumber1;
    }

    public void setQqNumber1(String qqNumber1) {
        this.qqNumber1 = qqNumber1;
    }

    public String getQqName1() {
        return qqName1;
    }

    public void setQqName1(String qqName1) {
        this.qqName1 = qqName1;
    }

    public String getQqAlt1() {
        return qqAlt1;
    }

    public void setQqAlt1(String qqAlt1) {
        this.qqAlt1 = qqAlt1;
    }

    public String getQqNumber2() {
        return qqNumber2;
    }

    public void setQqNumber2(String qqNumber2) {
        this.qqNumber2 = qqNumber2;
    }

    public String getQqName2() {
        return qqName2;
    }

    public void setQqName2(String qqName2) {
        this.qqName2 = qqName2;
    }

    public String getQqAlt2() {
        return qqAlt2;
    }

    public void setQqAlt2(String qqAlt2) {
        this.qqAlt2 = qqAlt2;
    }

    public String getLiuYanHref() {
        return liuYanHref;
    }

    public void setLiuYanHref(String liuYanHref) {
        this.liuYanHref = liuYanHref;
    }

    public String getEmailNumber() {
        return emailNumber;
    }

    public void setEmailNumber(String emailNumber) {
        this.emailNumber = emailNumber;
    }

    public String getCompanyHref() {
        return companyHref;
    }

    public void setCompanyHref(String companyHref) {
        this.companyHref = companyHref;
    }
}
