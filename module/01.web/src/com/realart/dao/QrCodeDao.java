package com.realart.dao;

import com.realart.entities.QrCode;
import com.realart.interfaces.BaseInterface;
import com.realart.utils.PropertyUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 二维码实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 20:22
 */
public class QrCodeDao {
    /**
     * 根据状态查二维码量
     * 如果state>0带上作为条件
     * 如果uuid非空带上作为条件
     *
     * @param uuid
     * @param state
     * @return
     * @throws Exception
     */
    public static int countQrCodesByUuidAndState(String uuid, int state) throws Exception {
        String sql = "SELECT count(1) count_num FROM qr_code WHERE 1=1";
        if(StringUtils.isNotBlank(uuid)){
            sql += " AND uuid='" + uuid + "'";
        }
        if(state > 0){
            sql += " AND state=" + state;
        }
        sql += " ORDER BY ID DESC";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int countNow = rs.getInt("count_num");
                return countNow;
            }
            return 0;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据序列号，状态，页码查二维码
     * 如果state>0带上作为条件
     * 如果uuid非空带上作为条件
     *
     * @param uuid
     * @param state
     * @param pageNum
     * @return
     * @throws Exception
     */
    public static List<QrCode> queryQrCodesByUuidAndStateAndPayNum(String uuid, int state, int pageNum) throws Exception {
        List<QrCode> list = new ArrayList<QrCode>();
        int pageSize = Integer.parseInt(PropertyUtil.getInstance().getProperty(BaseInterface.QR_CODE_PAGE_SIZE));
        String sql = "SELECT id,uuid,img_path,state,art_id,info,create_date,create_time,create_ip" +
                " FROM qr_code WHERE 1=1";
        if(StringUtils.isNotBlank(uuid)){
            sql += " AND uuid='" + uuid + "'";
        }
        if(state > 0){
            sql += " AND state=" + state;
        }
        sql += " ORDER BY ID DESC limit " + ((pageNum-1) * pageSize) + "," + pageSize;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String uuid2 = rs.getString("uuid");
                String imgPath = rs.getString("img_path");
                int state2 = rs.getInt("state");
                int artId = rs.getInt("art_id");
                String info = rs.getString("info");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                QrCode qrCode = new QrCode(id, uuid2, imgPath, state2, artId, info, createDate, createTime, createIp);
                list.add(qrCode);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据状态查二维码
     * 如果state>0带上作为条件
     * 如果uuid非空带上作为条件
     *
     * @return
     * @throws Exception
     */
    public static List<QrCode> queryQrCodesByUuidAndState(String uuid, int state) throws Exception {
        List<QrCode> list = new ArrayList<QrCode>();
        String sql = "SELECT id,uuid,img_path,state,art_id,info,create_date,create_time,create_ip" +
                " FROM qr_code WHERE 1=1";
        if(StringUtils.isNotBlank(uuid)){
            sql += " AND uuid='" + uuid + "'";
        }
        if(state > 0){
            sql += " AND state=" + state;
        }
        sql += " ORDER BY ID DESC";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String uuid2 = rs.getString("uuid");
                String imgPath = rs.getString("img_path");
                int state2 = rs.getInt("state");
                int artId = rs.getInt("art_id");
                String info = rs.getString("info");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                QrCode qrCode = new QrCode(id, uuid2, imgPath, state2, artId, info, createDate, createTime, createIp);
                list.add(qrCode);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据id查二维码
     *
     * @return
     * @throws Exception
     */
    public static QrCode getQrCodeByUuid(int id) throws Exception {
        QrCode qrCode = null;
        String sql = "SELECT id,uuid,img_path,state,art_id,info,create_date,create_time,create_ip" +
                " FROM qr_code WHERE id='" + id + "' ORDER BY ID DESC";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                String imgPath = rs.getString("img_path");
                int state = rs.getInt("state");
                int artId = rs.getInt("art_id");
                String info = rs.getString("info");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                qrCode = new QrCode(id, uuid, imgPath, state, artId, info, createDate, createTime, createIp);
            }
            return qrCode;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据uuid查二维码
     *
     * @return
     * @throws Exception
     */
    public static QrCode getQrCodeByUuid(String uuid) throws Exception {
        QrCode qrCode = null;
        String sql = "SELECT id,uuid,img_path,state,art_id,info,create_date,create_time,create_ip" +
                " FROM qr_code WHERE uuid='" + uuid + "' ORDER BY ID DESC";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String imgPath = rs.getString("img_path");
                int state = rs.getInt("state");
                int artId = rs.getInt("art_id");
                String info = rs.getString("info");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                qrCode = new QrCode(id, uuid, imgPath, state, artId, info, createDate, createTime, createIp);
            }
            return qrCode;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据艺术品id查二维码
     *
     * @return
     * @throws Exception
     */
    public static QrCode getQrCodeByArtId(int artId) throws Exception {
        QrCode qrCode = null;
        String sql = "SELECT id,uuid,img_path,state,art_id,info,create_date,create_time,create_ip" +
                " FROM qr_code WHERE art_id=" + artId + " ORDER BY ID DESC";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String uuid = rs.getString("uuid");
                String imgPath = rs.getString("img_path");
                int state = rs.getInt("state");
                String info = rs.getString("info");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                qrCode = new QrCode(id, uuid, imgPath, state, artId, info, createDate, createTime, createIp);
            }
            return qrCode;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 新增二维码
     *
     * @param qrCode
     * @throws Exception
     */
    public static void insertQrCode(QrCode qrCode) throws Exception {
        String sql = "insert into qr_code" +
                "(id,uuid,img_path,state,art_id,info,create_date,create_time,create_ip) values" +
                "(null,'" + qrCode.getUuid() + "','" + qrCode.getImgPath() + "'," + qrCode.getState() + "," +
                qrCode.getArtId() + ",'" + qrCode.getInfo() + "','" + qrCode.getCreateDate() + "','" +
                qrCode.getCreateTime() + "','" + qrCode.getCreateIp() + "')";
        DB.executeUpdate(sql);
    }

    /**
     * 更新二维码
     *
     * @param qrCode
     * @throws Exception
     */
    public static void updateQrCode(QrCode qrCode) throws Exception {
        String sql = "update qr_code set img_path='" + qrCode.getImgPath() + "',state=" + qrCode.getState() +
                ",art_id=" + qrCode.getArtId()  + ",info='" + qrCode.getInfo() + "' where id=" + qrCode.getId();
        DB.executeUpdate(sql);
    }

    /**
     * 删除二维码
     *
     * @param qrCode
     * @throws Exception
     */
    public static void deleteQrCode(QrCode qrCode) throws Exception {
        String sql = "delete from qr_code where id=" + qrCode.getId();
        DB.executeUpdate(sql);
    }
}
