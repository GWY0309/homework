package com.dao;

import com.bean.User;
import com.dbutils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {
    /**
     * 用户登录方法
     * @param username
     * @param password
     * @return 用户对象（非空：登录成功，否则登录失败）
     */
    public User login(String username, String password) {
        // 声明用户对象
        User user = null;

        // 1.获取数据库连接
        Connection conn = ConnectionManager.getConnection();
        try {
            // 2.定义SQL字符串
            String strSQL = "SELECT * FROM test WHERE username = ? AND password = ?";
            // 3.创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 4.设置占位符
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // 5.执行查询，返回结果集
            ResultSet rs = pstmt.executeQuery();
            // 判断结果集是否为空
            if (rs.next()) {
                // 创建用户对象
                user = new User();
                // 利用当前记录字段值来设置用户对象属性
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            // 关闭数据库连接
            ConnectionManager.closeConnection(conn);
        }

        // 返回用户对象
        return user;
    }
}

