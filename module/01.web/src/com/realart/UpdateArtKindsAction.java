package com.realart;

import com.realart.dao.UserDao;
import com.realart.interfaces.UserInterface;

/**
 * 修改艺术品所有分类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateArtKindsAction extends BaseAction implements UserInterface{
    private String artKinds;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("artKinds:[" + artKinds + "]");
        getUser().setArtKinds(artKinds);
        UserDao.updateArtKinds(getUser());
        //刷新session缓存中的user
        refreshSessionUser(getUser());

        message = "修改艺术品所有分类成功！";
        return SUCCESS;
    }

    public String getArtKinds() {
        return artKinds;
    }

    public void setArtKinds(String artKinds) {
        this.artKinds = artKinds;
    }
}
