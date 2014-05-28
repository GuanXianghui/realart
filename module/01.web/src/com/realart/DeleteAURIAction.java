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
public class DeleteAURIAction extends BaseAction implements UserInterface{
    private String auri;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("auri:[" + auri + "]");
        //�������û�ע����
        String artistUserRegistItems = ParamUtil.getInstance().getValueByName(ParamInterface.ARTIST_USER_REGIST_ITEMS);
        //�µ� �������û�ע����
        String newArtistUserRegistItems = StringUtils.EMPTY;
        //����json��ת��������
        JSONArray json = JSONArray.fromObject(artistUserRegistItems);
        for(int i=0;i<json.size();i++)
        {
            JSONObject temp = json.getJSONObject(i);
            String name = (StringUtils.trimToEmpty((String) temp.get("name")));
            if(StringUtils.equals(name, auri)){
                continue;
            }
            if(StringUtils.isNotBlank(newArtistUserRegistItems)){
                newArtistUserRegistItems += SymbolInterface.SYMBOL_COMMA;
            }
            String need = (StringUtils.trimToEmpty((String) temp.get("need")));
            String type = (StringUtils.trimToEmpty((String) temp.get("type")));
            newArtistUserRegistItems += "{\"name\":\"" + name + "\",\"need\":\"" + need + "\",\"type\":\"" + type + "\"}";
        }
        newArtistUserRegistItems = "[" + newArtistUserRegistItems + "]";
        logger.info("newArtistUserRegistItems=>" + newArtistUserRegistItems);
        ParamUtil.getInstance().updateParam(ParamInterface.ARTIST_USER_REGIST_ITEMS, newArtistUserRegistItems, "�������û�ע����");

        //ˢ�»���
        ParamUtil.refresh();

        message = "ɾ��ע����ɹ���";
        return SUCCESS;
    }

    public String getAuri() {
        return auri;
    }

    public void setAuri(String auri) {
        this.auri = auri;
    }
}
