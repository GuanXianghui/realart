package com.realart;

import com.realart.dao.QrCodeDao;
import com.realart.entities.QrCode;
import com.realart.interfaces.QrCodeInterface;
import com.realart.utils.DateUtil;
import com.realart.utils.FileUtil;
import com.realart.utils.TokenUtil;
import com.realart.utils.ZipUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.util.List;

/**
 * 下载二维码
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DownloadQrCodeAction extends BaseAction implements QrCodeInterface{
    private String uuid;
    private String state;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("uuid:[" + uuid + "],state:[" + state + "]");

        //服务器上的路径
        String serverPath = ServletActionContext.getServletContext().getRealPath("images/qr/");
        //批次号
        String fetchNo = DateUtil.getNowDate() + DateUtil.getNowTime();
        //创建文件夹
        String fetchDir = serverPath + "/" + fetchNo + "/";
        FileUtil.makeDir(fetchDir);
        //根据状态查二维码
        List<QrCode> qrCodes= QrCodeDao.queryQrCodesByUuidAndState(uuid, Integer.parseInt(state));
        if(qrCodes.size() == 0){
            message = "没有符合条件的二维码！";
            //返回结果
            String resp = "{isSuccess:false,message:'" + message + "',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            write(resp);
        } else {
            /**
             * 备注文件内容
             * 格式：
             * 序列号1 二维码对应地址1
             * 序列号2 二维码对应地址2
             * ...
             */
            String fileName = fetchDir + "/" + fetchNo + ".txt";
            String fileContent = StringUtils.EMPTY;
            //批量 拷贝文件到新建文件夹中
            for(QrCode qrCode : qrCodes){
                //拷贝文件到新建文件夹中
                String qrCodeFileName = qrCode.getUuid() + ".png";
                FileUtil.copy(serverPath + "/" + qrCodeFileName, fetchDir + "/" + qrCodeFileName);
                if(StringUtils.isNotBlank(fileContent)){
                    fileContent += "\r\n";
                }
                fileContent += "序列号:[" + qrCode.getUuid() + "]对应网址[" + QR_CODE_URL_PREFIX + qrCode.getUuid() + "]";
            }
            //生成备注文件
            FileUtil.writeFile(fileName, fileContent);
            //压缩文件夹
            String zipFile = serverPath + "/" + fetchNo + ".zip";
            ZipUtil.compressDir(fetchDir, zipFile);
            //删除文件夹
            FileUtil.deleteFile(fetchDir);
            //下载zip相对路径
            String downloadZip = "images/qr/" + fetchNo + ".zip";
            message = "创建压缩文件成功！";

            //返回结果
            String resp = "{isSuccess:true,message:'" + message + "',downloadZip:'" + downloadZip + "'," +
                    "hasNewToken:true,token:'" + TokenUtil.createToken(request) + "'}";
            write(resp);
        }
        return null;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
