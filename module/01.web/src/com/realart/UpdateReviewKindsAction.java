package com.realart;

import com.realart.dao.UserDao;
import com.realart.interfaces.UserInterface;

/**
 * 修改评论所有分类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateReviewKindsAction extends BaseAction implements UserInterface{
    private String reviewKinds;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("reviewKinds:[" + reviewKinds + "]");
        getUser().setArtKinds(reviewKinds);
        UserDao.updateArtKinds(getUser());
        //刷新session缓存中的user
        refreshSessionUser(getUser());

        message = "修改评论所有分类成功！";
        return SUCCESS;
    }

    public String getReviewKinds() {
        return reviewKinds;
    }

    public void setReviewKinds(String reviewKinds) {
        this.reviewKinds = reviewKinds;
    }
}
