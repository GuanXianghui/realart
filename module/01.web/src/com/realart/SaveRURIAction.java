package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * 保存注册项
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class SaveRURIAction extends BaseAction implements UserInterface{
    private String ruri;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("ruri:[" + ruri + "]");
        ParamUtil.getInstance().updateParam(ParamInterface.REVIEW_USER_REGIST_ITEMS, ruri, "评论用户注册项");

        //刷新缓存
        ParamUtil.refresh();

        message = "保存注册项成功！";
        return SUCCESS;
    }

    public String getRuri() {
        return ruri;
    }

    public void setRuri(String ruri) {
        this.ruri = ruri;
    }
}
