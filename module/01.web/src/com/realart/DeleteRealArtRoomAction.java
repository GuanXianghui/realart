package com.realart;

import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * 删除 首页真艺展品
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteRealArtRoomAction extends BaseAction implements UserInterface{
    private String index;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("index:[" + index + "]");
        //首页真艺展厅
        String slider01= ParamUtil.getInstance().getValueByName(HomeParamInterface.SLIDER_01);
        //新的 首页联系方法
        String newSlider01 = StringUtils.EMPTY;
        //导航json串转换成数组
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
        //首页联系方法管理
        ParamUtil.getInstance().updateParam(HomeParamInterface.SLIDER_01, newSlider01, "首页真艺展厅");

        //刷新缓存
        ParamUtil.refresh();

        message = "删除首页真艺展品成功！";
        return SUCCESS;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
