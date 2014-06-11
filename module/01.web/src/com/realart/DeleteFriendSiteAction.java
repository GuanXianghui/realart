package com.realart;

import com.realart.dao.HomeParamDao;
import com.realart.entities.URLTitleName;
import com.realart.interfaces.HomeParamInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ysjScrollParamUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * ɾ�� ��ҳ��������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteFriendSiteAction extends BaseAction implements UserInterface{
    private String id;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("id:[" + id + "]");
        URLTitleName urlTitleName = getURLTitleNameById(id);
        if(null != urlTitleName){
            HomeParamDao.deleteysjParam(urlTitleName.getID(), urlTitleName.getType());
        }
        //���û���ˢ��
        ysjScrollParamUtil.refresh();
        message = "ɾ����ҳ�������ӳɹ���";
        return SUCCESS;
    }

    /**
     * ����id�õ�URLTitleName
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
