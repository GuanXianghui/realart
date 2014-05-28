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
public class SaveAURIAction extends BaseAction implements UserInterface{
    private String auri;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("auri:[" + auri + "]");
        ParamUtil.getInstance().updateParam(ParamInterface.ARTIST_USER_REGIST_ITEMS, auri, "�������û�ע����");

        //ˢ�»���
        ParamUtil.refresh();

        message = "����ע����ɹ���";
        return SUCCESS;
    }

    public String getAuri() {
        return auri;
    }

    public void setAuri(String auri) {
        this.auri = auri;
    }
}
