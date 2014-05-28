package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * ������ҳ����
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class SaveGuideAction extends BaseAction implements UserInterface{
    private String guide;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("guide:[" + guide + "]");
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_GUIDE, guide, "��ҳ ����");

        //ˢ�»���
        ParamUtil.refresh();

        message = "������ҳ�����ɹ���";
        return SUCCESS;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
}
