package com.servlet;


import com.bean.User;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求对象字符编码格式
        request.setCharacterEncoding("utf-8");

        // 获取登录表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        System.out.println("username=>>>>>>>>>>>>>>>>>"+username);
//        System.out.println("password=>>>>>>>>>>>>>>>>>"+password);

        // 创建用户数据访问对象
        UserDao userDao = new UserDao();
        // 调用登录方法，返回用户对象
        User user = userDao.login(username, password);
//        System.out.println("sql_username=>>>>>>>>>>>>>>>>>"+user.getUsername());
//        System.out.println("sql_password=>>>>>>>>>>>>>>>>>"+user.getPassword());

        // 判断登录是否成功
        if (user != null && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            // 采用重定向，跳转到登录成功页面
            //response.sendRedirect("success.jsp?username=" + URLEncoder.encode(username, "utf-8"));
            response.sendRedirect("success.jsp?username=" + username);
        } else {
            // 采用重定向，跳转到登录失败页面
            response.sendRedirect("failure.jsp?username=" + URLEncoder.encode(username, "utf-8"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}



