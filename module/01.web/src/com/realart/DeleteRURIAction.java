package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * ɾ��ע����
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteRURIAction extends BaseAction implements UserInterface{
    private String ruri;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("ruri:[" + ruri + "]");
        //�����û�ע����
        String reviewUserRegistItems = ParamUtil.getInstance().getValueByName(ParamInterface.REVIEW_USER_REGIST_ITEMS);
        //�µ� �����û�ע����
        String newReviewUserRegistItems = StringUtils.EMPTY;
        //����json��ת��������
        JSONArray json = JSONArray.fromObject(reviewUserRegistItems);
        for(int i=0;i<json.size();i++)
        {
            JSONObject temp = json.getJSONObject(i);
            String name = (StringUtils.trimToEmpty((String) temp.get("name")));
            if(StringUtils.equals(name, ruri)){
                continue;
            }
            if(StringUtils.isNotBlank(newReviewUserRegistItems)){
                newReviewUserRegistItems += SymbolInterface.SYMBOL_COMMA;
            }
            String need = (StringUtils.trimToEmpty((String) temp.get("need")));
            String type = (StringUtils.trimToEmpty((String) temp.get("type")));
            newReviewUserRegistItems += "{\"name\":\"" + name + "\",\"need\":\"" + need + "\",\"type\":\"" + type + "\"}";
        }
        newReviewUserRegistItems = "[" + newReviewUserRegistItems + "]";
        logger.info("newReviewUserRegistItems=>" + newReviewUserRegistItems);
        ParamUtil.getInstance().updateParam(ParamInterface.REVIEW_USER_REGIST_ITEMS, newReviewUserRegistItems, "�����û�ע����");

        //ˢ�»���
        ParamUtil.refresh();

        message = "ɾ��ע����ɹ���";
        return SUCCESS;
    }

    public String getRuri() {
        return ruri;
    }

    public void setRuri(String ruri) {
        this.ruri = ruri;
    }
}
