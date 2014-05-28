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
 * 修改真艺网图片
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
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("realartImg:[" + realartImg + "],indexRealartWidth:[" + indexRealartWidth + "]," +
                "indexRealartHeight:[" + indexRealartHeight + "]");
        //获取当前realart图片
        String indexRealartImg = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_REALART_IMG);
        //判字段为空 如果图片上传过 图片可以为空
        if(realartImg == null && StringUtils.isBlank(indexRealartImg)){
            message = "服务器未收到照片";
            return ERROR;
        }

        //判字段为空
        if(StringUtils.isBlank(indexRealartWidth) || StringUtils.isBlank(indexRealartHeight)){
            message = "真艺网图的长和宽不能为空";
            return ERROR;
        }

        //创建图片
        String photoRoute = createPhoto();
        if(StringUtils.isNotBlank(photoRoute)){
            //修改首页realart
            ParamUtil.getInstance().updateParam(ParamInterface.INDEX_REALART_IMG, photoRoute, "首页真艺网");
        }
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_REALART_WIDTH, indexRealartWidth, "首页 真艺网 宽度");
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_REALART_HEIGHT, indexRealartHeight, "首页 真艺网 高度");

        //刷新缓存
        ParamUtil.refresh();

        message = "修改真艺网图片成功！";
        return SUCCESS;
    }

    /**
     * 创建图片
     * @return
     */
    private String createPhoto(){
        if(realartImg == null){
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
