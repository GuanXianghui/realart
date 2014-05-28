package com.realart.dao;

import com.realart.entities.Art;
import com.realart.entities.User;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 艺术作品实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 20:22
 */
public class ArtDao {
    /**
     * 根据用户id和状态查艺术作品
     *
     * @return
     * @throws Exception
     */
    public static List<Art> queryArtsByUserIdAndState(int userId, int state) throws Exception {
        List<Art> list = new ArrayList<Art>();
        String sql = "SELECT id,user_id,name,state,reason,photo,photo0,photo1,photo2,photo3,photo4,gongyi,type," +
                "length,width,height,build_date,title,introduction,location_bit,create_date,create_time," +
                "create_ip FROM art WHERE 1=1";
        if(userId > 0){
            sql += " AND user_id=" + userId;
        }
        if(state > -1){
            sql += " AND state=" + state;
        }
        sql += " ORDER BY ID";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId2 = rs.getInt("user_id");
                String name = rs.getString("name");
                int state2 = rs.getInt("state");
                String reason = rs.getString("reason");
                String photo = rs.getString("photo");
                String photo0 = rs.getString("photo0");
                String photo1 = rs.getString("photo1");
                String photo2 = rs.getString("photo2");
                String photo3 = rs.getString("photo3");
                String photo4 = rs.getString("photo4");
                String gongyi = rs.getString("gongyi");
                String type = rs.getString("type");
                float length = rs.getFloat("length");
                float width = rs.getFloat("width");
                float height = rs.getFloat("height");
                String buildDate = rs.getString("build_date");
                String title = rs.getString("title");
                String introduction = rs.getString("introduction");
                String locationBit = rs.getString("location_bit");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                Art art = new Art(id, userId2, name, state2, reason, photo, photo0, photo1, photo2, photo3, photo4, gongyi,
                        type, length, width, height, buildDate, title, introduction, locationBit, createDate,
                        createTime, createIp);
                list.add(art);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据id查艺术作品
     *
     * @param id
     * @return
     * @throws Exception
     */
    public static Art getArtById(int id) throws Exception {
        String sql = "SELECT id,user_id,name,state,reason,photo,photo0,photo1,photo2,photo3,photo4,gongyi,type," +
                "length,width,height,build_date,title,introduction,location_bit,create_date,create_time," +
                "create_ip FROM art WHERE id=" + id;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                int state = rs.getInt("state");
                String reason = rs.getString("reason");
                String photo = rs.getString("photo");
                String photo0 = rs.getString("photo0");
                String photo1 = rs.getString("photo1");
                String photo2 = rs.getString("photo2");
                String photo3 = rs.getString("photo3");
                String photo4 = rs.getString("photo4");
                String gongyi = rs.getString("gongyi");
                String type = rs.getString("type");
                float length = rs.getFloat("length");
                float width = rs.getFloat("width");
                float height = rs.getFloat("height");
                String buildDate = rs.getString("build_date");
                String title = rs.getString("title");
                String introduction = rs.getString("introduction");
                String locationBit = rs.getString("location_bit");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                Art art = new Art(id, userId, name, state, reason, photo, photo0, photo1, photo2, photo3, photo4, gongyi,
                        type, length, width, height, buildDate, title, introduction, locationBit, createDate,
                        createTime, createIp);
                return art;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 新增艺术作品
     *
     * @param art
     * @throws Exception
     */
    public static void insertArt(Art art) throws Exception {
        String sql = "insert into art" +
                "(id,user_id,name,state,reason,photo,photo0,photo1,photo2,photo3,photo4,gongyi,type," +
                "length,width,height,build_date,title,introduction,location_bit,create_date,create_time," +
                "create_ip) values" +
                "(null," + art.getUserId() + ",'" + art.getName() + "'," + art.getState() + ",'" + art.getReason() + "','" + art.getPhoto() +
                "','" + art.getPhoto0() + "','" + art.getPhoto1() + "','" + art.getPhoto2() + "','" + art.getPhoto3() +
                "','" + art.getPhoto4() + "','" + art.getGongyi() + "','" + art.getType() + "'," + art.getLength() +
                "," + art.getWidth() + "," + art.getHeight() + ",'" + art.getBuildDate() + "','" + art.getTitle() +
                "','" + art.getIntroduction() + "','" + art.getLocationBit() + "','" + art.getCreateDate() + "','" +
                art.getCreateTime() + "','" + art.getCreateIp()+ "')";
        DB.executeUpdate(sql);

        //根据用户id查最大的艺术品id
        art.setId(getMaxIdByUserId(art.getUserId()));
    }

    /**
     * 更新状态
     *
     * @param art
     * @throws Exception
     */
    public static void updateState(Art art) throws Exception {
        String sql = "update art set state=" + art.getState() + ",reason='" + art.getReason() + "' where id=" + art.getId();
        DB.executeUpdate(sql);
    }

    /**
     * 根据用户id查最大的艺术品id
     * @param userId
     * @return
     * @throws Exception
     */
    public static int getMaxIdByUserId(int userId) throws Exception{
        String sql = "SELECT MAX(id) max_id FROM art WHERE user_id=" + userId;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int maxId = rs.getInt("max_id");
                return maxId;
            }
            return 0;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 修改图片
     * @param art
     */
    public static void updatePhotos(Art art) throws Exception {
        String sql = "update art set photo='" + art.getPhoto() + "',photo0='" + art.getPhoto0() + "',photo1='" +
                art.getPhoto1() + "',photo2='" + art.getPhoto2() + "',photo3='" + art.getPhoto3() + "',photo4='" +
                art.getPhoto4() + "' where id=" + art.getId();
        DB.executeUpdate(sql);
    }

    /**
     * 修改位置位图
     * @param art
     * @throws Exception
     */
    public static void updateLocationBit(Art art) throws Exception{
        String sql = "update art set location_bit='" + art.getLocationBit() + "' where id=" + art.getId();
        DB.executeUpdate(sql);
    }

    /**
     * 修改艺术品
     * @param art
     * @throws Exception
     */
    public static void updateInfo(Art art) throws Exception{
        String sql = "update art set name='" + art.getName() + "',gongyi='" + art.getGongyi() + "',type='" +
                art.getType() + "',length=" + art.getLength() + ",width=" + art.getWidth() + ",height=" +
                art.getHeight() + ",build_date='" + art.getBuildDate() + "',title='" + art.getTitle() + "'," +
                "introduction='" + art.getIntroduction() + "' where id=" + art.getId();
        DB.executeUpdate(sql);
    }

    /**
     * 根据位置查询艺术品
     *
     * @return
     * @throws Exception
     */
    public static List<Art> queryArtsByType(String type) throws Exception {
        List<Art> list = new ArrayList<Art>();
        String sql = "SELECT id,user_id,name,state,reason,photo,photo0,photo1,photo2,photo3,photo4,gongyi,type," +
                "length,width,height,build_date,title,introduction,location_bit,create_date,create_time," +
                "create_ip FROM art WHERE 1=1";
        if(StringUtils.equals(type, "yszlTop")){
            sql += " AND location_bit like '_1________'";
        }

        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                int state = rs.getInt("state");
                String reason = rs.getString("reason");
                String photo = rs.getString("photo");
                String photo0 = rs.getString("photo0");
                String photo1 = rs.getString("photo1");
                String photo2 = rs.getString("photo2");
                String photo3 = rs.getString("photo3");
                String photo4 = rs.getString("photo4");
                String gongyi = rs.getString("gongyi");
                String type2 = rs.getString("type");
                float length = rs.getFloat("length");
                float width = rs.getFloat("width");
                float height = rs.getFloat("height");
                String buildDate = rs.getString("build_date");
                String title = rs.getString("title");
                String introduction = rs.getString("introduction");
                String locationBit = rs.getString("location_bit");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                Art art = new Art(id, userId, name, state, reason, photo, photo0, photo1, photo2, photo3, photo4, gongyi,
                        type2, length, width, height, buildDate, title, introduction, locationBit, createDate,
                        createTime, createIp);
                list.add(art);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }
}
