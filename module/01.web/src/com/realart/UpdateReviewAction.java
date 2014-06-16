package com.realart;

import com.realart.dao.ReviewDao;
import com.realart.entities.Review;
import com.realart.interfaces.ArtInterface;
import com.realart.utils.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.Date;

/**
 * �޸���Ʒ
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateReviewAction extends BaseAction implements ArtInterface {
    private String reviewId;
    private String title;
    private String type;
    private File photo;
    private String content;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("reviewId:[" + reviewId + "],title:[" + title + "],type:[" + type + "],photo:[" + photo +
                "],content:[" + content + "]");
        //���ֶ�Ϊ��
        if(StringUtils.isBlank(reviewId) || StringUtils.isBlank(title) || StringUtils.isBlank(content)){
            message = "�����������";
            return ERROR;
        }

        Review review = ReviewDao.getReviewById(Integer.parseInt(reviewId));
        if(null == review){
            message = "��Ĳ�������!";
            return ERROR;
        }

        review.setTitle(title);
        review.setType(type);
        //�ϴ�ͼƬ
        if(null != photo){
            review.setPhoto(createPhoto(photo));
        }
        review.setContent(content);
        ReviewDao.updateReview(review);

        message = "�޸����۳ɹ���";
        return SUCCESS;
    }

    /**
     * ����ͼƬ
     * @param photo
     * @return
     */
    private String createPhoto(File photo){
        if(null == photo){
            return StringUtils.EMPTY;
        }
        //�µ�ͼƬ����
        String photoName = getUser().getId() + "_" + new Date().getTime() + ".jpg";
        //�������ϵ�·��
        String photoPath = ServletActionContext.getServletContext().getRealPath("images/review") + "/" + photoName;
        //ҳ������λ�� ���·��
        String photoPagePath = "images/review/" + photoName;
        //�������ϵ�·����Ӧ���ļ�
        File imageFile = new File(photoPath);
        //�����ļ�
        FileUtil.copy(photo, imageFile);
        return photoPagePath;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
