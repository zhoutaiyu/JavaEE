package edu.cn.dao;

/**
 * Created by 周太宇 on 2017/10/22.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 负责获取连接的类
 */
public class LinkConnection {
    private final static String DRIVEFORNAME = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/book";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "1234";

    /**
     * 步骤：
     * 1.加载驱动
     * 2.DriverManager.getConnection获得驱动
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            //1.加载驱动
            Class.forName(DRIVEFORNAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载驱动出错了！");
        }
        try {
            //2.DriverManager.getConnection获得驱动
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("请检查你的url、账号、密码是否正确！");
        }
        return null;
    }

}
