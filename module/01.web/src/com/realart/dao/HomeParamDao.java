package com.realart.dao;

import com.realart.entities.URLTitleName;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 启动参数实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-31 08:41
 */
public class HomeParamDao {
    /**
     * 查询所有启动参数
     *
     * @return
     * @throws Exception
     */
    public static List<URLTitleName> queryAllysjParams(int typeno) throws Exception {
        List<URLTitleName> list = new ArrayList<URLTitleName>();
        String table="topysjparam";
        switch (typeno){
            case 0:
                table="topysjparam";
                break;
            case 1:
                table="friendsiteparam";
                break;
            case 4:
                table="itemlist";
                break;
            case 5:
                table="itemparam";
                break;
            default:break;
        }
        String sql= "SELECT * FROM "+table+" order by id";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id=rs.getInt("id");
                String name = rs.getString("name");
                String value = rs.getString("url");
                String info = rs.getString("img");
                int type=rs.getInt("type");
                boolean dis=rs.getBoolean("dis");
                URLTitleName param = new URLTitleName(id,name, value, info,dis,type);
                list.add(param);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 更新启动参数
     *
     * @param param
     * @throws Exception
     */
    public static void updateysjParam(URLTitleName param) throws Exception {
    	String table="topysjparam";
        switch (param.getType()){
        case 0:
        table="topysjparam";
        break;
        case 1:
        	 table="friendsiteparam";
             break;
        case 4:
         	 table="itemlist";
              break;
        case 5:
       	 table="itemparam";
            break;
        default:break;
        }
        String sql = "update "+table+" set name='" + param.getName() +
        		"',url='" + param.getUrl() + "',img='"+param.getImg()+"',type="+param.getType()+
        		",dis="+(param.getDis()?1:0)+" where id=" + param.getID();
        DB.executeUpdate(sql);
    }
    
    public static void insertysjParam(URLTitleName param) throws Exception {
    	String table="topysjparam";
        switch (param.getType()){
        case 0:
        table="topysjparam";
        break;
        case 1:
        	 table="friendsiteparam";
             break;
        case 4:
         	 table="itemlist";
              break;
        case 5:
       	 table="itemparam";
            break;
        default:break;
        }
        String sql = "insert into "+table+" (id,name,url,img,type,dis) values(null,'" + param.getName() +
        		"','" + param.getUrl() + "','"+param.getImg()+"',"+param.getType()+
        		","+(param.getDis()?1:0)+")";
        DB.executeUpdate(sql);
    }
    
    public static void deleteysjParam(int id,int typeno) throws Exception {
    	String table="topysjparam";
        switch (typeno){
        case 0:
        table="topysjparam";
        break;
        case 1:
        	 table="friendsiteparam";
             break;
        case 4:
         	 table="itemlist";
              break;
        case 5:
       	 table="itemparam";
            break;
        default:break;
        }
        String sql = "delete from "+table+" where id="
        		+id;
        DB.executeUpdate(sql);
    }
}
