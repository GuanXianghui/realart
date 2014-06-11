package com.realart;

import com.realart.entities.URLTitleName;
import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ysjScrollParamUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 删除 首页八框内容
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteItemListAction extends BaseAction implements UserInterface{
    private String id;
    private String index;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("id:[" + id + "],index:[" + index + "]");
        //根据id得到URLTitleName
        URLTitleName item = getURLTitleNameById(id);

        //新数据
        String newContent = StringUtils.EMPTY;
        String newTitle = StringUtils.EMPTY;
        String newAlt = StringUtils.EMPTY;
        String newImgUrl = StringUtils.EMPTY;
        String newItemUrl = StringUtils.EMPTY;
        String newBtnTitle = StringUtils.EMPTY;

        //老数据
        JSONObject itemImg = JSONObject.fromObject(item.getImg());//内容展示类型+图片地址+图片个数
        int oldType = itemImg.getInt("type");//内容展示类型
        JSONArray imgUrlJsonArray = JSONArray.fromObject(itemImg.get("url"));//图片地址 用户取出老图片
        int oldSize = itemImg.getInt("size");//图片个数
        String oldBigTitle = itemImg.getString("title");//大标题
        JSONArray btnTitleJsonArray = JSONArray.fromObject(itemImg.get("btntitle"));//按钮文字 用户取出老按钮文字

        JSONArray itemUrlJsonArray = JSONArray.fromObject(item.getUrl());//链接地址

        JSONObject itemName = JSONObject.fromObject(item.getName());//标题+提示+内容
        JSONArray titleJsonArray = JSONArray.fromObject(itemName.get("title"));//标题
        JSONArray altJsonArray = JSONArray.fromObject(itemName.get("alt"));//提示
        JSONArray contentJsonArray = JSONArray.fromObject(itemName.get("content"));//内容

        for(int i=0;i<oldSize;i++){
            if(StringUtils.equals(StringUtils.EMPTY + (i+1), index)){
                continue;
            }
            if(StringUtils.isNotBlank(newContent)){
                newContent += SymbolInterface.SYMBOL_COMMA;
                newTitle += SymbolInterface.SYMBOL_COMMA;
                newAlt += SymbolInterface.SYMBOL_COMMA;
                newImgUrl += SymbolInterface.SYMBOL_COMMA;
                newItemUrl += SymbolInterface.SYMBOL_COMMA;
                newBtnTitle += SymbolInterface.SYMBOL_COMMA;
            }

            String content = contentJsonArray.getString(i);
            String title = titleJsonArray.getString(i);
            String alt = altJsonArray.getString(i);
            String itemUrl = itemUrlJsonArray.getString(i);
            String imgUrl = imgUrlJsonArray.getString(i);
            String btnTitle = btnTitleJsonArray.getString(i);

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
         "size":3
         }
         */
        item.setImg("{" +
                "\"type\":" + oldType + "" +
                ",\"url\":[" + newImgUrl + "]" +
                ",\"size\":" + (oldSize-1) + "" +
                ",\"title\":\"" + oldBigTitle + "\"" +
                ",\"btntitle\":[" + newBtnTitle + "]" +
                "}");
        //其中id和type和dis不变
        ysjScrollParamUtil.getInstance().updateParam(item);

        //配置缓存刷新
        ysjScrollParamUtil.refresh();
        message = "删除首页八框内容成功！";
        return SUCCESS;
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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
