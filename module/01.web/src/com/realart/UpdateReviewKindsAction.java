package com.realart;

import com.realart.dao.UserDao;
import com.realart.interfaces.UserInterface;

/**
 * �޸��������з���
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateReviewKindsAction extends BaseAction implements UserInterface{
    private String reviewKinds;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("reviewKinds:[" + reviewKinds + "]");
        getUser().setArtKinds(reviewKinds);
        UserDao.updateArtKinds(getUser());
        //ˢ��session�����е�user
        refreshSessionUser(getUser());

        message = "�޸��������з���ɹ���";
        return SUCCESS;
    }

    public String getReviewKinds() {
        return reviewKinds;
    }

    public void setReviewKinds(String reviewKinds) {
        this.reviewKinds = reviewKinds;
    }
}
