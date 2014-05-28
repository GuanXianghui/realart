package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.FileUtil;
import com.realart.utils.ParamUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.Date;

/**
 * �޸�logo
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UploadLogoImgAction extends BaseAction implements UserInterface{
    private File logoImg;
    private String indexLogoWidth;
    private String indexLogoHeight;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("logoImg:[" + logoImg + "],indexLogoWidth:[" + indexLogoWidth + "]," +
                "indexLogoHeight:[" + indexLogoHeight + "]");
        //��ȡ��ǰlogoͼƬ
        String indexLogoImg = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_LOGO_IMG);
        //���ֶ�Ϊ�� ���ͼƬ�ϴ��� ͼƬ����Ϊ��
        if(logoImg == null && StringUtils.isBlank(indexLogoImg)){
            message = "������δ�յ���Ƭ";
            return ERROR;
        }

        //���ֶ�Ϊ��
        if(StringUtils.isBlank(indexLogoWidth) || StringUtils.isBlank(indexLogoHeight)){
            message = "logo�ĳ��Ϳ���Ϊ��";
            return ERROR;
        }

        //����ͼƬ
        String photoRoute = createPhoto();
        if(StringUtils.isNotBlank(photoRoute)){
            //�޸���ҳlogo
            ParamUtil.getInstance().updateParam(ParamInterface.INDEX_LOGO_IMG, photoRoute, "��ҳlogo");
        }
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_LOGO_WIDTH, indexLogoWidth, "��ҳ logo ���");
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_LOGO_HEIGHT, indexLogoHeight, "��ҳ logo �߶�");

        //ˢ�»���
        ParamUtil.refresh();

        message = "�޸�logo�ɹ���";
        return SUCCESS;
    }

    /**
     * ����ͼƬ
     * @return
     */
    private String createPhoto(){
        if(logoImg == null){
            return StringUtils.EMPTY;
        }
        //�µ�ͼƬ����
        String headPhotoName = new Date().getTime() + ".jpg";
        //�������ϵ�·��
        String headPhotoPath = ServletActionContext.getServletContext().getRealPath("images/admin") + "/" + headPhotoName;
        //ҳ������λ�� ���·��
        String headPhotoPagePath = "images/admin/" + headPhotoName;
        //�������ϵ�·����Ӧ���ļ�
        File imageFile = new File(headPhotoPath);
        //�����ļ�
        FileUtil.copy(logoImg, imageFile);
        return headPhotoPagePath;
    }

    public File getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(File logoImg) {
        this.logoImg = logoImg;
    }

    public String getIndexLogoWidth() {
        return indexLogoWidth;
    }

    public void setIndexLogoWidth(String indexLogoWidth) {
        this.indexLogoWidth = indexLogoWidth;
    }

    public String getIndexLogoHeight() {
        return indexLogoHeight;
    }

    public void setIndexLogoHeight(String indexLogoHeight) {
        this.indexLogoHeight = indexLogoHeight;
    }
}
