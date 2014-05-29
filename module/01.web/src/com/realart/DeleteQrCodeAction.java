package com.realart;

import com.realart.dao.QrCodeDao;
import com.realart.entities.QrCode;
import com.realart.interfaces.QrCodeInterface;

/**
 * 删除二维码
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteQrCodeAction extends BaseAction implements QrCodeInterface{
    private String qrCode;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("qrCode:[" + qrCode + "]");

        //查询二维码 判二维码是否被使用 如果 该序列号已绑定艺术品，不能删除！
        QrCode qc = QrCodeDao.getQrCodeByUuid(Integer.parseInt(qrCode));
        if(qc.getState() == STATE_USED){
            message = "该序列号已绑定艺术品，不能删除！";
            return ERROR;
        }

        //删除二维码
        QrCodeDao.deleteQrCode(qc);

        message = "删除二维码成功！";
        return SUCCESS;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
