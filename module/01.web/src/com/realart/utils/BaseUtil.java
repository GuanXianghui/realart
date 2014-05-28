package com.realart.utils;

import com.realart.entities.Art;
import com.realart.entities.User;
import com.realart.interfaces.BaseInterface;
import com.realart.interfaces.SymbolInterface;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 基础工具类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-30 12:09
 */
public class BaseUtil implements BaseInterface, SymbolInterface {
    /**
     * 判登录
     *
     * @param request
     */
    public static boolean isLogin(HttpServletRequest request) throws Exception {
        if(request.getSession().getAttribute(BaseInterface.USER_KEY) == null) {
            return false;
        }
        return true;
    }

    /**
     * 判管理员登录
     *
     * @param request
     */
    public static boolean isAdminLogin(HttpServletRequest request) throws Exception {
        if(request.getSession().getAttribute(BaseInterface.IS_ADMIN_USER) != Boolean.TRUE) {
            return false;
        }
        return true;
    }

    /**
     * 用户集合json串
     * @param users
     * @return
     */
    public static String getJsonArrayFromUsers(List<User> users) {
        String result = StringUtils.EMPTY;
        for(User user : users) {
            if(StringUtils.isNotBlank(result)) {
                result += SYMBOL_BIT_AND;
            }
            result += "{id:" + user.getId() + ",name:'" + user.getName() + "',userType:" +
                    user.getUserType() + ",certName:'" + user.getCertName() + "',titlePhoto:'" +
                    user.getTitlePhoto() + "',headPhoto:'" + user.getHeadPhoto() +"',info:'" +
                    user.getInfo().replaceAll("\\\'", "\\\\\\\'").replaceAll("\\\"", "\\\\\\\"") +
                    "',state:" + user.getState() + ",stateDesc:'" + user.getStateDesc() +
                    "',reason:'" + user.getReason().replaceAll("\\\'", "\\\\\\\'").replaceAll("\\\"", "\\\\\\\"") +
                    "',registerDate:'" + user.getRegisterDate() + "',registerTime:'" + user.getRegisterTime() +
                    "',registerIp:'" + user.getRegisterIp() + "'}";
        }
        return result;
    }

    /**
     * 艺术品集合json串
     * @param arts
     * @return
     */
    public static String getJsonArrayFromArts(List<Art> arts) {
        String result = StringUtils.EMPTY;
        for(Art art : arts) {
            if(StringUtils.isNotBlank(result)) {
                result += SYMBOL_BIT_AND;
            }
            result += "{id:" + art.getId() + ",userId:" + art.getUserId() + ",name:'" + art.getName() +
                    "',state:" + art.getState() + ",photo:'" + art.getPhoto() + "',photo0:'" + art.getPhoto0() +
                    "',photo1:'" + art.getPhoto1() + "',photo2:'" + art.getPhoto2() + "',photo3:'" +
                    art.getPhoto3() +"',photo4:'" + art.getPhoto4() +"',gongyi:'" + art.getGongyi() + "',type:'" +
                    art.getType() + "',length:" + art.getLength() + ",width:" + art.getWidth() + ",height:" +
                    art.getHeight() + ",buildDate:'" + art.getBuildDate() + "',title:'" + art.getTitle() +
                    "',introduction:'" + art.getIntroduction() + "',locationBit:'" + art.getLocationBit() +
                    "',createDate:'" + art.getCreateDate() + "',createTime:'" + art.getCreateTime() +
                    "',createIp:'" + art.getCreateIp() + "',stateDesc:'" + art.getStateDesc() + "'}";
        }
        return result;
    }

    /**
     * 校验管理员用户
     * @param name
     * @param password
     * @throws Exception
     */
    public static void checkAdminUser(String name, String password) throws Exception {
        String[] users = PropertyUtil.getInstance().getProperty(ADMIN_USER).split(SYMBOL_COMMA);
        String[] passwords = PropertyUtil.getInstance().getProperty(ADMIN_PASSWORD).split(SYMBOL_COMMA);
        for(int i=0;i<users.length;i++){
            String user = users[i];
            if(StringUtils.equals(name, user)){
                if(StringUtils.equals(password, passwords[i])){
                    return;
                }else{
                    throw new RuntimeException("你的密码输入错误！");
                }
            }
        }
        throw new RuntimeException("不存在该管理员用户[" + name + "]！");
    }
}
