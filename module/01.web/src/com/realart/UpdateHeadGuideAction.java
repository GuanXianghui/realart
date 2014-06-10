package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * 修改首页标题关键字
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
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("value0:[" + value0 + "],value1:[" + value1 + "],value2:[" + value2 + "],value3:[" +
                value3 + "]");
        String headGuide = "[" +
                "{\"name\":\"首页标题\",\"text\":\"" + value0 + "\"}" +
                ",{\"name\":\"关键字\",\"text\":\"" + value1 + "\"}" +
                ",{\"name\":\"网页描述\",\"text\":\"" + value2 + "\"}" +
                ",{\"name\":\"首页头部大标题\",\"text\":\"" + value3 + "\"}" +
                "]";
        //首页联系方法管理
        ParamUtil.getInstance().updateParam(HomeParamInterface.HEAD_GUIDE, headGuide, "首页标题关键字");

        //刷新缓存
        ParamUtil.refresh();

        message = "修改首页标题关键字成功！";
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
