package com.realart.utils;

import com.realart.dao.UserTokenDao;
import com.realart.entities.Art;
import com.realart.entities.User;
import com.realart.entities.UserToken;
import com.realart.interfaces.BaseInterface;
import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserTokenInterface;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * ����������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-30 12:09
 */
public class BaseUtil implements BaseInterface, SymbolInterface {
    /**
     * ��ͼƬ��֤���Ƿ���ȷ
     *
     * @param request
     * @param securityCode
     */
    public static boolean checkSecurityCode(HttpServletRequest request, String securityCode) throws Exception {
        if(!StringUtils.equals((String)request.getSession().getAttribute(BaseInterface.SESSION_SECURITY_CODE), securityCode)) {
            return false;
        }
        return true;
    }

    /**
     * �е�¼
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
     * �й���Ա��¼
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
     * �û�����json��
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
     * ����Ʒ����json��
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
                    "',state:" + art.getState() + ",kind:'" + art.getKind() + "',photo:'" + art.getPhoto() +
                    "',photo0:'" + art.getPhoto0() +
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
     * У�����Ա�û�����
     * @param name
     * @throws Exception
     */
    public static boolean isAdminName(String name) throws Exception {
        String[] users = PropertyUtil.getInstance().getProperty(ADMIN_USER).split(SYMBOL_COMMA);
        for(int i=0;i<users.length;i++){
            String user = users[i];
            if(StringUtils.equals(name, user)){
                return true;
            }
        }
        return false;
    }

    /**
     * У�����Ա�û�
     * @param name
     * @param password
     * @throws Exception
     */
    public static void checkAdminUser(String name, String password) throws Exception {
        String user = PropertyUtil.getInstance().getProperty(ADMIN_USER);
        String userPassword = ParamUtil.getInstance().getValueByName(ParamInterface.ADMIN_PASSWORD);
        if(StringUtils.equals(name, user)){
            if(StringUtils.equals(password, userPassword)){
                return;
            }else{
                throw new RuntimeException("��������������");
            }
        }
        throw new RuntimeException("�����ڸù���Ա�û�[" + name + "]��");
    }

    /**
     * �����û�TOKEN
     * @param userId
     * @param createDate
     * @param createTime
     * @param createIp
     * @return
     */
    public static UserToken createUserToken(int userId, String createDate, String createTime, String createIp) throws Exception {
        UserToken userToken = new UserToken(UUID.randomUUID().toString(), userId, UserTokenInterface.STATE_EFFECTIVE,
                createDate, createTime, createIp, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
        UserTokenDao.insertUserToken(userToken);
        return userToken;
    }

    /**
     * ��userToken��Ч
     * @param userId
     * @param token
     * @return
     * @throws Exception
     */
    public static boolean checkUserToken(int userId, String token) throws Exception {
        UserToken userToken = UserTokenDao.getUserToken(token);
        if(null == userToken || userToken.getUserId() != userId || userToken.getState() != UserTokenInterface.STATE_EFFECTIVE){
            return false;
        }
        return true;
    }

    /**
     * ��userTokenʧЧ
     * @param userId
     * @param token
     * @return
     * @throws Exception
     */
    public static void disableUserToken(int userId, String token, String updateDate, String updateTime, String updateIp) throws Exception {
        UserToken userToken = UserTokenDao.getUserToken(token);
        if(userToken.getUserId() == userId){
            userToken.setState(UserTokenInterface.STATE_NOT_EFFECTIVE);
            userToken.setUpdateDate(updateDate);
            userToken.setUpdateTime(updateTime);
            userToken.setUpdateIp(updateIp);
            UserTokenDao.updateUserTokenState(userToken);
        }
    }

    /**
     * ����request��ȡ����
     * @param request
     * @return
     */
    public static String getRequestStr(HttpServletRequest request){
        String url = request.getScheme() + "://";//����Э�� http �� https
        url += request.getHeader("host");//���������
        url += request.getRequestURI();//������
        if(request.getQueryString()!=null){//�ж���������Ƿ�Ϊ��
            url += "?" + request.getQueryString();//����
        }
        return url;
    }

    /**
     * �ϴ�ͼƬ
     * @param file
     * @param path
     * @return
     */
    public static String uploadImg(File file, String path){
        if(file == null){
            return StringUtils.EMPTY;
        }
        //�µ�ͼƬ����
        String imgName = new Date().getTime() + ".jpg";
        //�������ϵ�·��
        String imgPath = ServletActionContext.getServletContext().getRealPath(path) + "/" + imgName;
        //ҳ������λ�� ���·��
        String imgPagePath = path + "/" + imgName;
        //�������ϵ�·����Ӧ���ļ�
        File imageFile = new File(imgPath);
        //�����ļ�
        FileUtil.copy(file, imageFile);
        return imgPagePath;
    }
}
