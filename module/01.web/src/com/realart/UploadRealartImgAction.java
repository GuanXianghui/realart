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
 * �޸�������ͼƬ
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UploadRealartImgAction extends BaseAction implements UserInterface{
    private File realartImg;
    private String indexRealartWidth;
    private String indexRealartHeight;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("realartImg:[" + realartImg + "],indexRealartWidth:[" + indexRealartWidth + "]," +
                "indexRealartHeight:[" + indexRealartHeight + "]");
        //��ȡ��ǰrealartͼƬ
        String indexRealartImg = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_REALART_IMG);
        //���ֶ�Ϊ�� ���ͼƬ�ϴ��� ͼƬ����Ϊ��
        if(realartImg == null && StringUtils.isBlank(indexRealartImg)){
            message = "������δ�յ���Ƭ";
            return ERROR;
        }

        //���ֶ�Ϊ��
        if(StringUtils.isBlank(indexRealartWidth) || StringUtils.isBlank(indexRealartHeight)){
            message = "������ͼ�ĳ��Ϳ���Ϊ��";
            return ERROR;
        }

        //����ͼƬ
        String photoRoute = createPhoto();
        if(StringUtils.isNotBlank(photoRoute)){
            //�޸���ҳrealart
            ParamUtil.getInstance().updateParam(ParamInterface.INDEX_REALART_IMG, photoRoute, "��ҳ������");
        }
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_REALART_WIDTH, indexRealartWidth, "��ҳ ������ ���");
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_REALART_HEIGHT, indexRealartHeight, "��ҳ ������ �߶�");

        //ˢ�»���
        ParamUtil.refresh();

        message = "�޸�������ͼƬ�ɹ���";
        return SUCCESS;
    }

    /**
     * ����ͼƬ
     * @return
     */
    private String createPhoto(){
        if(realartImg == null){
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
        FileUtil.copy(realartImg, imageFile);
        return headPhotoPagePath;
    }

    public File getRealartImg() {
        return realartImg;
    }

    public void setRealartImg(File realartImg) {
        this.realartImg = realartImg;
    }

    public String getIndexRealartWidth() {
        return indexRealartWidth;
    }

    public void setIndexRealartWidth(String indexRealartWidth) {
        this.indexRealartWidth = indexRealartWidth;
    }

    public String getIndexRealartHeight() {
        return indexRealartHeight;
    }

    public void setIndexRealartHeight(String indexRealartHeight) {
        this.indexRealartHeight = indexRealartHeight;
    }
}
