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
 * 首页真艺展厅管理
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateRealArtRoomAction extends BaseAction implements UserInterface{
    private String realArtCount;
    private File url1;
    private File url2;
    private File url3;
    private File url4;
    private File url5;
    private File url6;
    private File url7;
    private File url8;
    private File url9;
    private File url10;
    private File url11;
    private File url12;
    private File url13;
    private File url14;
    private File url15;
    private File url16;
    private File url17;
    private File url18;
    private File url19;
    private File url20;
    private File url21;
    private File url22;
    private File url23;
    private File url24;
    private File url25;
    private File url26;
    private File url27;
    private File url28;
    private File url29;
    private File url30;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("realArtCount:[" + realArtCount + "]");

        String slider01 = StringUtils.EMPTY;
        //总个数
        int realArtCountInt = Integer.parseInt(realArtCount);
        for(int i=0;i<realArtCountInt;i++){
            if(StringUtils.isNotBlank(slider01)){
                slider01+= SymbolInterface.SYMBOL_COMMA;
            }
            String title = request.getParameter("title" + (i+1));
            String href = request.getParameter("href" + (i+1));
            String type = request.getParameter("type" + (i+1));
            File urlFile = getUrlByIndex(i + 1);
            String url;
            if(null == urlFile){
                url = getOldUrlFromJson(i + 1);
            } else {
                url = BaseUtil.uploadImg(urlFile, "images/upload");
            }
            slider01 += "{\"title\":\"" + title + "\",\"url\":\"" + url + "\",\"href\":\"" + href + "\",\"type\":\"" + type + "\"}";
        }
        slider01 = "[" + slider01 + "]";
        //首页真艺展厅
        ParamUtil.getInstance().updateParam(HomeParamInterface.SLIDER_01, slider01, "首页真艺展厅");

        //刷新缓存
        ParamUtil.refresh();

        message = "首页真艺展厅管理成功！";
        return SUCCESS;
    }

    /**
     * 根据索引得到url
     * @param index
     * @return
     */
    private File getUrlByIndex(int index){
        if(1 == index){
            return url1;
        }
        if(2 == index){
            return url2;
        }
        if(3 == index){
            return url3;
        }
        if(4 == index){
            return url4;
        }
        if(5 == index){
            return url5;
        }
        if(6 == index){
            return url6;
        }
        if(7 == index){
            return url7;
        }
        if(8 == index){
            return url8;
        }
        if(9 == index){
            return url9;
        }
        if(10 == index){
            return url10;
        }
        if(11 == index){
            return url11;
        }
        if(12 == index){
            return url12;
        }
        if(13 == index){
            return url13;
        }
        if(14 == index){
            return url14;
        }
        if(15 == index){
            return url15;
        }
        if(16 == index){
            return url16;
        }
        if(17 == index){
            return url17;
        }
        if(18 == index){
            return url18;
        }
        if(19 == index){
            return url19;
        }
        if(20 == index){
            return url20;
        }if(21 == index){
            return url21;
        }
        if(22 == index){
            return url22;
        }
        if(23 == index){
            return url23;
        }
        if(24 == index){
            return url24;
        }
        if(25 == index){
            return url25;
        }
        if(26 == index){
            return url26;
        }
        if(27 == index){
            return url27;
        }
        if(28 == index){
            return url28;
        }
        if(29 == index){
            return url29;
        }
        if(30 == index){
            return url30;
        }
        return null;
    }

    /**
     * 得到json里老的图片地址
     * @param index 从1开始
     * @return
     * @throws Exception
     */
    private String getOldUrlFromJson(int index) throws Exception {
        //首页联系方法管理
        String slider01= ParamUtil.getInstance().getValueByName(HomeParamInterface.SLIDER_01);
        JSONArray json = JSONArray.fromObject(slider01);
        if(index > json.size()){
            return StringUtils.EMPTY;
        }
        JSONObject temp = json.getJSONObject(index-1);
        return (StringUtils.trimToEmpty((String) temp.get("url")));
    }

    public String getRealArtCount() {
        return realArtCount;
    }

    public void setRealArtCount(String realArtCount) {
        this.realArtCount = realArtCount;
    }

    public File getUrl1() {
        return url1;
    }

    public void setUrl1(File url1) {
        this.url1 = url1;
    }

    public File getUrl2() {
        return url2;
    }

    public void setUrl2(File url2) {
        this.url2 = url2;
    }

    public File getUrl3() {
        return url3;
    }

    public void setUrl3(File url3) {
        this.url3 = url3;
    }

    public File getUrl4() {
        return url4;
    }

    public void setUrl4(File url4) {
        this.url4 = url4;
    }

    public File getUrl5() {
        return url5;
    }

    public void setUrl5(File url5) {
        this.url5 = url5;
    }

    public File getUrl6() {
        return url6;
    }

    public void setUrl6(File url6) {
        this.url6 = url6;
    }

    public File getUrl7() {
        return url7;
    }

    public void setUrl7(File url7) {
        this.url7 = url7;
    }

    public File getUrl8() {
        return url8;
    }

    public void setUrl8(File url8) {
        this.url8 = url8;
    }

    public File getUrl9() {
        return url9;
    }

    public void setUrl9(File url9) {
        this.url9 = url9;
    }

    public File getUrl10() {
        return url10;
    }

    public void setUrl10(File url10) {
        this.url10 = url10;
    }

    public File getUrl11() {
        return url11;
    }

    public void setUrl11(File url11) {
        this.url11 = url11;
    }

    public File getUrl12() {
        return url12;
    }

    public void setUrl12(File url12) {
        this.url12 = url12;
    }

    public File getUrl13() {
        return url13;
    }

    public void setUrl13(File url13) {
        this.url13 = url13;
    }

    public File getUrl14() {
        return url14;
    }

    public void setUrl14(File url14) {
        this.url14 = url14;
    }

    public File getUrl15() {
        return url15;
    }

    public void setUrl15(File url15) {
        this.url15 = url15;
    }

    public File getUrl16() {
        return url16;
    }

    public void setUrl16(File url16) {
        this.url16 = url16;
    }

    public File getUrl17() {
        return url17;
    }

    public void setUrl17(File url17) {
        this.url17 = url17;
    }

    public File getUrl18() {
        return url18;
    }

    public void setUrl18(File url18) {
        this.url18 = url18;
    }

    public File getUrl19() {
        return url19;
    }

    public void setUrl19(File url19) {
        this.url19 = url19;
    }

    public File getUrl20() {
        return url20;
    }

    public void setUrl20(File url20) {
        this.url20 = url20;
    }

    public File getUrl21() {
        return url21;
    }

    public void setUrl21(File url21) {
        this.url21 = url21;
    }

    public File getUrl22() {
        return url22;
    }

    public void setUrl22(File url22) {
        this.url22 = url22;
    }

    public File getUrl23() {
        return url23;
    }

    public void setUrl23(File url23) {
        this.url23 = url23;
    }

    public File getUrl24() {
        return url24;
    }

    public void setUrl24(File url24) {
        this.url24 = url24;
    }

    public File getUrl25() {
        return url25;
    }

    public void setUrl25(File url25) {
        this.url25 = url25;
    }

    public File getUrl26() {
        return url26;
    }

    public void setUrl26(File url26) {
        this.url26 = url26;
    }

    public File getUrl27() {
        return url27;
    }

    public void setUrl27(File url27) {
        this.url27 = url27;
    }

    public File getUrl28() {
        return url28;
    }

    public void setUrl28(File url28) {
        this.url28 = url28;
    }

    public File getUrl29() {
        return url29;
    }

    public void setUrl29(File url29) {
        this.url29 = url29;
    }

    public File getUrl30() {
        return url30;
    }

    public void setUrl30(File url30) {
        this.url30 = url30;
    }
}