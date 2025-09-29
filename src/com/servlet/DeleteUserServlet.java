package com.servlet;

import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteUserServlet", urlPatterns = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
            UserDao userDao = new UserDao();
            boolean isDelete = userDao.deleteUser(id);

            if (isDelete) {
                // 删除成功后重定向到 /getData 来刷新列表
                resp.sendRedirect(req.getContextPath() + "/getData");
                return;
            } else {
                // 设置错误消息并转发回 success.jsp
                req.setAttribute("error", "删除失败");
                req.getRequestDispatcher("/getData").forward(req, resp);
                return;
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "无效的用户ID");
            req.getRequestDispatcher("/getData").forward(req, resp);
            return;
        }
    }
}