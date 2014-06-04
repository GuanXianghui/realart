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
 * ���ض�ά��
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DownloadQrCodeAction extends BaseAction implements QrCodeInterface{
    private String uuid;
    private String state;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("uuid:[" + uuid + "],state:[" + state + "]");

        //�������ϵ�·��
        String serverPath = ServletActionContext.getServletContext().getRealPath("images/qr/");
        //���κ�
        String fetchNo = DateUtil.getNowDate() + DateUtil.getNowTime();
        //�����ļ���
        String fetchDir = serverPath + "/" + fetchNo + "/";
        FileUtil.makeDir(fetchDir);
        //����״̬���ά��
        List<QrCode> qrCodes= QrCodeDao.queryQrCodesByUuidAndState(uuid, Integer.parseInt(state));
        if(qrCodes.size() == 0){
            message = "û�з��������Ķ�ά�룡";
            //���ؽ��
            String resp = "{isSuccess:false,message:'" + message + "',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            write(resp);
        } else {
            /**
             * ��ע�ļ�����
             * ��ʽ��
             * ���к�1 ��ά���Ӧ��ַ1
             * ���к�2 ��ά���Ӧ��ַ2
             * ...
             */
            String fileName = fetchDir + "/" + fetchNo + ".txt";
            String fileContent = StringUtils.EMPTY;
            //���� �����ļ����½��ļ�����
            for(QrCode qrCode : qrCodes){
                //�����ļ����½��ļ�����
                String qrCodeFileName = qrCode.getUuid() + ".png";
                FileUtil.copy(serverPath + "/" + qrCodeFileName, fetchDir + "/" + qrCodeFileName);
                if(StringUtils.isNotBlank(fileContent)){
                    fileContent += "\r\n";
                }
                fileContent += "���к�:[" + qrCode.getUuid() + "]��Ӧ��ַ[" + QR_CODE_URL_PREFIX + qrCode.getUuid() + "]";
            }
            //���ɱ�ע�ļ�
            FileUtil.writeFile(fileName, fileContent);
            //ѹ���ļ���
            String zipFile = serverPath + "/" + fetchNo + ".zip";
            ZipUtil.compressDir(fetchDir, zipFile);
            //ɾ���ļ���
            FileUtil.deleteFile(fetchDir);
            //����zip���·��
            String downloadZip = "images/qr/" + fetchNo + ".zip";
            message = "����ѹ���ļ��ɹ���";

            //���ؽ��
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
