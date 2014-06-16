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
 * 修改作品
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
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("reviewId:[" + reviewId + "],title:[" + title + "],type:[" + type + "],photo:[" + photo +
                "],content:[" + content + "]");
        //判字段为空
        if(StringUtils.isBlank(reviewId) || StringUtils.isBlank(title) || StringUtils.isBlank(content)){
            message = "请输入必输项";
            return ERROR;
        }

        Review review = ReviewDao.getReviewById(Integer.parseInt(reviewId));
        if(null == review){
            message = "你的操作有误!";
            return ERROR;
        }

        review.setTitle(title);
        review.setType(type);
        //上传图片
        if(null != photo){
            review.setPhoto(createPhoto(photo));
        }
        review.setContent(content);
        ReviewDao.updateReview(review);

        message = "修改评论成功！";
        return SUCCESS;
    }

    /**
     * 创建图片
     * @param photo
     * @return
     */
    private String createPhoto(File photo){
        if(null == photo){
            return StringUtils.EMPTY;
        }
        //新的图片名称
        String photoName = getUser().getId() + "_" + new Date().getTime() + ".jpg";
        //服务器上的路径
        String photoPath = ServletActionContext.getServletContext().getRealPath("images/review") + "/" + photoName;
        //页面引用位置 相对路径
        String photoPagePath = "images/review/" + photoName;
        //服务器上的路径对应的文件
        File imageFile = new File(photoPath);
        //拷贝文件
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
