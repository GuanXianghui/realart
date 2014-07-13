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
 * 管理员注册艺术家
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class AdminRegistArtistFetchAction extends BaseAction implements UserInterface{
    private File file;
    private String resultFile;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("file:[" + file + "]");
        //判字段为空
        if(file == null){
            message = "服务器端未接受到文件";
            return ERROR;
        }

        //服务器端保存位置
        String fileName = DateUtil.getNowDate() + DateUtil.getNowTime() + ".xls";
        String routePath = ServletActionContext.getServletContext().getRealPath("files/regist-artist-fetch/upload") + "/" + fileName;
        //服务器上的路径对应的文件
        File serverFile = new File(routePath);
        //拷贝文件
        FileUtil.copy(file, serverFile);

        HSSFWorkbook workbook;
        HSSFSheet sheet;

        // 1 装载文件看是否有异常
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));
            sheet = workbook.getSheetAt(0);
        } catch (Exception e) {
            logger.error("装载文件异常", e);
            message = "装载文件异常";
            return ERROR;
        }

        // 2 判是否有数据
        int totalRowNum = sheet.getLastRowNum();
        /**
         * 0表示一行都没有 或者 只有一行
         */
        logger.info("文件行数：" + totalRowNum);
        if(totalRowNum <= 0)
        {
            message = "该文件无数据";
            return ERROR;
        }

        // 3 校验第一行数据是否一致
        for(int i=0;i<totalRowNum;i++)
        {
            boolean isRowError = false;
            HSSFRow tempRow = sheet.getRow(i+1);//过滤掉第一行抬头
            if(tempRow.getLastCellNum() < 3){
                isRowError = true;
                tempRow.createCell(3);
                tempRow.getCell(3).setCellValue("注册失败，该行数据有误！");
                continue;
            }
            String name = getHSSFCellValue(tempRow.getCell(0));
            String password = getHSSFCellValue(tempRow.getCell(1));
            String certName = getHSSFCellValue(tempRow.getCell(2));
            logger.info(name + "," + password + "," + certName);
            if(StringUtils.isBlank(name) || StringUtils.isBlank(password) || StringUtils.isBlank(certName)){
                isRowError = true;
                tempRow.createCell(3);
                tempRow.getCell(3).setCellValue("注册失败，该行数据有误！");
                continue;
            }
            password = new MD5Util().md5(password + PropertyUtil.getInstance().getProperty(BaseInterface.MD5_KEY));

            //判该名字和用户类型是否已存在
            boolean isExist = UserDao.isNameExist(name);
            if(isExist || BaseUtil.isAdminName(name)){
                message = "该用户名已被用，请更换用户名！";
                isRowError = true;
                tempRow.createCell(3);
                tempRow.getCell(3).setCellValue("注册失败，该用户名已被用，请更换用户名！");
                continue;
            }

            try{
                //创建用户
                User user = new User(name, USER_TYPE_ARTIST, password, certName, StringUtils.EMPTY, StringUtils.EMPTY,
                        StringUtils.EMPTY, "{}", USER_STATE_NEED_CHECK, StringUtils.EMPTY, StringUtils.EMPTY, date,
                        time, getIp());
                UserDao.insertUser(user);
            } catch (Exception e){
                isRowError = true;
                tempRow.createCell(3);
                tempRow.getCell(3).setCellValue("注册失败，执行sql异常！");
                continue;
            }

            tempRow.createCell(3);
            tempRow.getCell(3).setCellValue("注册成功！");
        }

        String fileName2 = DateUtil.getNowDate() + DateUtil.getNowTime() + "_result" + ".xls";
        String routePath2 = ServletActionContext.getServletContext().getRealPath("files/regist-artist-fetch/upload") + "/" + fileName2;
        FileOutputStream fOut = new FileOutputStream(routePath2);
        workbook.write(fOut);
        fOut.flush();
        fOut.close();
        logger.info("生成结果文件成功！resultFile=" + routePath2);
        resultFile = "files/regist-artist-fetch/upload/" + fileName2;

        message = "批量注册艺术家结束，可下载结果！";
        return SUCCESS;
    }

    /**
     * 得到单元格的值
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
            case HSSFCell.CELL_TYPE_STRING://字符串类型
                cellValue = cell.getRichStringCellValue().getString();
                if (((String)cellValue).trim().equals("")
                        || ((String)cellValue).trim().length() <= 0) {
                    cellValue = "";
                }
                //cellValue = inputEncode((String)cellValue);
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字类型
//                if (valueArr.length == 2 && valueArr[1].equals("date")) {
//                    cellValue = cell.getDateCellValue();;
//                }
//                if (valueArr.length == 2 && valueArr[1].equals("timestamp")) {
//                    Date date = cell.getDateCellValue();
//                    SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String time=format1.format(date);
//                    cellValue= Timestamp.valueOf(time);
//                }else { // 如果长度为2，说明此列为默认字符串类型
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
