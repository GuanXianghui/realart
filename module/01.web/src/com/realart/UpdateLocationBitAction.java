package com.realart;

import com.realart.dao.ReviewDao;
import com.realart.entities.Review;
import com.realart.interfaces.BaseInterface;
import com.realart.interfaces.UserInterface;
import org.apache.commons.lang.StringUtils;

/**
 * 修改位置位图
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
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("reviewId:[" + reviewId + "],type:[" + type + "],value:[" + value + "]");
        //判字段为空
        if(StringUtils.isBlank(reviewId) || StringUtils.isBlank(type) || StringUtils.isBlank(value)){
            message = "请输入必输项";
            return ERROR;
        }

        //判是否管理员
        boolean isAdminLogin = Boolean.TRUE.equals(request.getSession().getAttribute(BaseInterface.IS_ADMIN_USER));
        if(!isAdminLogin){
            response.sendRedirect("index.jsp");
            return null;
        }

        //判评论存在
        Review review = ReviewDao.getReviewById(Integer.parseInt(reviewId));
        if(review == null){
            response.sendRedirect("index.jsp");
            return null;
        }

        //判类型合法
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
        //更新位置信息
        ReviewDao.updateLocationBit(review);
        message = "更新位置信息成功！";
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
