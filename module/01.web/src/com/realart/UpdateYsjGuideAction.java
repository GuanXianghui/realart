package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * �޸���ҳ������չʾ
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateYsjGuideAction extends BaseAction implements UserInterface{
    private String title;
    private String name;
    private String href;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("title:[" + title + "],name:[" + name + "],href:[" + href + "]");
        String ysjMore = "{\"name\":\"" + name + "\",\"href\":\"" + href + "\"}";
        //��ҳ�����Ҹ���
        ParamUtil.getInstance().updateParam(HomeParamInterface.YSJ_TITLE, title, "��ҳ������չʾ����");
        //��ҳ�����Ҹ���
        ParamUtil.getInstance().updateParam(HomeParamInterface.YSJ_MORE, ysjMore, "��ҳ�����Ҹ���");

        //ˢ�»���
        ParamUtil.refresh();

        message = "�޸���ҳ������չʾ�ɹ���";
        return SUCCESS;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
