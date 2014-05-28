package com.realart;

import com.realart.dao.ArtDao;
import com.realart.entities.Art;
import com.realart.interfaces.BaseInterface;
import com.realart.interfaces.UserInterface;
import org.apache.commons.lang.StringUtils;

/**
 * �޸�����Ʒλ��λͼ
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateArtLocationBitAction extends BaseAction implements UserInterface{
    private String artId;
    private String type;
    private String value;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("artId:[" + artId + "],type:[" + type + "],value:[" + value + "]");
        //���ֶ�Ϊ��
        if(StringUtils.isBlank(artId) || StringUtils.isBlank(type) || StringUtils.isBlank(value)){
            message = "�����������";
            return ERROR;
        }

        //���Ƿ����Ա
        boolean isAdminLogin = Boolean.TRUE.equals(request.getSession().getAttribute(BaseInterface.IS_ADMIN_USER));
        if(!isAdminLogin){
            response.sendRedirect("index.jsp");
            return null;
        }

        //�����۴���
        Art art = ArtDao.getArtById(Integer.parseInt(artId));
        if(art == null){
            response.sendRedirect("index.jsp");
            return null;
        }

        //�����ͺϷ�
        if(StringUtils.equals(type, "yszlTop")){
            art.setYszlTop(StringUtils.equals("1", value));
        } else {
            response.sendRedirect("index.jsp");
            return null;
        }
        //����λ����Ϣ
        ArtDao.updateLocationBit(art);
        message = "����λ����Ϣ�ɹ���";
        response.sendRedirect("showArt.jsp?id=" + art.getId());
        return null;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
