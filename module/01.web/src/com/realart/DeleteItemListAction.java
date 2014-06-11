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
 * ɾ�� ��ҳ�˿�����
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteItemListAction extends BaseAction implements UserInterface{
    private String id;
    private String index;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("id:[" + id + "],index:[" + index + "]");
        //����id�õ�URLTitleName
        URLTitleName item = getURLTitleNameById(id);

        //������
        String newContent = StringUtils.EMPTY;
        String newTitle = StringUtils.EMPTY;
        String newAlt = StringUtils.EMPTY;
        String newImgUrl = StringUtils.EMPTY;
        String newItemUrl = StringUtils.EMPTY;
        String newBtnTitle = StringUtils.EMPTY;

        //������
        JSONObject itemImg = JSONObject.fromObject(item.getImg());//����չʾ����+ͼƬ��ַ+ͼƬ����
        int oldType = itemImg.getInt("type");//����չʾ����
        JSONArray imgUrlJsonArray = JSONArray.fromObject(itemImg.get("url"));//ͼƬ��ַ �û�ȡ����ͼƬ
        int oldSize = itemImg.getInt("size");//ͼƬ����
        String oldBigTitle = itemImg.getString("title");//�����
        JSONArray btnTitleJsonArray = JSONArray.fromObject(itemImg.get("btntitle"));//��ť���� �û�ȡ���ϰ�ť����

        JSONArray itemUrlJsonArray = JSONArray.fromObject(item.getUrl());//���ӵ�ַ

        JSONObject itemName = JSONObject.fromObject(item.getName());//����+��ʾ+����
        JSONArray titleJsonArray = JSONArray.fromObject(itemName.get("title"));//����
        JSONArray altJsonArray = JSONArray.fromObject(itemName.get("alt"));//��ʾ
        JSONArray contentJsonArray = JSONArray.fromObject(itemName.get("content"));//����

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
         * ��ʽ���£�
         * {
         "title":["����1","����2","����3"],
         "alt":["��ʾ1","��ʾ2","��ʾ3"],
         "content":["����1","����2","����3"]
         }
         */
        item.setName("{" +
                "\"title\":[" + newTitle + "]" +
                ",\"alt\":[" + newAlt + "]" +
                ",\"content\":[" + newContent + "]" +
                "}");
        /**
         * ��ʽ���£�
         *  ["test.jsp","test.jsp","test.jsp"]
         */
        item.setUrl("[" + newItemUrl + "]");
        /**
         * ��ʽ���£�
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
        //����id��type��dis����
        ysjScrollParamUtil.getInstance().updateParam(item);

        //���û���ˢ��
        ysjScrollParamUtil.refresh();
        message = "ɾ����ҳ�˿����ݳɹ���";
        return SUCCESS;
    }

    /**
     * ����id�õ�URLTitleName
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
