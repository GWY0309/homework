<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bean.UserInfo" %>
<html>
<head>
    <title>登录成功</title>
    <script>
        // 显示添加用户表单
        function showAddUserForm() {
            document.getElementById("addUserFormContainer").style.display = "block";
        }

        // 隐藏添加用户表单
        function hideAddUserForm() {
            document.getElementById("addUserFormContainer").style.display = "none";
        }

        // 提交添加用户表单前的校验
        function submitAddUserForm() {
            var form = document.forms["addUserForm"];
            if (form["username"].value === "" || form["address"].value === "" || form["phone"].value === "") {
                alert("所有字段都是必填项!");
                return false;
            }
            // 提交表单
            form.submit();
            return true;
        }
    </script>
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
        <a href="deleteUser?id=<%= user.getId() %>" style="text-decoration: none;" onclick="return confirm('确定要删除该用户吗？')">删除</a>
    </li>
    <%
            }
        }
    %>
</ul>

<div style="text-align: center; margin-top: 20px;">
    <button type="button" onclick="showAddUserForm()">添加用户</button>
</div>

<div id="addUserFormContainer" style="display:none; margin: 20px auto; width: 300px;">
    <form name="addUserForm" action="addUser" method="post" onsubmit="return submitAddUserForm()">
        <table border="0" cellpadding="10" style="margin: 0 auto;">
            <tr>
                <td align="right">用户名:</td>
                <td><input type="text" name="username" required/></td>
            </tr>
            <tr>
                <td align="right">地址:</td>
                <td><input type="text" name="address" required/></td>
            </tr>
            <tr>
                <td align="right">电话:</td>
                <td><input type="text" name="phone" required/></td>
            </tr>
            <tr>
                <td align="right">性别:</td>
                <td>
                    <select name="sex" required>
                        <option value="1">男</option>
                        <option value="0">女</option>
                    </select>
                </td>
            </tr>
            <tr align="center">
                <td colspan="2">
                    <input type="submit" value="添加"/>
                    <button type="button" onclick="hideAddUserForm()">取消</button>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>