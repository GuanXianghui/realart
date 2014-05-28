package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * ����ע����
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class SaveRURIAction extends BaseAction implements UserInterface{
    private String ruri;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("ruri:[" + ruri + "]");
        ParamUtil.getInstance().updateParam(ParamInterface.REVIEW_USER_REGIST_ITEMS, ruri, "�����û�ע����");

        //ˢ�»���
        ParamUtil.refresh();

        message = "����ע����ɹ���";
        return SUCCESS;
    }

    public String getRuri() {
        return ruri;
    }

    public void setRuri(String ruri) {
        this.ruri = ruri;
    }
}
