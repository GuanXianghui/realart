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
 * ��ά��ʵ�������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 20:22
 */
public class QrCodeDao {
    /**
     * ����״̬���ά����
     * ���state>0������Ϊ����
     * ���uuid�ǿմ�����Ϊ����
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
                throw new RuntimeException("���ݿ�������������ԣ�");
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
     * �������кţ�״̬��ҳ����ά��
     * ���state>0������Ϊ����
     * ���uuid�ǿմ�����Ϊ����
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
                throw new RuntimeException("���ݿ�������������ԣ�");
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
     * ����״̬���ά��
     * ���state>0������Ϊ����
     * ���uuid�ǿմ�����Ϊ����
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
                throw new RuntimeException("���ݿ�������������ԣ�");
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
     * ����id���ά��
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
                throw new RuntimeException("���ݿ�������������ԣ�");
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
     * ����uuid���ά��
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
                throw new RuntimeException("���ݿ�������������ԣ�");
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
     * ��������Ʒid���ά��
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
                throw new RuntimeException("���ݿ�������������ԣ�");
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
     * ������ά��
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
     * ���¶�ά��
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
     * ɾ����ά��
     *
     * @param qrCode
     * @throws Exception
     */
    public static void deleteQrCode(QrCode qrCode) throws Exception {
        String sql = "delete from qr_code where id=" + qrCode.getId();
        DB.executeUpdate(sql);
    }
}
