package com.servlet;

import com.bean.User;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.login(username, password);

        // 判断登录是否成功
        if (user != null && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            // 登录成功, 将用户名存储在Session中
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // 重定向到新的getData servlet
            response.sendRedirect("getData");
            return; // 添加return确保后续代码不执行
        } else {
            // 采用重定向，跳转到登录失败页面
            response.sendRedirect("failure.jsp?username=" + URLEncoder.encode(username, "utf-8"));
            return; // 添加return确保后续代码不执行
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}