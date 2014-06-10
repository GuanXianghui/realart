package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * ɾ�� ��ҳ��ϵ����
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteContactGuideAction extends BaseAction implements UserInterface{
    private String index;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("index:[" + index + "]");
        //��ҳ��ϵ��������
        String contactGuide= ParamUtil.getInstance().getValueByName(HomeParamInterface.CONTACT_GUIDE);
        //�µ� ��ҳ��ϵ����
        String newContactGuide = StringUtils.EMPTY;
        //����json��ת��������
        JSONArray json = JSONArray.fromObject(contactGuide);
        for(int i=0;i<json.size();i++)
        {
            if(i + 1 == Integer.parseInt(index)){
                continue;
            }
            if(StringUtils.isNotBlank(newContactGuide)){
                newContactGuide += SymbolInterface.SYMBOL_COMMA;
            }
            JSONObject temp = json.getJSONObject(i);
            String name = (StringUtils.trimToEmpty((String) temp.get("name")));
            String text = (StringUtils.trimToEmpty((String) temp.get("text")));
            String ico = (StringUtils.trimToEmpty((String) temp.get("ico")));
            newContactGuide += "{\"name\":\"" + name + "\",\"text\":\"" + text +  "\",\"ico\":\"" + ico + "\"}";
        }
        newContactGuide = "[" + newContactGuide + "]";
        logger.info("newContactGuide=>" + newContactGuide);
        //��ҳ��ϵ��������
        ParamUtil.getInstance().updateParam(HomeParamInterface.CONTACT_GUIDE, newContactGuide, "��ϵ����");

        //ˢ�»���
        ParamUtil.refresh();

        message = "ɾ����ҳ��ϵ�����ɹ���";
        return SUCCESS;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
