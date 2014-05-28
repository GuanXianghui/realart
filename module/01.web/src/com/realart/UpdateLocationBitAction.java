package com.realart;

import com.realart.dao.ReviewDao;
import com.realart.entities.Review;
import com.realart.interfaces.BaseInterface;
import com.realart.interfaces.UserInterface;
import org.apache.commons.lang.StringUtils;

/**
 * �޸�λ��λͼ
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateLocationBitAction extends BaseAction implements UserInterface{
    private String reviewId;
    private String type;
    private String value;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("reviewId:[" + reviewId + "],type:[" + type + "],value:[" + value + "]");
        //���ֶ�Ϊ��
        if(StringUtils.isBlank(reviewId) || StringUtils.isBlank(type) || StringUtils.isBlank(value)){
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
        Review review = ReviewDao.getReviewById(Integer.parseInt(reviewId));
        if(review == null){
            response.sendRedirect("index.jsp");
            return null;
        }

        //�����ͺϷ�
        if(StringUtils.equals(type, "zdtj")){
            review.setZdtj(StringUtils.equals("1", value));
        } else if(StringUtils.equals(type, "zdtjTop")){
            review.setZdtjTop(StringUtils.equals("1", value));
        } else if(StringUtils.equals(type, "mwgs")){
            review.setMwgs(StringUtils.equals("1", value));
        } else if(StringUtils.equals(type, "mwgsTop")){
            review.setMwgsTop(StringUtils.equals("1", value));
        } else if(StringUtils.equals(type, "bzyc")){
            review.setBzyc(StringUtils.equals("1", value));
        } else if(StringUtils.equals(type, "bzycTop")){
            review.setBzycTop(StringUtils.equals("1", value));
        } else if(StringUtils.equals(type, "hyzlTop")){
            review.setHyzlTop(StringUtils.equals("1", value));
        } else {
            response.sendRedirect("index.jsp");
            return null;
        }
        //����λ����Ϣ
        ReviewDao.updateLocationBit(review);
        message = "����λ����Ϣ�ɹ���";
        response.sendRedirect("showReview.jsp?id=" + review.getId());
        return null;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
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
