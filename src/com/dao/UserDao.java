package com.dao;

import com.bean.User;
import com.bean.UserInfo;
import com.dbutils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    /**
     * 用户登录方法
     * @param username
     * @param password
     * @return 用户对象（非空：登录成功，否则登录失败）
     */
    public User login(String username, String password) {
        User user = null;
        Connection conn = ConnectionManager.getConnection();
        try {
            // **修正这里：确保从 test 表查询登录用户**
            String strSQL = "SELECT * FROM test WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return user;
    }

    /**
     * 获取用户列表方法
     * @return 用户信息列表
     */
    public List<UserInfo> getUserList() {
        List<UserInfo> userList = new ArrayList<>();
        Connection conn = ConnectionManager.getConnection();
        try {
            // **这里保持不变：从 t_list 表获取用户列表**
            String strSQL = "SELECT * FROM t_list";
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(rs.getInt("id"));
                userInfo.setUsername(rs.getString("username"));
                userInfo.setAddress(rs.getString("address"));
                userInfo.setPhone(rs.getString("phone"));
                userInfo.setSex(rs.getInt("sex"));
                userList.add(userInfo);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return userList;
    }

    public boolean deleteUser(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean isDelete = false;
        try {
            // 获取数据库连接
            conn = ConnectionManager.getConnection();
            // 定义SQL字符串，用于删除用户
            String strSQL = "DELETE FROM t_list WHERE id = ?";
            // 创建预备语句对象
            pstmt = conn.prepareStatement(strSQL);
            // 设置SQL语句中的参数值
            pstmt.setInt(1, id);
            // 执行删除操作
            int affectedRows = pstmt.executeUpdate();
            // 如果受影响的行数为1，则表示成功删除了一条记录
            if (affectedRows == 1) {
                isDelete = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            // 关闭数据库连接
            ConnectionManager.closeConnection(conn);
        }
        return isDelete;
    }
}