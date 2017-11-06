<%--
  Created by IntelliJ IDEA.
  User: 周太宇
  Date: 2017/9/24
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/tag.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改教师信息</title>
    <link href='<c:url value="/bootstrap/bootstrap.min.css"/>' rel="stylesheet"/>
    <script type="text/javascript" src="${baseurl}bootstrap/jquery.js"></script>
    <script type="text/javascript" src="${baseurl}bootstrap/bootstrap.min.js"></script>
</head>
<body>
<center>
    <font style="color: red">${msg}</font>
    <form action="${baseurl}editTeaSubmit.action" method="post">
        <table border="1" width="100%" class="table table-bordered table-hover">
            <tr>
                <td>序号</td>
                <td>工号</td>
                <td>姓名</td>
                <td>密码</td>
                <td>操作</td>
            </tr>
            <tr>
                <td>${tea.id}<input type="hidden" name="id" value='${tea.id}' size="0"/></td>
                <td>${tea.wid}</td>
                <td>${tea.name}</td>
                <td><input type="text" name="password" value='${tea.password}' size="1"/></td>
                <td><input type="submit" value="确定修改"></td>
            </tr>
        </table>
    </form>
    <div><a href="${baseurl }selectTea.action">返回全部教师信息页面</a></div>
</center>
</body>
</html>
