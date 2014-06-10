package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.BaseUtil;
import com.realart.utils.ParamUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.File;

/**
 * 首页联系方法管理
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateContactGuideAction extends BaseAction implements UserInterface{
    private String guideCount;
    private File ico1;
    private File ico2;
    private File ico3;
    private File ico4;
    private File ico5;
    private File ico6;
    private File ico7;
    private File ico8;
    private File ico9;
    private File ico10;
    private File ico11;
    private File ico12;
    private File ico13;
    private File ico14;
    private File ico15;
    private File ico16;
    private File ico17;
    private File ico18;
    private File ico19;
    private File ico20;
    private File ico21;
    private File ico22;
    private File ico23;
    private File ico24;
    private File ico25;
    private File ico26;
    private File ico27;
    private File ico28;
    private File ico29;
    private File ico30;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("guideCount:[" + guideCount + "]");

        String contactGuide = StringUtils.EMPTY;
        //总个数
        int guideCountInt = Integer.parseInt(guideCount);
        for(int i=0;i<guideCountInt;i++){
            if(StringUtils.isNotBlank(contactGuide)){
                contactGuide+= SymbolInterface.SYMBOL_COMMA;
            }
            String name = request.getParameter("name" + (i+1));
            String text = request.getParameter("text" + (i+1));
            File icoFile = getIcoByIndex(i+1);
            String ico;
            if(null == icoFile){
                ico = getOldIcoFromJson(i+1);
            } else {
                ico = BaseUtil.uploadImg(icoFile, "images/upload");
            }
            contactGuide += "{\"name\":\"" + name + "\",\"text\":\"" + text + "\",\"ico\":\"" + ico + "\"}";
        }
        contactGuide = "[" + contactGuide + "]";
        //首页联系方法管理
        ParamUtil.getInstance().updateParam(HomeParamInterface.CONTACT_GUIDE, contactGuide, "联系方法");

        //刷新缓存
        ParamUtil.refresh();

        message = "首页联系方法管理成功！";
        return SUCCESS;
    }

    /**
     * 根据索引得到Ico
     * @param index
     * @return
     */
    private File getIcoByIndex(int index){
        if(1 == index){
            return ico1;
        }
        if(2 == index){
            return ico2;
        }
        if(3 == index){
            return ico3;
        }
        if(4 == index){
            return ico4;
        }
        if(5 == index){
            return ico5;
        }
        if(6 == index){
            return ico6;
        }
        if(7 == index){
            return ico7;
        }
        if(8 == index){
            return ico8;
        }
        if(9 == index){
            return ico9;
        }
        if(10 == index){
            return ico10;
        }
        if(11 == index){
            return ico11;
        }
        if(12 == index){
            return ico12;
        }
        if(13 == index){
            return ico13;
        }
        if(14 == index){
            return ico14;
        }
        if(15 == index){
            return ico15;
        }
        if(16 == index){
            return ico16;
        }
        if(17 == index){
            return ico17;
        }
        if(18 == index){
            return ico18;
        }
        if(19 == index){
            return ico19;
        }
        if(20 == index){
            return ico20;
        }if(21 == index){
            return ico21;
        }
        if(22 == index){
            return ico22;
        }
        if(23 == index){
            return ico23;
        }
        if(24 == index){
            return ico24;
        }
        if(25 == index){
            return ico25;
        }
        if(26 == index){
            return ico26;
        }
        if(27 == index){
            return ico27;
        }
        if(28 == index){
            return ico28;
        }
        if(29 == index){
            return ico29;
        }
        if(30 == index){
            return ico30;
        }
        return null;
    }

    /**
     * 得到json里老的图片地址
     * @param index 从1开始
     * @return
     * @throws Exception
     */
    private String getOldIcoFromJson(int index) throws Exception {
        //首页联系方法管理
        String contactGuide= ParamUtil.getInstance().getValueByName(HomeParamInterface.CONTACT_GUIDE);
        JSONArray json = JSONArray.fromObject(contactGuide);
        if(index > json.size()){
            return StringUtils.EMPTY;
        }
        JSONObject temp = json.getJSONObject(index-1);
        return (StringUtils.trimToEmpty((String) temp.get("ico")));
    }

    public String getGuideCount() {
        return guideCount;
    }

    public void setGuideCount(String guideCount) {
        this.guideCount = guideCount;
    }

    public File getIco1() {
        return ico1;
    }

    public void setIco1(File ico1) {
        this.ico1 = ico1;
    }

    public File getIco2() {
        return ico2;
    }

    public void setIco2(File ico2) {
        this.ico2 = ico2;
    }

    public File getIco3() {
        return ico3;
    }

    public void setIco3(File ico3) {
        this.ico3 = ico3;
    }

    public File getIco4() {
        return ico4;
    }

    public void setIco4(File ico4) {
        this.ico4 = ico4;
    }

    public File getIco5() {
        return ico5;
    }

    public void setIco5(File ico5) {
        this.ico5 = ico5;
    }

    public File getIco6() {
        return ico6;
    }

    public void setIco6(File ico6) {
        this.ico6 = ico6;
    }

    public File getIco7() {
        return ico7;
    }

    public void setIco7(File ico7) {
        this.ico7 = ico7;
    }

    public File getIco8() {
        return ico8;
    }

    public void setIco8(File ico8) {
        this.ico8 = ico8;
    }

    public File getIco9() {
        return ico9;
    }

    public void setIco9(File ico9) {
        this.ico9 = ico9;
    }

    public File getIco10() {
        return ico10;
    }

    public void setIco10(File ico10) {
        this.ico10 = ico10;
    }

    public File getIco11() {
        return ico11;
    }

    public void setIco11(File ico11) {
        this.ico11 = ico11;
    }

    public File getIco12() {
        return ico12;
    }

    public void setIco12(File ico12) {
        this.ico12 = ico12;
    }

    public File getIco13() {
        return ico13;
    }

    public void setIco13(File ico13) {
        this.ico13 = ico13;
    }

    public File getIco14() {
        return ico14;
    }

    public void setIco14(File ico14) {
        this.ico14 = ico14;
    }

    public File getIco15() {
        return ico15;
    }

    public void setIco15(File ico15) {
        this.ico15 = ico15;
    }

    public File getIco16() {
        return ico16;
    }

    public void setIco16(File ico16) {
        this.ico16 = ico16;
    }

    public File getIco17() {
        return ico17;
    }

    public void setIco17(File ico17) {
        this.ico17 = ico17;
    }

    public File getIco18() {
        return ico18;
    }

    public void setIco18(File ico18) {
        this.ico18 = ico18;
    }

    public File getIco19() {
        return ico19;
    }

    public void setIco19(File ico19) {
        this.ico19 = ico19;
    }

    public File getIco20() {
        return ico20;
    }

    public void setIco20(File ico20) {
        this.ico20 = ico20;
    }

    public File getIco21() {
        return ico21;
    }

    public void setIco21(File ico21) {
        this.ico21 = ico21;
    }

    public File getIco22() {
        return ico22;
    }

    public void setIco22(File ico22) {
        this.ico22 = ico22;
    }

    public File getIco23() {
        return ico23;
    }

    public void setIco23(File ico23) {
        this.ico23 = ico23;
    }

    public File getIco24() {
        return ico24;
    }

    public void setIco24(File ico24) {
        this.ico24 = ico24;
    }

    public File getIco25() {
        return ico25;
    }

    public void setIco25(File ico25) {
        this.ico25 = ico25;
    }

    public File getIco26() {
        return ico26;
    }

    public void setIco26(File ico26) {
        this.ico26 = ico26;
    }

    public File getIco27() {
        return ico27;
    }

    public void setIco27(File ico27) {
        this.ico27 = ico27;
    }

    public File getIco28() {
        return ico28;
    }

    public void setIco28(File ico28) {
        this.ico28 = ico28;
    }

    public File getIco29() {
        return ico29;
    }

    public void setIco29(File ico29) {
        this.ico29 = ico29;
    }

    public File getIco30() {
        return ico30;
    }

    public void setIco30(File ico30) {
        this.ico30 = ico30;
    }
}
