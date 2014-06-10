package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * 修改首页联系方法标题
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateContactTitleAction extends BaseAction implements UserInterface{
    private String contactTitle;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("contactTitle:[" + contactTitle + "]");
        //首页联系方法管理
        ParamUtil.getInstance().updateParam(HomeParamInterface.CONTACT_TITLE, contactTitle, "首面联系方法标题");

        //刷新缓存
        ParamUtil.refresh();

        message = "修改首页联系方法标题成功！";
        return SUCCESS;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }
}
