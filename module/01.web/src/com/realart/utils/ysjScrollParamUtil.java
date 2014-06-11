package com.realart.utils;

import com.realart.dao.HomeParamDao;
import com.realart.entities.URLTitleName;

import java.util.ArrayList;
import java.util.List;

/**
 * ��������������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-31 08:39
 */
public class ysjScrollParamUtil {
    private static ysjScrollParamUtil instance;

    public static ysjScrollParamUtil getInstance() throws Exception {
        if (null == instance) {
            instance = new ysjScrollParamUtil();
        }
        return instance;
    }

    static List<URLTitleName> params;
    static List<URLTitleName> params1;
    static List<URLTitleName> params3;
    static List<URLTitleName> params2;
    static List<URLTitleName> params4;
    static List<URLTitleName> params5;
    private ysjScrollParamUtil() throws Exception {
        refresh();
    }

    /**
     * ���û���ˢ��
     */
    public static void refresh() throws Exception {
        // 1 ��ѯ������������
        params = HomeParamDao.queryAllysjParams(0);
        params1 = HomeParamDao.queryAllysjParams(1);
        params2 = HomeParamDao.queryAllysjParams(2);
        params3 = HomeParamDao.queryAllysjParams(3);
        params4 = HomeParamDao.queryAllysjParams(4);
        params5 = HomeParamDao.queryAllysjParams(5);
    }

    /**
     * ��ȡֵ
     *
     * @param type
     * @return
     */
    public List<URLTitleName> getValueByID(int type) {
        switch(type)
        {
            case 0:
                return params;
            case 1:
                return params1;
            case 2:
                return params2;
            case 3:
                return params3;
            case 4:
                return params4;
            case 5:
                return params5;
            default: break;

        }
        return new ArrayList<URLTitleName>();
    }

    /**
     * ����name�޸�value��info
     * @param u
     */
    public void updateParam(URLTitleName u) throws Exception {
        //������������
        HomeParamDao.updateysjParam(u);

        //���û���ˢ��
        refresh();
    }
}