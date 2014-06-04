package com.realart;

import com.realart.dao.UserDao;
import com.realart.entities.User;
import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.BaseUtil;
import com.realart.utils.FileUtil;
import com.realart.utils.ParamUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * ע�����ۻ�Ա
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class RegistReviewAction extends BaseAction implements UserInterface{
    private String name;
    private String certName;
    private File titlePhoto;
    private File headPhoto;
    private String email;
    private String password;
    private String confirmPassword;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],certName:[" + certName + "],titlePhoto:[" + titlePhoto + "]," +
                "headPhoto:[" + headPhoto + "],email:[" + email + "],password:[" + password + "]," +
                "confirmPassword:[" + confirmPassword + "]");
        //���ֶ�Ϊ��
        if(StringUtils.isBlank(name) || StringUtils.isBlank(certName) || StringUtils.isBlank(email) ||
                StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)){
            message = "�����������";
            return ERROR;
        }
        //�������Ƿ�һ��
        if(!StringUtils.equals(password, confirmPassword)){
            message = "������������";
            return ERROR;
        }
        //��ͼƬΪ��
        if(null == titlePhoto || null == headPhoto)
        {
            message = "���ϴ������ͼƬ!";
            return ERROR;
        }

        //�����û�ע����
        String reviewUserRegistItems = ParamUtil.getInstance().getValueByName(ParamInterface.REVIEW_USER_REGIST_ITEMS);
        //�����û�ע���� json��ת��������
        JSONArray json = JSONArray.fromObject(reviewUserRegistItems);
        for(int i=0;i<json.size();i++)
        {
            JSONObject temp = json.getJSONObject(i);
            String name = (StringUtils.trimToEmpty((String) temp.get("name")));
            String need = (StringUtils.trimToEmpty((String) temp.get("need")));
            String type = (StringUtils.trimToEmpty((String) temp.get("type")));
            //�ǿ�У��
            if(Integer.parseInt(need) == COLUMN_NEED_YES){//���ַ���
                if(Integer.parseInt(type) == COLUMN_TYPE_STRING){
                    //�������ֻ�ȡ�ַ���
                    String value = request.getParameter("item" + (i+1));
                    if(StringUtils.isBlank(value)){
                        message = "������" + name;
                        return ERROR;
                    }
                } else if(Integer.parseInt(type) == COLUMN_TYPE_FILE){//���ļ�
                    //�������ֻ�ȡ�ļ�
                    MultiPartRequestWrapper mpRequest = (MultiPartRequestWrapper)ServletActionContext.getRequest();
                    File[] files = mpRequest.getFiles("item" + (i+1));
                    if(files == null || files.length == 0 || files[0] == null){
                        message = "���ϴ�" + name;
                        return ERROR;
                    }
                }
            }
        }

        //�и����ֺ��û������Ƿ��Ѵ���
        boolean isExist = UserDao.isNameExist(name);
        if(isExist || BaseUtil.isAdminName(name)){
            message = "���û����ѱ��ã�������û�����";
            return ERROR;
        }

        //�����û�
        User user = new User(name, USER_TYPE_REVIEW, password, certName, StringUtils.EMPTY, StringUtils.EMPTY,
                email, StringUtils.EMPTY, USER_STATE_NORMAL, StringUtils.EMPTY, date, time, getIp());
        UserDao.insertUser(user);

        //�����������û����Ͳ��û�
        user = UserDao.getUserByName(name);
        //����ѹ��ͼ
        String titlePhotoRoute = createTitlePhoto(user);
        //��������ͼƬ
        String headPhotoRoute = createHeadPhoto(user);
        //�õ��û���Ϣ
        String info = getInfoFromRequestAndRURI(user, request, reviewUserRegistItems);
        //�����û���Ϣ
        user.setTitlePhoto(titlePhotoRoute);
        user.setHeadPhoto(headPhotoRoute);
        user.setInfo(info);
        UserDao.updateUserInfo(user);
        //ˢ��session�����е�user
        refreshSessionUser(user);
        //���session�����еĹ���Ա�û�
        clearSessionAdminUser();

        message = "�����û��ɹ���";
        return SUCCESS;
    }

    /**
     * ����ѹ��ͼ
     * @param user
     * @return
     */
    private String createTitlePhoto(User user){
        //�µ�ͼƬ����
        String headPhotoName = user.getId() + "_" + new Date().getTime() + ".jpg";
        //�������ϵ�·��
        String headPhotoPath = ServletActionContext.getServletContext().getRealPath("images/user/title") + "/" + headPhotoName;
        //ҳ������λ�� ���·��
        String headPhotoPagePath = "images/user/title/" + headPhotoName;
        //�������ϵ�·����Ӧ���ļ�
        File imageFile = new File(headPhotoPath);
        //�����ļ�
        FileUtil.copy(titlePhoto, imageFile);
        return headPhotoPagePath;
    }

    /**
     * ��������ͼƬ
     * @param user
     * @return
     */
    private String createHeadPhoto(User user){
        //�µ�ͼƬ����
        String headPhotoName = user.getId() + "_" + new Date().getTime() + ".jpg";
        //�������ϵ�·��
        String headPhotoPath = ServletActionContext.getServletContext().getRealPath("images/user/head") + "/" + headPhotoName;
        //ҳ������λ�� ���·��
        String headPhotoPagePath = "images/user/head/" + headPhotoName;
        //�������ϵ�·����Ӧ���ļ�
        File imageFile = new File(headPhotoPath);
        //�����ļ�
        FileUtil.copy(headPhoto, imageFile);
        return headPhotoPagePath;
    }

    /**
     * �õ��û���Ϣ json��
     * @param user
     * @param request
     * @param reviewUserRegistItems
     * @return
     */
    private String getInfoFromRequestAndRURI(User user, HttpServletRequest request, String reviewUserRegistItems) {
        String info = StringUtils.EMPTY;
        //�����û�ע���� json��ת��������
        JSONArray json = JSONArray.fromObject(reviewUserRegistItems);
        for(int i=0;i<json.size();i++)
        {
            if(StringUtils.isNotBlank(info)){
                info += SymbolInterface.SYMBOL_COMMA;
            }
            JSONObject temp = json.getJSONObject(i);
            String name = (StringUtils.trimToEmpty((String) temp.get("name")));
            String need = (StringUtils.trimToEmpty((String) temp.get("need")));
            String type = (StringUtils.trimToEmpty((String) temp.get("type")));
            if(Integer.parseInt(type) == COLUMN_TYPE_STRING){
                //�������ֻ�ȡ�ַ���
                String value = request.getParameter("item" + (i+1));
                info += "\"" + name + "\":\"" + StringUtils.trimToEmpty(value) + "\"";
            } else if(Integer.parseInt(type) == COLUMN_TYPE_FILE){//���ļ�
                //�������ֻ�ȡ�ļ�
                MultiPartRequestWrapper mpRequest = (MultiPartRequestWrapper)ServletActionContext.getRequest();
                File[] files = mpRequest.getFiles("item" + (i+1));
                if(files == null || files.length == 0 || files[0] == null){
                    info += "\"" + name + "\":\"" + StringUtils.EMPTY + "\"";
                } else {
                    String photoPagePath = createUserPhoto(user, (i+1), files[0]);
                    info += "\"" + name + "\":\"" + photoPagePath + "\"";
                }
            }
        }
        info = "{" + info + "}";
        return info;
    }

    /**
     * ����ͼƬ
     * @param index
     * @param user
     * @return
     */
    private String createUserPhoto(User user, int index, File photo){
        //�µ�ͼƬ����
        String photoName = user.getId() + "_" + index + "_" + new Date().getTime() + ".jpg";
        //�������ϵ�·��
        String photoPath = ServletActionContext.getServletContext().getRealPath("images/user") + "/" + photoName;
        //ҳ������λ�� ���·��
        String photoPagePath = "images/user/" + photoName;
        //�������ϵ�·����Ӧ���ļ�
        File imageFile = new File(photoPath);
        //�����ļ�
        FileUtil.copy(photo, imageFile);
        return photoPagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public File getTitlePhoto() {
        return titlePhoto;
    }

    public void setTitlePhoto(File titlePhoto) {
        this.titlePhoto = titlePhoto;
    }

    public File getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(File headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
