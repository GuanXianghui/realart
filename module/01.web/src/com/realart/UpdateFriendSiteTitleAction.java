package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * 修改首页友情链接标题
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateFriendSiteTitleAction extends BaseAction implements UserInterface{
    private String friendSite;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("friendSite:[" + friendSite + "]");
        //首页联系方法管理
        ParamUtil.getInstance().updateParam(HomeParamInterface.FRIEND_TITLE, friendSite, "友情链接标题");

        //刷新缓存
        ParamUtil.refresh();

        message = "修改首页友情链接标题成功！";
        return SUCCESS;
    }

    public String getFriendSite() {
        return friendSite;
    }

    public void setFriendSite(String friendSite) {
        this.friendSite = friendSite;
    }
}
