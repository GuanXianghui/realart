package com.realart;

import com.realart.dao.ReviewDao;
import com.realart.entities.Review;
import com.realart.entities.User;
import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.ReviewInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.FileUtil;
import com.realart.utils.ParamUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.Date;

/**
 * �޸ĵ�ͼ
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UploadBgImgAction extends BaseAction implements UserInterface{
    private File bgImg;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("bgImg:[" + bgImg + "]");
        //���ֶ�Ϊ��
        if(bgImg == null){
            message = "������δ�յ���Ƭ";
            return ERROR;
        }

        //����ͼƬ
        String photoRoute = createPhoto();

        //�޸���ҳ��ͼ
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_BG_IMG, photoRoute, "��ҳ��ͼ");

        //ˢ�»���
        ParamUtil.refresh();

        message = "�޸ĵ�ͼ�ɹ���";
        return SUCCESS;
    }

    /**
     * ����ͼƬ
     * @return
     */
    private String createPhoto(){
        //�µ�ͼƬ����
        String headPhotoName = new Date().getTime() + ".jpg";
        //�������ϵ�·��
        String headPhotoPath = ServletActionContext.getServletContext().getRealPath("images/admin") + "/" + headPhotoName;
        //ҳ������λ�� ���·��
        String headPhotoPagePath = "images/admin/" + headPhotoName;
        //�������ϵ�·����Ӧ���ļ�
        File imageFile = new File(headPhotoPath);
        //�����ļ�
        FileUtil.copy(bgImg, imageFile);
        return headPhotoPagePath;
    }

    public File getBgImg() {
        return bgImg;
    }

    public void setBgImg(File bgImg) {
        this.bgImg = bgImg;
    }
}
