package com.ydgk.eight.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 游斌
 * @create 2020-02-28  20:04
 */
public class JdbcUtil {
    private static ComboPooledDataSource ds;
    static {
        //创建连接池对象并设置属性
        ds = new ComboPooledDataSource();
        //读取文件中的值
        Properties properties= new Properties();
        try {
            properties.load(JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            //properties.load(JdbcUtil.class.getResourceAsStream("jdbc.properties"));
            ds.setDriverClass(properties.getProperty("driverName"));
            ds.setPassword(properties.getProperty("password"));
            ds.setJdbcUrl(properties.getProperty("url"));
            ds.setUser(properties.getProperty("root"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        System.out.println(connection);
    }
}
