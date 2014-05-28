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
 * 修改logo
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
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("logoImg:[" + logoImg + "],indexLogoWidth:[" + indexLogoWidth + "]," +
                "indexLogoHeight:[" + indexLogoHeight + "]");
        //获取当前logo图片
        String indexLogoImg = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_LOGO_IMG);
        //判字段为空 如果图片上传过 图片可以为空
        if(logoImg == null && StringUtils.isBlank(indexLogoImg)){
            message = "服务器未收到照片";
            return ERROR;
        }

        //判字段为空
        if(StringUtils.isBlank(indexLogoWidth) || StringUtils.isBlank(indexLogoHeight)){
            message = "logo的长和宽不能为空";
            return ERROR;
        }

        //创建图片
        String photoRoute = createPhoto();
        if(StringUtils.isNotBlank(photoRoute)){
            //修改首页logo
            ParamUtil.getInstance().updateParam(ParamInterface.INDEX_LOGO_IMG, photoRoute, "首页logo");
        }
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_LOGO_WIDTH, indexLogoWidth, "首页 logo 宽度");
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_LOGO_HEIGHT, indexLogoHeight, "首页 logo 高度");

        //刷新缓存
        ParamUtil.refresh();

        message = "修改logo成功！";
        return SUCCESS;
    }

    /**
     * 创建图片
     * @return
     */
    private String createPhoto(){
        if(logoImg == null){
            return StringUtils.EMPTY;
        }
        //新的图片名称
        String headPhotoName = new Date().getTime() + ".jpg";
        //服务器上的路径
        String headPhotoPath = ServletActionContext.getServletContext().getRealPath("images/admin") + "/" + headPhotoName;
        //页面引用位置 相对路径
        String headPhotoPagePath = "images/admin/" + headPhotoName;
        //服务器上的路径对应的文件
        File imageFile = new File(headPhotoPath);
        //拷贝文件
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
