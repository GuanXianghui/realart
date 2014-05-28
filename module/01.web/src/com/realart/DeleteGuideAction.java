package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * 删除首页导航
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteGuideAction extends BaseAction implements UserInterface{
    private String index;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("index:[" + index + "]");
        //首页 导航
        String indexGuide = ParamUtil.getInstance().getValueByName(ParamInterface.INDEX_GUIDE);
        //新的 首页 导航
        String newIndexGuide = StringUtils.EMPTY;
        //导航json串转换成数组
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
        ParamUtil.getInstance().updateParam(ParamInterface.INDEX_GUIDE, newIndexGuide, "首页 导航");

        //刷新缓存
        ParamUtil.refresh();

        message = "删除首页导航成功！";
        return SUCCESS;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
