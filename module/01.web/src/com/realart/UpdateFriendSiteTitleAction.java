package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * �޸���ҳ�������ӱ���
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateFriendSiteTitleAction extends BaseAction implements UserInterface{
    private String friendSite;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("friendSite:[" + friendSite + "]");
        //��ҳ��ϵ��������
        ParamUtil.getInstance().updateParam(HomeParamInterface.FRIEND_TITLE, friendSite, "�������ӱ���");

        //ˢ�»���
        ParamUtil.refresh();

        message = "�޸���ҳ�������ӱ���ɹ���";
        return SUCCESS;
    }

    public String getFriendSite() {
        return friendSite;
    }

    public void setFriendSite(String friendSite) {
        this.friendSite = friendSite;
    }
}
