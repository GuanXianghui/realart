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
 * 修改艺术家资料
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
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("certName:[" + certName + "],titlePhoto:[" + titlePhoto + "],headPhoto:[" + headPhoto + "]");
        //判字段为空
        if(StringUtils.isBlank(certName)){
            message = "请输入必输项";
            return ERROR;
        }
        getUser().setCertName(certName);

        if(titlePhoto != null){
            //创建压题图
            String titlePhotoRoute = createTitlePhoto(getUser());
            getUser().setTitlePhoto(titlePhotoRoute);
        }
        if(headPhoto != null){
            //创建个人图片
            String headPhotoRoute = createHeadPhoto(getUser());
            getUser().setHeadPhoto(headPhotoRoute);
        }

        //艺术家用户注册项
        String artistUserRegistItems = ParamUtil.getInstance().getValueByName(ParamInterface.ARTIST_USER_REGIST_ITEMS);
        //评论用户注册项 json串转换成数组
        JSONArray json = JSONArray.fromObject(artistUserRegistItems);
        for(int i=0;i<json.size();i++)
        {
            JSONObject temp = json.getJSONObject(i);
            String name = (StringUtils.trimToEmpty((String) temp.get("name")));
            String need = (StringUtils.trimToEmpty((String) temp.get("need")));
            String type = (StringUtils.trimToEmpty((String) temp.get("type")));
            //非空校验
            if(Integer.parseInt(need) == COLUMN_NEED_YES){//判字符串
                if(Integer.parseInt(type) == COLUMN_TYPE_STRING){
                    //根据名字获取字符串
                    String value = request.getParameter("item" + (i+1));
                    if(StringUtils.isBlank(value)){
                        message = "请输入" + name;
                        return ERROR;
                    }
                } else if(Integer.parseInt(type) == COLUMN_TYPE_FILE){//判文件
                    //文件不判必输 因为这里是修改，之前肯定上传过，如果现在没传，说明不想改
                }
            }
        }

        //得到用户信息
        String info = getInfoFromRequestAndAURI(getUser(), request, artistUserRegistItems);
        getUser().setInfo(info);

        UserDao.updateUserInfo(getUser());

        if(getUser().getState() == UserInterface.USER_STATE_CHECK_FAILED){
            getUser().setState(UserInterface.USER_STATE_NEED_CHECK);
            UserDao.updateUserState(getUser());
        }

        //刷新session缓存中的user
        refreshSessionUser(getUser());

        message = "修改用户成功！";
        return SUCCESS;
    }

    /**
     * 创建压题图
     * @param user
     * @return
     */
    private String createTitlePhoto(User user){
        //新的图片名称
        String headPhotoName = user.getId() + "_" + new Date().getTime() + ".jpg";
        //服务器上的路径
        String headPhotoPath = ServletActionContext.getServletContext().getRealPath("images/user/title") + "/" + headPhotoName;
        //页面引用位置 相对路径
        String headPhotoPagePath = "images/user/title/" + headPhotoName;
        //服务器上的路径对应的文件
        File imageFile = new File(headPhotoPath);
        //拷贝文件
        FileUtil.copy(titlePhoto, imageFile);
        return headPhotoPagePath;
    }

    /**
     * 创建个人图片
     * @param user
     * @return
     */
    private String createHeadPhoto(User user){
        //新的图片名称
        String headPhotoName = user.getId() + "_" + new Date().getTime() + ".jpg";
        //服务器上的路径
        String headPhotoPath = ServletActionContext.getServletContext().getRealPath("images/user/head") + "/" + headPhotoName;
        //页面引用位置 相对路径
        String headPhotoPagePath = "images/user/head/" + headPhotoName;
        //服务器上的路径对应的文件
        File imageFile = new File(headPhotoPath);
        //拷贝文件
        FileUtil.copy(headPhoto, imageFile);
        return headPhotoPagePath;
    }

    /**
     * 得到用户信息 json串
     * @param user
     * @param request
     * @param artistUserRegistItems
     * @return
     */
    private String getInfoFromRequestAndAURI(User user, HttpServletRequest request, String artistUserRegistItems) {
        String info = StringUtils.EMPTY;
        //评论用户注册项 json串转换成数组
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
                //根据名字获取字符串 todo 这里没有传 需要赋值原来的值
                String value = request.getParameter("item" + (i+1));
                info += "\"" + name + "\":\"" + StringUtils.trimToEmpty(value) + "\"";
            } else if(Integer.parseInt(type) == COLUMN_TYPE_FILE){//判文件
                //根据名字获取文件
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
     * 创建图片
     * @param index
     * @param user
     * @return
     */
    private String createUserPhoto(User user, int index, File photo){
        //新的图片名称
        String photoName = user.getId() + "_" + index + "_" + new Date().getTime() + ".jpg";
        //服务器上的路径
        String photoPath = ServletActionContext.getServletContext().getRealPath("images/user") + "/" + photoName;
        //页面引用位置 相对路径
        String photoPagePath = "images/user/" + photoName;
        //服务器上的路径对应的文件
        File imageFile = new File(photoPath);
        //拷贝文件
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
