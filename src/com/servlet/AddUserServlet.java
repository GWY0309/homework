package com.servlet;

import com.bean.UserInfo;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddUserServlet", urlPatterns = "/addUser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        try {
            String username = req.getParameter("username");
            String address = req.getParameter("address");
            String phone = req.getParameter("phone");
            String sex = req.getParameter("sex");

            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(username);
            userInfo.setAddress(address);
            userInfo.setPhone(phone);
            userInfo.setSex(Integer.parseInt(sex));

            UserDao userDao = new UserDao();
            boolean isAdd = userDao.addUser(userInfo);

            if (isAdd) {
                // 添加成功后重定向到 /getData 来刷新列表
                resp.sendRedirect(req.getContextPath() + "/getData");
            } else {
                // 设置错误消息并转发
                req.setAttribute("error", "添加用户失败");
                req.getRequestDispatcher("/getData").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 如果有异常，也转发到列表页并设置错误消息
            req.setAttribute("error", "添加用户时发生错误");
            req.getRequestDispatcher("/getData").forward(req, resp);
        }
    }
}