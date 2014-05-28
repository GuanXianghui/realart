package com.realart;

import com.realart.dao.ReviewDao;
import com.realart.entities.Review;
import com.realart.entities.User;
import com.realart.interfaces.ReviewInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.Date;

/**
 * 注册评论会员
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class CreateReviewAction extends BaseAction implements UserInterface{
    private String type;
    private String title;
    private File photo;
    private String content;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("type:[" + type + "],title:[" + title + "],photo:[" + photo + "],content:[" +
                content + "]");
        //判字段为空
        if(StringUtils.isBlank(title) || StringUtils.isBlank(content)){
            message = "请输入必输项";
            return ERROR;
        }

        //创建图片
        String photoRoute = StringUtils.EMPTY;
        if(photo != null){
            photoRoute = createPhoto(getUser());
        }
        //创建评论
        Review review = new Review(getUser().getId(), title, type, photoRoute, content,
                ReviewInterface.DEFAULT_LOCATION_BIT, date, time, getIp());
        ReviewDao.insertReview(review);

        message = "创建评论成功！";
        response.sendRedirect("showReview.jsp?id=" + review.getId());
        return null;
    }

    /**
     * 创建图片
     * @param user
     * @return
     */
    private String createPhoto(User user){
        //新的图片名称
        String headPhotoName = user.getId() + "_" + new Date().getTime() + ".jpg";
        //服务器上的路径
        String headPhotoPath = ServletActionContext.getServletContext().getRealPath("images/review") + "/" + headPhotoName;
        //页面引用位置 相对路径
        String headPhotoPagePath = "images/review/" + headPhotoName;
        //服务器上的路径对应的文件
        File imageFile = new File(headPhotoPath);
        //拷贝文件
        FileUtil.copy(photo, imageFile);
        return headPhotoPagePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
