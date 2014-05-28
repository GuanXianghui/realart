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
 * 修改底图
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UploadBgImgAction extends BaseAction implements UserInterface{
    private File bgImg;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("bgImg:[" + bgImg + "]");
        //判字段为空
        if(bgImg == null){
            message = "服务器未收到照片";
            return ERROR;
        }

        //创建图片
        String photoRoute = createPhoto();

        //修改首页底图
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_BG_IMG, photoRoute, "首页底图");

        //刷新缓存
        ParamUtil.refresh();

        message = "修改底图成功！";
        return SUCCESS;
    }

    /**
     * 创建图片
     * @return
     */
    private String createPhoto(){
        //新的图片名称
        String headPhotoName = new Date().getTime() + ".jpg";
        //服务器上的路径
        String headPhotoPath = ServletActionContext.getServletContext().getRealPath("images/admin") + "/" + headPhotoName;
        //页面引用位置 相对路径
        String headPhotoPagePath = "images/admin/" + headPhotoName;
        //服务器上的路径对应的文件
        File imageFile = new File(headPhotoPath);
        //拷贝文件
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
