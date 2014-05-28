package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * 保存审核失败原因
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class SaveCWRAction extends BaseAction implements UserInterface{
    private String cwr;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("cwr:[" + cwr + "]");
        ParamUtil.getInstance().updateParam(ParamInterface.CHECK_WRONG_REASON, cwr, "审核失败原因");

        //刷新缓存
        ParamUtil.refresh();

        message = "保存审核失败原因成功！";
        return SUCCESS;
    }

    public String getCwr() {
        return cwr;
    }

    public void setCwr(String cwr) {
        this.cwr = cwr;
    }
}
