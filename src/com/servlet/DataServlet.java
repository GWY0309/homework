package com.servlet;

import com.bean.UserInfo;
import com.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/getData")
public class DataServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 检查用户是否登录
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        request.setCharacterEncoding("utf-8");

        UserDao userDao = new UserDao();
        List<UserInfo> userList = userDao.getUserList();

        request.setAttribute("userList", userList);
        // 转发请求到success.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
        dispatcher.forward(request, response);
    }
}