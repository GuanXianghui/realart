package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * �������ʧ��ԭ��
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class SaveCWRAction extends BaseAction implements UserInterface{
    private String cwr;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("cwr:[" + cwr + "]");
        ParamUtil.getInstance().updateParam(ParamInterface.CHECK_WRONG_REASON, cwr, "���ʧ��ԭ��");

        //ˢ�»���
        ParamUtil.refresh();

        message = "�������ʧ��ԭ��ɹ���";
        return SUCCESS;
    }

    public String getCwr() {
        return cwr;
    }

    public void setCwr(String cwr) {
        this.cwr = cwr;
    }
}
