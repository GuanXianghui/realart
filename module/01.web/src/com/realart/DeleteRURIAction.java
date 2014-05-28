package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * 删除注册项
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteRURIAction extends BaseAction implements UserInterface{
    private String ruri;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("ruri:[" + ruri + "]");
        //评论用户注册项
        String reviewUserRegistItems = ParamUtil.getInstance().getValueByName(ParamInterface.REVIEW_USER_REGIST_ITEMS);
        //新的 评论用户注册项
        String newReviewUserRegistItems = StringUtils.EMPTY;
        //导航json串转换成数组
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
        ParamUtil.getInstance().updateParam(ParamInterface.REVIEW_USER_REGIST_ITEMS, newReviewUserRegistItems, "评论用户注册项");

        //刷新缓存
        ParamUtil.refresh();

        message = "删除注册项成功！";
        return SUCCESS;
    }

    public String getRuri() {
        return ruri;
    }

    public void setRuri(String ruri) {
        this.ruri = ruri;
    }
}
