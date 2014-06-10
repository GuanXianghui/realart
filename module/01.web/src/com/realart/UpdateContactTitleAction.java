package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * �޸���ҳ��ϵ��������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateContactTitleAction extends BaseAction implements UserInterface{
    private String contactTitle;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("contactTitle:[" + contactTitle + "]");
        //��ҳ��ϵ��������
        ParamUtil.getInstance().updateParam(HomeParamInterface.CONTACT_TITLE, contactTitle, "������ϵ��������");

        //ˢ�»���
        ParamUtil.refresh();

        message = "�޸���ҳ��ϵ��������ɹ���";
        return SUCCESS;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }
}
