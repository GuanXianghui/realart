package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * 删除 首页联系方法
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteContactGuideAction extends BaseAction implements UserInterface{
    private String index;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("index:[" + index + "]");
        //首页联系方法管理
        String contactGuide= ParamUtil.getInstance().getValueByName(HomeParamInterface.CONTACT_GUIDE);
        //新的 首页联系方法
        String newContactGuide = StringUtils.EMPTY;
        //导航json串转换成数组
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
        //首页联系方法管理
        ParamUtil.getInstance().updateParam(HomeParamInterface.CONTACT_GUIDE, newContactGuide, "联系方法");

        //刷新缓存
        ParamUtil.refresh();

        message = "删除首页联系方法成功！";
        return SUCCESS;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
