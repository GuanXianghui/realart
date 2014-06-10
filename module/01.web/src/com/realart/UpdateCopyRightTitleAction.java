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
public class UpdateCopyRightTitleAction extends BaseAction implements UserInterface{
    private String copyright;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("copyright:[" + copyright + "]");
        //��ҳ��ϵ��������
        ParamUtil.getInstance().updateParam(HomeParamInterface.COPYRIGHT_TITLE, copyright, "��Ȩ��Ϣ");

        //ˢ�»���
        ParamUtil.refresh();

        message = "�޸���ҳ��ϵ��������ɹ���";
        return SUCCESS;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
