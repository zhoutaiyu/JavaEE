<%--
  Created by IntelliJ IDEA.
  User: 周太宇
  Date: 2017/10/12
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h2>Hello World!</h2>
<form action="userServlet" method="post">
    name:<input type="text" name="username">
    password:<input type="text" name="password">
    <input type="submit" value="login">
</form>
</body>
</html>
