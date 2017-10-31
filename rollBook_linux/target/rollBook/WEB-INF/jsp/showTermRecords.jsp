<%--
  Created by IntelliJ IDEA.
  User: 周太宇
  Date: 2017/9/24
  Time: 15:38
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
    <link href='<c:url value="/bootstrap/bootstrap.min.css"/>' rel="stylesheet"/>
    <script type="text/javascript" src="${baseurl}bootstrap/jquery.js"></script>
    <script type="text/javascript" src="${baseurl}bootstrap/bootstrap.min.js"></script>
    <title>本学期扣/加分</title>
</head>
<body>
<center>
本学期扣/加分情况
<table border="1" width="100%" class="table table-bordered table-hover">
    <tr>
        <td>日期</td>
        <td>课程</td>
        <td>事件</td>
        <td>扣分</td>
    </tr>
    <c:forEach items="${recordVoList}" var="record">
        <tr>
            <td>${record.theDate}</td>
            <td>${record.cname}</td>
            <td>${record.thingName}</td>
            <td>${record.score}</td>
        </tr>
    </c:forEach>
</table>
    <c:if test="${empty recordVoList}">
        <span style="color: red">恭喜小生没有扣分情况。</span>
    </c:if>
</center>
</body>
</html>
