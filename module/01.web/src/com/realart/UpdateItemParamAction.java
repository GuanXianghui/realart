package com.realart;

import com.realart.dao.HomeParamDao;
import com.realart.entities.URLTitleName;
import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.BaseUtil;
import com.realart.utils.ysjScrollParamUtil;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.List;

/**
 * 首页八框导航管理
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateItemParamAction extends BaseAction implements UserInterface{
    private File img1;
    private File img2;
    private File img3;
    private File img4;
    private File img5;
    private File img6;
    private File img7;
    private File img8;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        //总个数
        for(int i=0;i<8;i++){
            URLTitleName urlTitleName = getURLTitleNameById((i+1));
            String name = request.getParameter("name" + (i+1));
            String dis = request.getParameter("dis" + (i+1));

            File imgFile = getImgByIndex(i + 1);
            String img;
            if(null == imgFile){
                img = urlTitleName==null?StringUtils.EMPTY:urlTitleName.getImg();
            } else {
                img = BaseUtil.uploadImg(imgFile, "images/upload");
            }

            //直接更新
            //id字段不变，url，不变，type不变，type用户区分表
            urlTitleName.setName(name);
            urlTitleName.setImg(img);
            urlTitleName.setDis("1".equals(dis));
            //更新启动参数
            HomeParamDao.updateysjParam(urlTitleName);
        }

        //配置缓存刷新
        ysjScrollParamUtil.refresh();

        message = "首页八框导航管理成功！";
        return SUCCESS;
    }

    /**
     * 根据索引得到img
     * @param index
     * @return
     */
    private File getImgByIndex(int index){
        if(1 == index){
            return img1;
        }
        if(2 == index){
            return img2;
        }
        if(3 == index){
            return img3;
        }
        if(4 == index){
            return img4;
        }
        if(5 == index){
            return img5;
        }
        if(6 == index){
            return img6;
        }
        if(7 == index){
            return img7;
        }
        if(8 == index){
            return img8;
        }
        return null;
    }

    /**
     * 根据id得到URLTitleName
     * @param id
     * @return
     * @throws Exception
     */
    private URLTitleName getURLTitleNameById(int id) throws Exception {
        List<URLTitleName> itemParams = ysjScrollParamUtil.getInstance().getValueByID(HomeParamInterface.ITEM_GUIDE);
        for(URLTitleName temp : itemParams){
            if(temp.getID() == id){
                return temp;
            }
        }
        return null;
    }

    public File getImg1() {
        return img1;
    }

    public void setImg1(File img1) {
        this.img1 = img1;
    }

    public File getImg2() {
        return img2;
    }

    public void setImg2(File img2) {
        this.img2 = img2;
    }

    public File getImg3() {
        return img3;
    }

    public void setImg3(File img3) {
        this.img3 = img3;
    }

    public File getImg4() {
        return img4;
    }

    public void setImg4(File img4) {
        this.img4 = img4;
    }

    public File getImg5() {
        return img5;
    }

    public void setImg5(File img5) {
        this.img5 = img5;
    }

    public File getImg6() {
        return img6;
    }

    public void setImg6(File img6) {
        this.img6 = img6;
    }

    public File getImg7() {
        return img7;
    }

    public void setImg7(File img7) {
        this.img7 = img7;
    }

    public File getImg8() {
        return img8;
    }

    public void setImg8(File img8) {
        this.img8 = img8;
    }
}