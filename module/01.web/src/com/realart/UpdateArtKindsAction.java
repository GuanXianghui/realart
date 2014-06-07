package com.realart;

import com.realart.dao.UserDao;
import com.realart.interfaces.UserInterface;

/**
 * �޸�����Ʒ���з���
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateArtKindsAction extends BaseAction implements UserInterface{
    private String artKinds;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("artKinds:[" + artKinds + "]");
        getUser().setArtKinds(artKinds);
        UserDao.updateArtKinds(getUser());
        //ˢ��session�����е�user
        refreshSessionUser(getUser());

        message = "�޸�����Ʒ���з���ɹ���";
        return SUCCESS;
    }

    public String getArtKinds() {
        return artKinds;
    }

    public void setArtKinds(String artKinds) {
        this.artKinds = artKinds;
    }
}
