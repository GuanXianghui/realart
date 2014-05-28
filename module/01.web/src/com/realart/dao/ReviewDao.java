package com.realart.dao;

import com.realart.entities.Review;
import com.realart.interfaces.ReviewInterface;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 评论实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 20:22
 */
public class ReviewDao implements ReviewInterface {
    /**
     * 根据用户Id查询评论
     *
     * @return
     * @throws Exception
     */
    public static List<Review> queryReviewsByUserId(int userId) throws Exception {
        List<Review> list = new ArrayList<Review>();
        String sql = "SELECT id,title,type,photo,content,location_bit,create_date,create_time,create_ip FROM" +
                " review WHERE user_id=" + userId + " order by id";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String type = rs.getString("type");
                String photo = rs.getString("photo");
                String content = rs.getString("content");
                String locationBit = rs.getString("location_bit");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                Review review = new Review(id, userId, title, type, photo, content, locationBit, createDate,
                        createTime, createIp);
                list.add(review);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据id查评论
     *
     * @param id
     * @return
     * @throws Exception
     */
    public static Review getReviewById(int id) throws Exception {
        String sql = "SELECT user_id,title,type,photo,content,location_bit,create_date,create_time,create_ip" +
                " FROM review WHERE id=" + id;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String title = rs.getString("title");
                String type = rs.getString("type");
                String photo = rs.getString("photo");
                String content = rs.getString("content");
                String locationBit = rs.getString("location_bit");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                Review review = new Review(id, userId, title, type, photo, content, locationBit, createDate,
                        createTime, createIp);
                return review;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 新增评论
     *
     * @param review
     * @throws Exception
     */
    public static void insertReview(Review review) throws Exception {
        String sql = "insert into review" +
                "(id,user_id,title,type,photo,content,location_bit,create_date,create_time,create_ip)" +
                "values" +
                "(null," + review.getUserId() + ",'" + review.getTitle() + "','" + review.getType() + "','" +
                review.getPhoto() + "','" + review.getContent() + "','" + review.getLocationBit() +
                "','" + review.getCreateDate() + "','" + review.getCreateTime() + "','" + review.getCreateIp() + "')";
        DB.executeUpdate(sql);

        //设置id <- 根据用户id查询最大的评论id
        review.setId(getMaxIdByUserId(review.getUserId()));
    }

    /**
     * 更新位置信息
     *
     * @param review
     * @throws Exception
     */
    public static void updateLocationBit(Review review) throws Exception {
        String sql = "update review set location_bit='" + review.getLocationBit() + "' where id=" + review.getId();
        DB.executeUpdate(sql);
    }

    /**
     * 根据用户id查询最大的评论id
     * @param userId
     * @return
     * @throws Exception
     */
    public static int getMaxIdByUserId(int userId) throws Exception {
        String sql = "SELECT MAX(id) max_id FROM review WHERE user_id=" + userId;
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
     * 根据用户Id查询评论
     *
     * @return
     * @throws Exception
     */
    public static List<Review> queryReviewsByType(String type) throws Exception {
        List<Review> list = new ArrayList<Review>();
        String sql = "SELECT id,user_id,title,type,photo,content,location_bit,create_date,create_time," +
                "create_ip FROM review WHERE 1=1";
        if(StringUtils.equals(type, "zdtj")){
            sql += " AND location_bit like '_1________'";
        } else if(StringUtils.equals(type, "zdtjTop")){
            sql += " AND location_bit like '__1_______'";
        } else if(StringUtils.equals(type, "mwgs")){
            sql += " AND location_bit like '___1______'";
        } else if(StringUtils.equals(type, "mwgsTop")){
            sql += " AND location_bit like '____1_____'";
        } else if(StringUtils.equals(type, "bzyc")){
            sql += " AND location_bit like '_____1____'";
        } else if(StringUtils.equals(type, "bzycTop")){
            sql += " AND location_bit like '______1___'";
        } else if(StringUtils.equals(type, "hyzlTop")){
            sql += " AND location_bit like '_______1__'";
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
                String title = rs.getString("title");
                String reviewType = rs.getString("type");
                String photo = rs.getString("photo");
                String content = rs.getString("content");
                String locationBit = rs.getString("location_bit");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                Review review = new Review(id, userId, title, reviewType, photo, content, locationBit, createDate,
                        createTime, createIp);
                list.add(review);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }
}
