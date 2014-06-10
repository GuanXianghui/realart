package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * ɾ�� ��ҳ����չƷ
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteRealArtRoomAction extends BaseAction implements UserInterface{
    private String index;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("index:[" + index + "]");
        //��ҳ����չ��
        String slider01= ParamUtil.getInstance().getValueByName(HomeParamInterface.SLIDER_01);
        //�µ� ��ҳ��ϵ����
        String newSlider01 = StringUtils.EMPTY;
        //����json��ת��������
        JSONArray json = JSONArray.fromObject(slider01);
        for(int i=0;i<json.size();i++)
        {
            if(i + 1 == Integer.parseInt(index)){
                continue;
            }
            if(StringUtils.isNotBlank(newSlider01)){
                newSlider01 += SymbolInterface.SYMBOL_COMMA;
            }
            JSONObject temp = json.getJSONObject(i);
            String title = (StringUtils.trimToEmpty((String) temp.get("title")));
            String url = (StringUtils.trimToEmpty((String) temp.get("url")));
            String href = (StringUtils.trimToEmpty((String) temp.get("href")));
            String type = (StringUtils.trimToEmpty((String) temp.get("type")));
            newSlider01 += "{\"title\":\"" + title + "\",\"url\":\"" + url + "\",\"href\":\"" + href + "\",\"type\":\"" + type + "\"}";
        }
        newSlider01 = "[" + newSlider01 + "]";
        logger.info("newSlider01=>" + newSlider01);
        //��ҳ��ϵ��������
        ParamUtil.getInstance().updateParam(HomeParamInterface.SLIDER_01, newSlider01, "��ҳ����չ��");

        //ˢ�»���
        ParamUtil.refresh();

        message = "ɾ����ҳ����չƷ�ɹ���";
        return SUCCESS;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
