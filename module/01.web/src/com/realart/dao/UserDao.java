package com.realart.dao;

import com.realart.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * �û�ʵ�������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 20:22
 */
public class UserDao {
    /**
     * �����û����Ͳ�ѯ�û�
     *
     * @return
     * @throws Exception
     */
    public static List<User> queryUsersByUserType(int userType) throws Exception {
        List<User> list = new ArrayList<User>();
        String sql = "SELECT id,name,password,cert_name,title_photo,head_photo,email," +
                "info,state,reason,art_kinds,register_date,register_time,register_ip FROM user WHERE user_type=" +
                userType + " order by id";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("���ݿ�������������ԣ�");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String certName = rs.getString("cert_name");
                String titlePhoto = rs.getString("title_photo");
                String headPhoto = rs.getString("head_photo");
                String email = rs.getString("email");
                String info = rs.getString("info");
                int state = rs.getInt("state");
                String reason = rs.getString("reason");
                String artKinds = rs.getString("art_kinds");
                String registerDate = rs.getString("register_date");
                String registerTime = rs.getString("register_time");
                String registerIp = rs.getString("register_ip");
                User user = new User(id, name, userType, password, certName, titlePhoto,
                        headPhoto, email, info, state, reason, artKinds, registerDate, registerTime, registerIp);
                list.add(user);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * �����û����ͺ��û�״̬��ѯ�û�
     *
     * @return
     * @throws Exception
     */
    public static List<User> queryUsersByUserTypeAndState(int userType, int state) throws Exception {
        List<User> list = new ArrayList<User>();
        String sql = "SELECT id,name,password,cert_name,title_photo,head_photo,email," +
                "info,reason,art_kinds,register_date,register_time,register_ip FROM user WHERE user_type=" +
                userType + " AND state=" + state + " order by id";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("���ݿ�������������ԣ�");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String certName = rs.getString("cert_name");
                String titlePhoto = rs.getString("title_photo");
                String headPhoto = rs.getString("head_photo");
                String email = rs.getString("email");
                String info = rs.getString("info");
                String reason = rs.getString("reason");
                String artKinds = rs.getString("art_kinds");
                String registerDate = rs.getString("register_date");
                String registerTime = rs.getString("register_time");
                String registerIp = rs.getString("register_ip");
                User user = new User(id, name, userType, password, certName, titlePhoto,
                        headPhoto, email, info, state, reason, artKinds, registerDate, registerTime, registerIp);
                list.add(user);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * ����id���û�
     *
     * @param id
     * @return
     * @throws Exception
     */
    public static User getUserById(int id) throws Exception {
        String sql = "SELECT name,user_type,password,cert_name,title_photo," +
                "head_photo,email,info,state,reason,art_kinds,register_date,register_time,register_ip FROM user " +
                "WHERE id=" + id;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("���ݿ�������������ԣ�");
            }
            while (rs.next()) {
                String name = rs.getString("name");
                int userType = rs.getInt("user_type");
                String password = rs.getString("password");
                String certName = rs.getString("cert_name");
                String titlePhoto = rs.getString("title_photo");
                String headPhoto = rs.getString("head_photo");
                String email = rs.getString("email");
                String info = rs.getString("info");
                int state = rs.getInt("state");
                String reason = rs.getString("reason");
                String artKinds = rs.getString("art_kinds");
                String registerDate = rs.getString("register_date");
                String registerTime = rs.getString("register_time");
                String registerIp = rs.getString("register_ip");
                User user = new User(id, name, userType, password, certName, titlePhoto,
                        headPhoto, email, info, state, reason, artKinds, registerDate, registerTime, registerIp);
                return user;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * �����������û�
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static User getUserByName(String name) throws Exception {
        String sql = "SELECT id,password,user_type,cert_name,title_photo," +
                "head_photo,email,info,state,reason,art_kinds,register_date,register_time,register_ip FROM user " +
                "WHERE name='" + name + "'";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("���ݿ�������������ԣ�");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                int userType = rs.getInt("user_type");
                String certName = rs.getString("cert_name");
                String titlePhoto = rs.getString("title_photo");
                String headPhoto = rs.getString("head_photo");
                String email = rs.getString("email");
                String info = rs.getString("info");
                int state = rs.getInt("state");
                String reason = rs.getString("reason");
                String artKinds = rs.getString("art_kinds");
                String registerDate = rs.getString("register_date");
                String registerTime = rs.getString("register_time");
                String registerIp = rs.getString("register_ip");
                User user = new User(id, name, userType, password, certName, titlePhoto,
                        headPhoto, email, info, state, reason, artKinds, registerDate, registerTime, registerIp);
                return user;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * �и������Ƿ��Ѵ���
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static boolean isNameExist(String name) throws Exception {
        User user = getUserByName(name);
        return user != null;
    }

    /**
     * �����û�
     *
     * @param user
     * @throws Exception
     */
    public static void insertUser(User user) throws Exception {
        String sql = "insert into user" +
                "(id,name,user_type,password,cert_name,title_photo," +
                "head_photo,email,info,state,reason,art_kinds,register_date,register_time,register_ip)" +
                "values" +
                "(null,'" + user.getName() + "'," + user.getUserType() + ",'" + user.getPassword() + "','" +
                user.getCertName() + "','" + user.getTitlePhoto() + "','" + user.getHeadPhoto() + "','" +
                user.getEmail() + "','" + user.getInfo() + "'," + user.getState() + ",'" + user.getReason() + "','" +
                user.getArtKinds() + "','" + user.getRegisterDate() + "','" + user.getRegisterTime() + "','" + user.getRegisterIp() + "')";
        DB.executeUpdate(sql);

        //�����������û����Ͳ��û�
        user = getUserByName(user.getName());
    }

    /**
     * �����û�����
     *
     * @param user
     * @throws Exception
     */
    public static void updateUserPassword(User user) throws Exception {
        String sql = "update user set password='" + user.getPassword() + "' where id=" + user.getId();
        DB.executeUpdate(sql);
    }

    /**
     * �����û�״̬
     *
     * @param user
     * @throws Exception
     */
    public static void updateUserState(User user) throws Exception {
        String sql = "update user set state=" + user.getState() + ",reason='" + user.getReason() + "' where id=" + user.getId();
        DB.executeUpdate(sql);
    }

    /**
     * �����û���Ϣ
     *
     * @param user
     * @throws Exception
     */
    public static void updateUserInfo(User user) throws Exception {
        String sql = "update user set cert_name='" + user.getCertName() + "',title_photo='" +
                user.getTitlePhoto() + "',head_photo='" + user.getHeadPhoto() + "',email='" + user.getEmail() +
                "',info='" + user.getInfo() + "',reason='" + user.getReason() + "' where id=" + user.getId();
        DB.executeUpdate(sql);
    }

    /**
     * �����û� ����Ʒ���з���
     *
     * @param user
     * @throws Exception
     */
    public static void updateArtKinds(User user) throws Exception {
        String sql = "update user set art_kinds='" + user.getArtKinds() + "' where id=" + user.getId();
        DB.executeUpdate(sql);
    }
}
