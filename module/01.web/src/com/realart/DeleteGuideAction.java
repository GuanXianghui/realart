package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * ɾ����ҳ����
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteGuideAction extends BaseAction implements UserInterface{
    private String index;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("index:[" + index + "]");
        //��ҳ ����
        String indexGuide = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_GUIDE);
        //�µ� ��ҳ ����
        String newIndexGuide = StringUtils.EMPTY;
        //����json��ת��������
        JSONArray json = JSONArray.fromObject(indexGuide);
        for(int i=0;i<json.size();i++)
        {
            if(i + 1 == Integer.parseInt(index)){
                continue;
            }
            if(StringUtils.isNotBlank(newIndexGuide)){
                newIndexGuide += SymbolInterface.SYMBOL_COMMA;
            }
            JSONObject temp = json.getJSONObject(i);
            String name = (StringUtils.trimToEmpty((String) temp.get("name")));
            String url = (StringUtils.trimToEmpty((String) temp.get("url")));
            newIndexGuide += "{\"name\":\"" + name + "\",\"url\":\"" + url + "\"}";
        }
        newIndexGuide = "[" + newIndexGuide + "]";
        logger.info("newIndexGuide=>" + newIndexGuide);
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_GUIDE, newIndexGuide, "��ҳ ����");

        //ˢ�»���
        ParamUtil.refresh();

        message = "ɾ����ҳ�����ɹ���";
        return SUCCESS;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
