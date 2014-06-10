package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;

/**
 * 修改首页艺术家展示
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
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("title:[" + title + "],name:[" + name + "],href:[" + href + "]");
        String ysjMore = "{\"name\":\"" + name + "\",\"href\":\"" + href + "\"}";
        //首页艺术家更多
        ParamUtil.getInstance().updateParam(HomeParamInterface.YSJ_TITLE, title, "首页艺术家展示标题");
        //首页艺术家更多
        ParamUtil.getInstance().updateParam(HomeParamInterface.YSJ_MORE, ysjMore, "首页艺术家更多");

        //刷新缓存
        ParamUtil.refresh();

        message = "修改首页艺术家展示成功！";
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
