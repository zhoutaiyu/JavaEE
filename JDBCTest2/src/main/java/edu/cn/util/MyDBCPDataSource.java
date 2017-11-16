package edu.cn.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by 周太宇 on 2017/11/16.
 */
public class MyDBCPDataSource {
    // 单例数据源
    public static BasicDataSource DBCPDATASOURCE = getDBCPDataSource();

    private static BasicDataSource getDBCPDataSource() {
        // 加载配置文件
        Properties properties = new Properties();
        try {
            properties.load(MyDBCPDataSource.class.getResourceAsStream("/dbcp.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BasicDataSource dataSource = new BasicDataSource();
        // 获取配置
        if (properties.isEmpty()) {
            return null;
        }
        // 基础配置
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        dataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
        //设置初始大小
        dataSource.setInitialSize(5);
        //最大连接数
        dataSource.setMaxTotal(10);
        //最大空闲数
        dataSource.setMaxIdle(2);
        return dataSource;
    }
}
