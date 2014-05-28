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
 * ע�����ۻ�Ա
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
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("type:[" + type + "],title:[" + title + "],photo:[" + photo + "],content:[" +
                content + "]");
        //���ֶ�Ϊ��
        if(StringUtils.isBlank(title) || StringUtils.isBlank(content)){
            message = "�����������";
            return ERROR;
        }

        //����ͼƬ
        String photoRoute = StringUtils.EMPTY;
        if(photo != null){
            photoRoute = createPhoto(getUser());
        }
        //��������
        Review review = new Review(getUser().getId(), title, type, photoRoute, content,
                ReviewInterface.DEFAULT_LOCATION_BIT, date, time, getIp());
        ReviewDao.insertReview(review);

        message = "�������۳ɹ���";
        response.sendRedirect("showReview.jsp?id=" + review.getId());
        return null;
    }

    /**
     * ����ͼƬ
     * @param user
     * @return
     */
    private String createPhoto(User user){
        //�µ�ͼƬ����
        String headPhotoName = user.getId() + "_" + new Date().getTime() + ".jpg";
        //�������ϵ�·��
        String headPhotoPath = ServletActionContext.getServletContext().getRealPath("images/review") + "/" + headPhotoName;
        //ҳ������λ�� ���·��
        String headPhotoPagePath = "images/review/" + headPhotoName;
        //�������ϵ�·����Ӧ���ļ�
        File imageFile = new File(headPhotoPath);
        //�����ļ�
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
