package com.realart;

import com.realart.dao.UserDao;
import com.realart.entities.User;
import com.realart.interfaces.BaseInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * ����Աע��������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class AdminRegistArtistFetchAction extends BaseAction implements UserInterface{
    private File file;
    private String resultFile;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("file:[" + file + "]");
        //���ֶ�Ϊ��
        if(file == null){
            message = "��������δ���ܵ��ļ�";
            return ERROR;
        }

        //�������˱���λ��
        String fileName = DateUtil.getNowDate() + DateUtil.getNowTime() + ".xls";
        String routePath = ServletActionContext.getServletContext().getRealPath("files/regist-artist-fetch/upload") + "/" + fileName;
        //�������ϵ�·����Ӧ���ļ�
        File serverFile = new File(routePath);
        //�����ļ�
        FileUtil.copy(file, serverFile);

        HSSFWorkbook workbook;
        HSSFSheet sheet;

        // 1 װ���ļ����Ƿ����쳣
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));
            sheet = workbook.getSheetAt(0);
        } catch (Exception e) {
            logger.error("װ���ļ��쳣", e);
            message = "װ���ļ��쳣";
            return ERROR;
        }

        // 2 ���Ƿ�������
        int totalRowNum = sheet.getLastRowNum();
        /**
         * 0��ʾһ�ж�û�� ���� ֻ��һ��
         */
        logger.info("�ļ�������" + totalRowNum);
        if(totalRowNum <= 0)
        {
            message = "���ļ�������";
            return ERROR;
        }

        // 3 У���һ�������Ƿ�һ��
        for(int i=0;i<totalRowNum;i++)
        {
            boolean isRowError = false;
            HSSFRow tempRow = sheet.getRow(i+1);//���˵���һ��̧ͷ
            if(tempRow.getLastCellNum() < 3){
                isRowError = true;
                tempRow.createCell(3);
                tempRow.getCell(3).setCellValue("ע��ʧ�ܣ�������������");
                continue;
            }
            String name = getHSSFCellValue(tempRow.getCell(0));
            String password = getHSSFCellValue(tempRow.getCell(1));
            String certName = getHSSFCellValue(tempRow.getCell(2));
            logger.info(name + "," + password + "," + certName);
            if(StringUtils.isBlank(name) || StringUtils.isBlank(password) || StringUtils.isBlank(certName)){
                isRowError = true;
                tempRow.createCell(3);
                tempRow.getCell(3).setCellValue("ע��ʧ�ܣ�������������");
                continue;
            }
            password = new MD5Util().md5(password + PropertyUtil.getInstance().getProperty(BaseInterface.MD5_KEY));

            //�и����ֺ��û������Ƿ��Ѵ���
            boolean isExist = UserDao.isNameExist(name);
            if(isExist || BaseUtil.isAdminName(name)){
                message = "���û����ѱ��ã�������û�����";
                isRowError = true;
                tempRow.createCell(3);
                tempRow.getCell(3).setCellValue("ע��ʧ�ܣ����û����ѱ��ã�������û�����");
                continue;
            }

            try{
                //�����û�
                User user = new User(name, USER_TYPE_ARTIST, password, certName, StringUtils.EMPTY, StringUtils.EMPTY,
                        StringUtils.EMPTY, "{}", USER_STATE_NEED_CHECK, StringUtils.EMPTY, StringUtils.EMPTY, date,
                        time, getIp());
                UserDao.insertUser(user);
            } catch (Exception e){
                isRowError = true;
                tempRow.createCell(3);
                tempRow.getCell(3).setCellValue("ע��ʧ�ܣ�ִ��sql�쳣��");
                continue;
            }

            tempRow.createCell(3);
            tempRow.getCell(3).setCellValue("ע��ɹ���");
        }

        String fileName2 = DateUtil.getNowDate() + DateUtil.getNowTime() + "_result" + ".xls";
        String routePath2 = ServletActionContext.getServletContext().getRealPath("files/regist-artist-fetch/upload") + "/" + fileName2;
        FileOutputStream fOut = new FileOutputStream(routePath2);
        workbook.write(fOut);
        fOut.flush();
        fOut.close();
        logger.info("���ɽ���ļ��ɹ���resultFile=" + routePath2);
        resultFile = "files/regist-artist-fetch/upload/" + fileName2;

        message = "����ע�������ҽ����������ؽ����";
        return SUCCESS;
    }

    /**
     * �õ���Ԫ���ֵ
     * @param cell
     * @return
     */
    public static String getHSSFCellValue(HSSFCell cell)
    {
        if(null == cell)
        {
            return StringUtils.EMPTY;
        }
        Object cellValue = null;
//        String[] valueArr = mapvalue.split(",");
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING://�ַ�������
                cellValue = cell.getRichStringCellValue().getString();
                if (((String)cellValue).trim().equals("")
                        || ((String)cellValue).trim().length() <= 0) {
                    cellValue = "";
                }
                //cellValue = inputEncode((String)cellValue);
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://��������
//                if (valueArr.length == 2 && valueArr[1].equals("date")) {
//                    cellValue = cell.getDateCellValue();;
//                }
//                if (valueArr.length == 2 && valueArr[1].equals("timestamp")) {
//                    Date date = cell.getDateCellValue();
//                    SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String time=format1.format(date);
//                    cellValue= Timestamp.valueOf(time);
//                }else { // �������Ϊ2��˵������ΪĬ���ַ�������
                BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                // cellValue =big.toEngineeringString();
                cellValue = big.toString();
//                }
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                BigDecimal bigula = new BigDecimal(cell
                        .getCachedFormulaResultType());
                // cellValue = bigula.toEngineeringString();
                cellValue = bigula.toString();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                cellValue = "";
                break;
            default:
                break;
        }
        return null == cellValue?"":cellValue.toString();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getResultFile() {
        return resultFile;
    }

    public void setResultFile(String resultFile) {
        this.resultFile = resultFile;
    }
}
