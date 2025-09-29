<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bean.UserInfo" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<h3 style="text-align: center">恭喜，<%= session.getAttribute("username") %>，登录成功！</h3>
<h1 style="text-align: center">用户列表</h1>
<ul style="text-align: center; list-style-type: none; padding: 0;">
    <%
        List<UserInfo> userList = (List<UserInfo>) request.getAttribute("userList");
        if (userList != null) {
            for (UserInfo user : userList) {
                String gender = user.getSex() == 1 ? "男" : "女";
    %>
    <li>
        <%= user.getUsername() %> - <%= user.getAddress() %> - <%= user.getPhone() %> - <%= gender %>
        <%-- 添加删除链接 --%>
        <a href="deleteUser?id=<%= user.getId() %>" style="text-decoration: none;" onclick="return confirm('确定要删除该用户吗？')">删除</a>
    </li>
    <%
            }
        }
    %>
</ul>
</body>
</html>