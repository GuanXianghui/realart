package com.realart.dao;

import com.realart.entities.UserToken;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 用户TOKEN实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-17 23:36
 */
public class UserTokenDao {
    /**
     * 新增用户TOKEN
     *
     * @param userToken
     * @throws Exception
     */
    public static void insertUserToken(UserToken userToken) throws Exception {
        String sql = "insert into user_token(token,user_id,state,create_date,create_time,create_ip," +
                "update_date,update_time,update_ip)values('" + userToken.getToken() + "'," +
                userToken.getUserId() + "," + userToken.getState() + ",'" + userToken.getCreateDate() + "','" +
                userToken.getCreateTime() + "','" + userToken.getCreateIp() + "','" + userToken.getUpdateDate() +
                "','" + userToken.getUpdateTime() + "','" + userToken.getUpdateIp() + "')";
        DB.executeUpdate(sql);
    }

    /**
     * 根据token查用户TOKEN
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static UserToken getUserToken(String token) throws Exception {
        String sql = "SELECT token,user_id,state,create_date,create_time,create_ip,update_date," +
                "update_time,update_ip FROM user_token WHERE token='" + token + "'";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                int state = rs.getInt("state");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                String updateIp = rs.getString("update_ip");
                UserToken userToken = new UserToken(token, userId, state, createDate, createTime, createIp,
                        updateDate, updateTime, updateIp);
                return userToken;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 更新用户TOKEN状态
     *
     * @param userToken
     * @throws Exception
     */
    public static void updateUserTokenState(UserToken userToken) throws Exception {
        String sql = "update user_token set state=" + userToken.getState() + ",update_date='" +
                userToken.getUpdateDate() + "',update_time='" + userToken.getUpdateTime() + "',update_ip='" +
                userToken.getUpdateIp() + "' where token='" + userToken.getToken() + "'";
        DB.executeUpdate(sql);
    }
}
