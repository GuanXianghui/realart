package com.realart;

import com.realart.entities.URLTitleName;
import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.BaseUtil;
import com.realart.utils.ysjScrollParamUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.List;

/**
 * 首页八框内容管理
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateItemListAction extends BaseAction implements UserInterface{
    private String id;
    private String size;
    private String type;
    private String dis;
    private String bigTitle;//当内容展示类型为4才需要配置
    private File imgUrl1;
    private File imgUrl2;
    private File imgUrl3;
    private File imgUrl4;
    private File imgUrl5;
    private File imgUrl6;
    private File imgUrl7;
    private File imgUrl8;
    private File imgUrl9;
    private File imgUrl10;
    private File imgUrl11;
    private File imgUrl12;
    private File imgUrl13;
    private File imgUrl14;
    private File imgUrl15;
    private File imgUrl16;
    private File imgUrl17;
    private File imgUrl18;
    private File imgUrl19;
    private File imgUrl20;
    private File imgUrl21;
    private File imgUrl22;
    private File imgUrl23;
    private File imgUrl24;
    private File imgUrl25;
    private File imgUrl26;
    private File imgUrl27;
    private File imgUrl28;
    private File imgUrl29;
    private File imgUrl30;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("id:[" + id + "]," + "size:[" + size + "]," + "type:[" + type + "]," + "dis:[" + dis + "]");

        //根据id得到URLTitleName
        URLTitleName item = getURLTitleNameById(id);
        //新数据
        String newContent = StringUtils.EMPTY;
        String newTitle = StringUtils.EMPTY;
        String newAlt = StringUtils.EMPTY;
        String newImgUrl = StringUtils.EMPTY;
        String newItemUrl = StringUtils.EMPTY;
        String newBtnTitle = StringUtils.EMPTY;//当内容展示类型为4才需要配置

        //老数据
        JSONObject itemImg = JSONObject.fromObject(item.getImg());//内容展示类型+图片地址+图片个数
        //int oldType = itemImg.getInt("type");//内容展示类型 这里没用
        JSONArray imgUrlJsonArray = JSONArray.fromObject(itemImg.get("url"));//图片地址 用户取出老图片
        int oldSize = itemImg.getInt("size");//图片个数 用户比较 哪些是多出来的部分

        //JSONArray itemUrlJsonArray = JSONArray.fromObject(item.getUrl());//链接地址 这里没用

        //JSONObject itemName = JSONObject.fromObject(item.getName());//标题+提示+内容
        //JSONArray titleJsonArray = JSONArray.fromObject(itemName.get("title"));//标题 这里没用
        //JSONArray altJsonArray = JSONArray.fromObject(itemName.get("alt"));//提示 这里没用
        //JSONArray contentJsonArray = JSONArray.fromObject(itemName.get("content"));//内容 这里没用

        for(int i=0;i<Integer.parseInt(size);i++){
            String content = request.getParameter("content" + (i + 1));
            String title = request.getParameter("title" + (i+1));
            String alt = request.getParameter("alt" + (i+1));
            String itemUrl = request.getParameter("itemUrl" + (i + 1));
            String btnTitle = request.getParameter("btnTitle" + (i + 1));

            File imgUrlFile = getImgUrlByIndex(i + 1);
            String imgUrl;
            if(null == imgUrlFile){
                imgUrl = i+1>oldSize?StringUtils.EMPTY:imgUrlJsonArray.getString(i);
            } else {
                imgUrl = BaseUtil.uploadImg(imgUrlFile, "images/upload");
            }

            if(StringUtils.isNotBlank(newContent)){
                newContent += SymbolInterface.SYMBOL_COMMA;
                newTitle += SymbolInterface.SYMBOL_COMMA;
                newAlt += SymbolInterface.SYMBOL_COMMA;
                newImgUrl += SymbolInterface.SYMBOL_COMMA;
                newItemUrl += SymbolInterface.SYMBOL_COMMA;
                newBtnTitle += SymbolInterface.SYMBOL_COMMA;
            }
            newContent += "\"" + content + "\"";
            newTitle += "\"" + title + "\"";
            newAlt += "\"" + alt + "\"";
            newImgUrl += "\"" + imgUrl + "\"";
            newItemUrl += "\"" + itemUrl + "\"";
            newBtnTitle += "\"" + btnTitle + "\"";
        }

        /**
         * 格式如下：
         * {
         "title":["标题1","标题2","标题3"],
         "alt":["提示1","提示2","提示3"],
         "content":["内容1","内容2","内容3"]
         }
         */
        item.setName("{" +
                "\"title\":[" + newTitle + "]" +
                ",\"alt\":[" + newAlt + "]" +
                ",\"content\":[" + newContent + "]" +
                "}");
        /**
         * 格式如下：
         *  ["test.jsp","test.jsp","test.jsp"]
         */
        item.setUrl("[" + newItemUrl + "]");
        /**
         * 格式如下：
         * {
         "type":1,
         "url":["images/nine/17.jpg","images/nine/18.jpg","images/nine/9.jpg"],
         "size":3,
         "btntitle":["按钮1","按钮2","按钮3","按钮4","按钮5"],
         "title":"大标题"
         }
         */
        item.setImg("{" +
                "\"type\":" + type + "" +
                ",\"url\":[" + newImgUrl + "]" +
                ",\"size\":" + size + "" +
                ",\"btntitle\":[" + newBtnTitle + "]" +//当内容展示类型为4才需要配置
                ",\"title\":\"" + bigTitle + "\"" +//当内容展示类型为4才需要配置
                "}");
        item.setDis("1".equals(dis));
        //其中id和type不变
        ysjScrollParamUtil.getInstance().updateParam(item);

        //配置缓存刷新
        ysjScrollParamUtil.refresh();

        message = "首页八框内容管理成功！";
        return SUCCESS;
    }

    /**
     * 根据索引得到imgUrl
     * @param index
     * @return
     */
    private File getImgUrlByIndex(int index){
        if(1 == index){
            return imgUrl1;
        }
        if(2 == index){
            return imgUrl2;
        }
        if(3 == index){
            return imgUrl3;
        }
        if(4 == index){
            return imgUrl4;
        }
        if(5 == index){
            return imgUrl5;
        }
        if(6 == index){
            return imgUrl6;
        }
        if(7 == index){
            return imgUrl7;
        }
        if(8 == index){
            return imgUrl8;
        }
        if(9 == index){
            return imgUrl9;
        }
        if(10 == index){
            return imgUrl10;
        }
        if(11 == index){
            return imgUrl11;
        }
        if(12 == index){
            return imgUrl12;
        }
        if(13 == index){
            return imgUrl13;
        }
        if(14 == index){
            return imgUrl14;
        }
        if(15 == index){
            return imgUrl15;
        }
        if(16 == index){
            return imgUrl16;
        }
        if(17 == index){
            return imgUrl17;
        }
        if(18 == index){
            return imgUrl18;
        }
        if(19 == index){
            return imgUrl19;
        }
        if(20 == index){
            return imgUrl20;
        }if(21 == index){
            return imgUrl21;
        }
        if(22 == index){
            return imgUrl22;
        }
        if(23 == index){
            return imgUrl23;
        }
        if(24 == index){
            return imgUrl24;
        }
        if(25 == index){
            return imgUrl25;
        }
        if(26 == index){
            return imgUrl26;
        }
        if(27 == index){
            return imgUrl27;
        }
        if(28 == index){
            return imgUrl28;
        }
        if(29 == index){
            return imgUrl29;
        }
        if(30 == index){
            return imgUrl30;
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
        List<URLTitleName> itemList =ysjScrollParamUtil.getInstance().getValueByID(HomeParamInterface.ITEM_LIST);
        for(URLTitleName temp : itemList){
            if(StringUtils.equals(StringUtils.EMPTY + temp.getID(), id)){
                return temp;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getBigTitle() {
        return bigTitle;
    }

    public void setBigTitle(String bigTitle) {
        this.bigTitle = bigTitle;
    }

    public File getImgUrl1() {
        return imgUrl1;
    }

    public void setImgUrl1(File imgUrl1) {
        this.imgUrl1 = imgUrl1;
    }

    public File getImgUrl2() {
        return imgUrl2;
    }

    public void setImgUrl2(File imgUrl2) {
        this.imgUrl2 = imgUrl2;
    }

    public File getImgUrl3() {
        return imgUrl3;
    }

    public void setImgUrl3(File imgUrl3) {
        this.imgUrl3 = imgUrl3;
    }

    public File getImgUrl4() {
        return imgUrl4;
    }

    public void setImgUrl4(File imgUrl4) {
        this.imgUrl4 = imgUrl4;
    }

    public File getImgUrl5() {
        return imgUrl5;
    }

    public void setImgUrl5(File imgUrl5) {
        this.imgUrl5 = imgUrl5;
    }

    public File getImgUrl6() {
        return imgUrl6;
    }

    public void setImgUrl6(File imgUrl6) {
        this.imgUrl6 = imgUrl6;
    }

    public File getImgUrl7() {
        return imgUrl7;
    }

    public void setImgUrl7(File imgUrl7) {
        this.imgUrl7 = imgUrl7;
    }

    public File getImgUrl8() {
        return imgUrl8;
    }

    public void setImgUrl8(File imgUrl8) {
        this.imgUrl8 = imgUrl8;
    }

    public File getImgUrl9() {
        return imgUrl9;
    }

    public void setImgUrl9(File imgUrl9) {
        this.imgUrl9 = imgUrl9;
    }

    public File getImgUrl10() {
        return imgUrl10;
    }

    public void setImgUrl10(File imgUrl10) {
        this.imgUrl10 = imgUrl10;
    }

    public File getImgUrl11() {
        return imgUrl11;
    }

    public void setImgUrl11(File imgUrl11) {
        this.imgUrl11 = imgUrl11;
    }

    public File getImgUrl12() {
        return imgUrl12;
    }

    public void setImgUrl12(File imgUrl12) {
        this.imgUrl12 = imgUrl12;
    }

    public File getImgUrl13() {
        return imgUrl13;
    }

    public void setImgUrl13(File imgUrl13) {
        this.imgUrl13 = imgUrl13;
    }

    public File getImgUrl14() {
        return imgUrl14;
    }

    public void setImgUrl14(File imgUrl14) {
        this.imgUrl14 = imgUrl14;
    }

    public File getImgUrl15() {
        return imgUrl15;
    }

    public void setImgUrl15(File imgUrl15) {
        this.imgUrl15 = imgUrl15;
    }

    public File getImgUrl16() {
        return imgUrl16;
    }

    public void setImgUrl16(File imgUrl16) {
        this.imgUrl16 = imgUrl16;
    }

    public File getImgUrl17() {
        return imgUrl17;
    }

    public void setImgUrl17(File imgUrl17) {
        this.imgUrl17 = imgUrl17;
    }

    public File getImgUrl18() {
        return imgUrl18;
    }

    public void setImgUrl18(File imgUrl18) {
        this.imgUrl18 = imgUrl18;
    }

    public File getImgUrl19() {
        return imgUrl19;
    }

    public void setImgUrl19(File imgUrl19) {
        this.imgUrl19 = imgUrl19;
    }

    public File getImgUrl20() {
        return imgUrl20;
    }

    public void setImgUrl20(File imgUrl20) {
        this.imgUrl20 = imgUrl20;
    }

    public File getImgUrl21() {
        return imgUrl21;
    }

    public void setImgUrl21(File imgUrl21) {
        this.imgUrl21 = imgUrl21;
    }

    public File getImgUrl22() {
        return imgUrl22;
    }

    public void setImgUrl22(File imgUrl22) {
        this.imgUrl22 = imgUrl22;
    }

    public File getImgUrl23() {
        return imgUrl23;
    }

    public void setImgUrl23(File imgUrl23) {
        this.imgUrl23 = imgUrl23;
    }

    public File getImgUrl24() {
        return imgUrl24;
    }

    public void setImgUrl24(File imgUrl24) {
        this.imgUrl24 = imgUrl24;
    }

    public File getImgUrl25() {
        return imgUrl25;
    }

    public void setImgUrl25(File imgUrl25) {
        this.imgUrl25 = imgUrl25;
    }

    public File getImgUrl26() {
        return imgUrl26;
    }

    public void setImgUrl26(File imgUrl26) {
        this.imgUrl26 = imgUrl26;
    }

    public File getImgUrl27() {
        return imgUrl27;
    }

    public void setImgUrl27(File imgUrl27) {
        this.imgUrl27 = imgUrl27;
    }

    public File getImgUrl28() {
        return imgUrl28;
    }

    public void setImgUrl28(File imgUrl28) {
        this.imgUrl28 = imgUrl28;
    }

    public File getImgUrl29() {
        return imgUrl29;
    }

    public void setImgUrl29(File imgUrl29) {
        this.imgUrl29 = imgUrl29;
    }

    public File getImgUrl30() {
        return imgUrl30;
    }

    public void setImgUrl30(File imgUrl30) {
        this.imgUrl30 = imgUrl30;
    }
}