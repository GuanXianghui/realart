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
 * 首页艺术家管理
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateYsjParamAction extends BaseAction implements UserInterface{
    private String ysjParamCount;
    private File img1;
    private File img2;
    private File img3;
    private File img4;
    private File img5;
    private File img6;
    private File img7;
    private File img8;
    private File img9;
    private File img10;
    private File img11;
    private File img12;
    private File img13;
    private File img14;
    private File img15;
    private File img16;
    private File img17;
    private File img18;
    private File img19;
    private File img20;
    private File img21;
    private File img22;
    private File img23;
    private File img24;
    private File img25;
    private File img26;
    private File img27;
    private File img28;
    private File img29;
    private File img30;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("ysjParamCount:[" + ysjParamCount + "]");

        //总个数
        int ysjParamCountInt = Integer.parseInt(ysjParamCount);
        for(int i=0;i<ysjParamCountInt;i++){
            String id = request.getParameter("id" + (i+1));
            URLTitleName urlTitleName = getURLTitleNameById(id);
            String name = request.getParameter("name" + (i+1));
            String url = request.getParameter("url" + (i+1));
            String dis = request.getParameter("dis" + (i + 1));

            File imgFile = getImgByIndex(i + 1);
            String img;
            if(null == imgFile){
                img = urlTitleName==null?StringUtils.EMPTY:urlTitleName.getImg();
            } else {
                img = BaseUtil.uploadImg(imgFile, "images/upload");
            }

            if(null == urlTitleName){//没有则新增
                //id设置为0，然后insert，id赋值为null，自增赋值
                urlTitleName = new URLTitleName(0, name, url, img, "1".equals(dis), HomeParamInterface.HOME_YSJ_LIST);
                HomeParamDao.insertysjParam(urlTitleName);
            } else {//有则更新
                //id字段不变，type不变，type用户区分表
                urlTitleName.setName(name);
                urlTitleName.setUrl(url);
                urlTitleName.setImg(img);
                urlTitleName.setDis("1".equals(dis));
                //更新启动参数
                HomeParamDao.updateysjParam(urlTitleName);
            }
        }

        //配置缓存刷新
        ysjScrollParamUtil.refresh();

        message = "首页艺术家管理成功！";
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
        if(9 == index){
            return img9;
        }
        if(10 == index){
            return img10;
        }
        if(11 == index){
            return img11;
        }
        if(12 == index){
            return img12;
        }
        if(13 == index){
            return img13;
        }
        if(14 == index){
            return img14;
        }
        if(15 == index){
            return img15;
        }
        if(16 == index){
            return img16;
        }
        if(17 == index){
            return img17;
        }
        if(18 == index){
            return img18;
        }
        if(19 == index){
            return img19;
        }
        if(20 == index){
            return img20;
        }if(21 == index){
            return img21;
        }
        if(22 == index){
            return img22;
        }
        if(23 == index){
            return img23;
        }
        if(24 == index){
            return img24;
        }
        if(25 == index){
            return img25;
        }
        if(26 == index){
            return img26;
        }
        if(27 == index){
            return img27;
        }
        if(28 == index){
            return img28;
        }
        if(29 == index){
            return img29;
        }
        if(30 == index){
            return img30;
        }
        return null;
    }

    /**
     * 根据id得到URLTitleName
     * @param id
     * @return
     * @throws Exception
     */
    private URLTitleName getURLTitleNameById(String id) throws Exception {
        List<URLTitleName> ysjParamList = ysjScrollParamUtil.getInstance().getValueByID(HomeParamInterface.HOME_YSJ_LIST);
        for(URLTitleName temp : ysjParamList){
            if(StringUtils.equals(StringUtils.EMPTY + temp.getID(), id)){
                return temp;
            }
        }
        return null;
    }

    public String getYsjParamCount() {
        return ysjParamCount;
    }

    public void setYsjParamCount(String ysjParamCount) {
        this.ysjParamCount = ysjParamCount;
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

    public File getImg9() {
        return img9;
    }

    public void setImg9(File img9) {
        this.img9 = img9;
    }

    public File getImg10() {
        return img10;
    }

    public void setImg10(File img10) {
        this.img10 = img10;
    }

    public File getImg11() {
        return img11;
    }

    public void setImg11(File img11) {
        this.img11 = img11;
    }

    public File getImg12() {
        return img12;
    }

    public void setImg12(File img12) {
        this.img12 = img12;
    }

    public File getImg13() {
        return img13;
    }

    public void setImg13(File img13) {
        this.img13 = img13;
    }

    public File getImg14() {
        return img14;
    }

    public void setImg14(File img14) {
        this.img14 = img14;
    }

    public File getImg15() {
        return img15;
    }

    public void setImg15(File img15) {
        this.img15 = img15;
    }

    public File getImg16() {
        return img16;
    }

    public void setImg16(File img16) {
        this.img16 = img16;
    }

    public File getImg17() {
        return img17;
    }

    public void setImg17(File img17) {
        this.img17 = img17;
    }

    public File getImg18() {
        return img18;
    }

    public void setImg18(File img18) {
        this.img18 = img18;
    }

    public File getImg19() {
        return img19;
    }

    public void setImg19(File img19) {
        this.img19 = img19;
    }

    public File getImg20() {
        return img20;
    }

    public void setImg20(File img20) {
        this.img20 = img20;
    }

    public File getImg21() {
        return img21;
    }

    public void setImg21(File img21) {
        this.img21 = img21;
    }

    public File getImg22() {
        return img22;
    }

    public void setImg22(File img22) {
        this.img22 = img22;
    }

    public File getImg23() {
        return img23;
    }

    public void setImg23(File img23) {
        this.img23 = img23;
    }

    public File getImg24() {
        return img24;
    }

    public void setImg24(File img24) {
        this.img24 = img24;
    }

    public File getImg25() {
        return img25;
    }

    public void setImg25(File img25) {
        this.img25 = img25;
    }

    public File getImg26() {
        return img26;
    }

    public void setImg26(File img26) {
        this.img26 = img26;
    }

    public File getImg27() {
        return img27;
    }

    public void setImg27(File img27) {
        this.img27 = img27;
    }

    public File getImg28() {
        return img28;
    }

    public void setImg28(File img28) {
        this.img28 = img28;
    }

    public File getImg29() {
        return img29;
    }

    public void setImg29(File img29) {
        this.img29 = img29;
    }

    public File getImg30() {
        return img30;
    }

    public void setImg30(File img30) {
        this.img30 = img30;
    }
}