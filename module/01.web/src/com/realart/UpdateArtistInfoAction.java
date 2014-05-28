package com.realart;

import com.realart.dao.UserDao;
import com.realart.entities.User;
import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
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
 * �޸�����������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class UpdateArtistInfoAction extends BaseAction implements UserInterface{
    private String certName;
    private File titlePhoto;
    private File headPhoto;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("certName:[" + certName + "],titlePhoto:[" + titlePhoto + "],headPhoto:[" + headPhoto + "]");
        //���ֶ�Ϊ��
        if(StringUtils.isBlank(certName)){
            message = "�����������";
            return ERROR;
        }
        getUser().setCertName(certName);

        if(titlePhoto != null){
            //����ѹ��ͼ
            String titlePhotoRoute = createTitlePhoto(getUser());
            getUser().setTitlePhoto(titlePhotoRoute);
        }
        if(headPhoto != null){
            //��������ͼƬ
            String headPhotoRoute = createHeadPhoto(getUser());
            getUser().setHeadPhoto(headPhotoRoute);
        }

        //�������û�ע����
        String artistUserRegistItems = ParamUtil.getInstance().getValueByName(ParamInterface.ARTIST_USER_REGIST_ITEMS);
        //�����û�ע���� json��ת��������
        JSONArray json = JSONArray.fromObject(artistUserRegistItems);
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
                    //�ļ����б��� ��Ϊ�������޸ģ�֮ǰ�϶��ϴ������������û����˵�������
                }
            }
        }

        //�õ��û���Ϣ
        String info = getInfoFromRequestAndAURI(getUser(), request, artistUserRegistItems);
        getUser().setInfo(info);

        UserDao.updateUserInfo(getUser());

        if(getUser().getState() == UserInterface.USER_STATE_CHECK_FAILED){
            getUser().setState(UserInterface.USER_STATE_NEED_CHECK);
            UserDao.updateUserState(getUser());
        }

        //ˢ��session�����е�user
        refreshSessionUser(getUser());

        message = "�޸��û��ɹ���";
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
     * @param artistUserRegistItems
     * @return
     */
    private String getInfoFromRequestAndAURI(User user, HttpServletRequest request, String artistUserRegistItems) {
        String info = StringUtils.EMPTY;
        //�����û�ע���� json��ת��������
        JSONArray json = JSONArray.fromObject(artistUserRegistItems);
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
                //�������ֻ�ȡ�ַ��� todo ����û�д� ��Ҫ��ֵԭ����ֵ
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
}
