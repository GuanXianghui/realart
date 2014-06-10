package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * �޸���ҳ����ؼ���
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateHeadGuideAction extends BaseAction implements UserInterface{
    private String value0;
    private String value1;
    private String value2;
    private String value3;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("value0:[" + value0 + "],value1:[" + value1 + "],value2:[" + value2 + "],value3:[" +
                value3 + "]");
        String headGuide = "[" +
                "{\"name\":\"��ҳ����\",\"text\":\"" + value0 + "\"}" +
                ",{\"name\":\"�ؼ���\",\"text\":\"" + value1 + "\"}" +
                ",{\"name\":\"��ҳ����\",\"text\":\"" + value2 + "\"}" +
                ",{\"name\":\"��ҳͷ�������\",\"text\":\"" + value3 + "\"}" +
                "]";
        //��ҳ��ϵ��������
        ParamUtil.getInstance().updateParam(HomeParamInterface.HEAD_GUIDE, headGuide, "��ҳ����ؼ���");

        //ˢ�»���
        ParamUtil.refresh();

        message = "�޸���ҳ����ؼ��ֳɹ���";
        return SUCCESS;
    }

    public String getValue0() {
        return value0;
    }

    public void setValue0(String value0) {
        this.value0 = value0;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }
}
