package com.realart;

import com.realart.dao.QrCodeDao;
import com.realart.entities.QrCode;
import com.realart.interfaces.QrCodeInterface;

/**
 * ɾ����ά��
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteQrCodeAction extends BaseAction implements QrCodeInterface{
    private String qrCode;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("qrCode:[" + qrCode + "]");

        //��ѯ��ά�� �ж�ά���Ƿ�ʹ�� ��� �����к��Ѱ�����Ʒ������ɾ����
        QrCode qc = QrCodeDao.getQrCodeByUuid(Integer.parseInt(qrCode));
        if(qc.getState() == STATE_USED){
            message = "�����к��Ѱ�����Ʒ������ɾ����";
            return ERROR;
        }

        //ɾ����ά��
        QrCodeDao.deleteQrCode(qc);

        message = "ɾ����ά��ɹ���";
        return SUCCESS;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
