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
 * 注册评论会员
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
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],certName:[" + certName + "],titlePhoto:[" + titlePhoto + "]," +
                "headPhoto:[" + headPhoto + "],email:[" + email + "],password:[" + password + "]," +
                "confirmPassword:[" + confirmPassword + "]");
        //判字段为空
        if(StringUtils.isBlank(name) || StringUtils.isBlank(certName) || StringUtils.isBlank(email) ||
                StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)){
            message = "请输入必输项";
            return ERROR;
        }
        //判密码是否一致
        if(!StringUtils.equals(password, confirmPassword)){
            message = "密码输入有误";
            return ERROR;
        }
        //判图片为空
        if(null == titlePhoto || null == headPhoto)
        {
            message = "请上传必须的图片!";
            return ERROR;
        }

        //评论用户注册项
        String reviewUserRegistItems = ParamUtil.getInstance().getValueByName(ParamInterface.REVIEW_USER_REGIST_ITEMS);
        //评论用户注册项 json串转换成数组
        JSONArray json = JSONArray.fromObject(reviewUserRegistItems);
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
                    //根据名字获取文件
                    MultiPartRequestWrapper mpRequest = (MultiPartRequestWrapper)ServletActionContext.getRequest();
                    File[] files = mpRequest.getFiles("item" + (i+1));
                    if(files == null || files.length == 0 || files[0] == null){
                        message = "请上传" + name;
                        return ERROR;
                    }
                }
            }
        }

        //判该名字和用户类型是否已存在
        boolean isExist = UserDao.isNameExist(name);
        if(isExist || BaseUtil.isAdminName(name)){
            message = "该用户名已被用，请更换用户名！";
            return ERROR;
        }

        //创建用户
        User user = new User(name, USER_TYPE_REVIEW, password, certName, StringUtils.EMPTY, StringUtils.EMPTY,
                email, StringUtils.EMPTY, USER_STATE_NORMAL, StringUtils.EMPTY, date, time, getIp());
        UserDao.insertUser(user);

        //根据姓名和用户类型查用户
        user = UserDao.getUserByName(name);
        //创建压题图
        String titlePhotoRoute = createTitlePhoto(user);
        //创建个人图片
        String headPhotoRoute = createHeadPhoto(user);
        //得到用户信息
        String info = getInfoFromRequestAndRURI(user, request, reviewUserRegistItems);
        //更新用户信息
        user.setTitlePhoto(titlePhotoRoute);
        user.setHeadPhoto(headPhotoRoute);
        user.setInfo(info);
        UserDao.updateUserInfo(user);
        //刷新session缓存中的user
        refreshSessionUser(user);
        //清空session缓存中的管理员用户
        clearSessionAdminUser();

        message = "创建用户成功！";
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
     * @param reviewUserRegistItems
     * @return
     */
    private String getInfoFromRequestAndRURI(User user, HttpServletRequest request, String reviewUserRegistItems) {
        String info = StringUtils.EMPTY;
        //评论用户注册项 json串转换成数组
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
                //根据名字获取字符串
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
