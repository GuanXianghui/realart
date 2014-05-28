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
public class SaveAURIAction extends BaseAction implements UserInterface{
    private String auri;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("auri:[" + auri + "]");
        ParamUtil.getInstance().updateParam(ParamInterface.ARTIST_USER_REGIST_ITEMS, auri, "艺术家用户注册项");

        //刷新缓存
        ParamUtil.refresh();

        message = "保存注册项成功！";
        return SUCCESS;
    }

    public String getAuri() {
        return auri;
    }

    public void setAuri(String auri) {
        this.auri = auri;
    }
}
