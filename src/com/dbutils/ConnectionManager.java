package com.dbutils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
    private static final String DRIVER = "com.mysql.jdbc.Driver"; // 数据库驱动程序
    private static final String URL = "jdbc:mysql://localhost:3306/t_user?useUnicode=true&characterEncoding=utf8"; // 数据库统一资源标识符
    private static final String USER = "root"; // 数据库用户
    private static final String PASSWORD = "123456"; // 数据库密码



    //私有化构造方法，拒绝实例化
    private ConnectionManager() {
    }

    /**
     * 获取数据库连接静态方法
     *
     * @return 数据库连接对象
     */
    public static Connection getConnection() {
        // 定义数据库连接
        Connection conn = null;

        try {
            // 安装数据库驱动程序
            Class.forName(DRIVER);
            // 获取数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 返回数据库连接
        return conn;
    }

    /**
     * 关闭数据连接静态方法
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        // 判断数据库连接是否非空
        if (conn != null) {
            try {
                // 判断连接是否未关闭
                if (!conn.isClosed()) {
                    // 关闭数据库连接
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 主方法：测试两个静态方法
     *
     * @param args
     */
    public static void main(String[] args) {
        // 获取数据库连接
        Connection conn = getConnection();
        // 判断数据库连接是否成功
        if (conn != null) {
            JOptionPane.showMessageDialog(null, "恭喜，数据库连接成功！");
        } else {
            JOptionPane.showMessageDialog(null, "遗憾，数据库连接失败！");
        }
        // 关闭数据库连接
        closeConnection(conn);
    }
}
