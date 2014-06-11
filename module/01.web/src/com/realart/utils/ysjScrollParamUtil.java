package com.realart.utils;

import com.realart.dao.HomeParamDao;
import com.realart.entities.URLTitleName;

import java.util.ArrayList;
import java.util.List;

/**
 * 启动参数工具类
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
     * 配置缓存刷新
     */
    public static void refresh() throws Exception {
        // 1 查询所有启动参数
        params = HomeParamDao.queryAllysjParams(0);
        params1 = HomeParamDao.queryAllysjParams(1);
        params2 = HomeParamDao.queryAllysjParams(2);
        params3 = HomeParamDao.queryAllysjParams(3);
        params4 = HomeParamDao.queryAllysjParams(4);
        params5 = HomeParamDao.queryAllysjParams(5);
    }

    /**
     * 获取值
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
     * 根据name修改value和info
     * @param u
     */
    public void updateParam(URLTitleName u) throws Exception {
        //更新启动参数
        HomeParamDao.updateysjParam(u);

        //配置缓存刷新
        refresh();
    }
}