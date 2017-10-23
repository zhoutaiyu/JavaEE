package edu.cn.dao;

/**
 * Created by 周太宇 on 2017/10/22.
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 负责关闭数据库相关资源的类
 */
public class CloseResources {
    /**
     * 依次关闭资源，先申请的资源后关闭，由于后面申请的资源依赖Connection
     * @param con
     * @param statement
     * @param rs
     */
    public static void close(Connection con, Statement statement, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
