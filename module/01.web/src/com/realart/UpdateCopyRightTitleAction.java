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
public class UpdateCopyRightTitleAction extends BaseAction implements UserInterface{
    private String copyright;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("copyright:[" + copyright + "]");
        //首页联系方法管理
        ParamUtil.getInstance().updateParam(HomeParamInterface.COPYRIGHT_TITLE, copyright, "版权信息");

        //刷新缓存
        ParamUtil.refresh();

        message = "修改首页联系方法标题成功！";
        return SUCCESS;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
