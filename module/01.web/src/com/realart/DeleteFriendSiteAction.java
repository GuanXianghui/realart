package com.realart;

import com.realart.dao.HomeParamDao;
import com.realart.entities.URLTitleName;
import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ysjScrollParamUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 删除 首页友情链接
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteFriendSiteAction extends BaseAction implements UserInterface{
    private String id;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("id:[" + id + "]");
        URLTitleName urlTitleName = getURLTitleNameById(id);
        if(null != urlTitleName){
            HomeParamDao.deleteysjParam(urlTitleName.getID(), urlTitleName.getType());
        }
        //配置缓存刷新
        ysjScrollParamUtil.refresh();
        message = "删除首页友情链接成功！";
        return SUCCESS;
    }

    /**
     * 根据id得到URLTitleName
     * @param id
     * @return
     * @throws Exception
     */
    private URLTitleName getURLTitleNameById(String id) throws Exception {
        List<URLTitleName> friendSiteList = ysjScrollParamUtil.getInstance().getValueByID(HomeParamInterface.HOME_FRIENDSITE_LIST);
        for(URLTitleName temp : friendSiteList){
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
}
