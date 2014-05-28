package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * 保存首页导航
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class SaveGuideAction extends BaseAction implements UserInterface{
    private String guide;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("guide:[" + guide + "]");
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_GUIDE, guide, "首页 导航");

        //刷新缓存
        ParamUtil.refresh();

        message = "保存首页导航成功！";
        return SUCCESS;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
}
