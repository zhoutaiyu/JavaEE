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
    <title>错误信息</title>
    <link href='<c:url value="/bootstrap/bootstrap.min.css"/>' rel="stylesheet"/>
    <script type="text/javascript" src="${baseurl}/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${baseurl}/js/jquery-1.4.4.js"></script>
    <script type="text/javascript" src="${baseurl}/bootstrap/jquery.js"></script>
</head>
<body>
<c:if test="${message!='未知错误'}">
    ${message}
    <a href="${baseurl}first.action">
        <button type="button" class="btn btn-default">返回</button>
    </a>
</c:if>

<c:if test="${message=='未知错误'}">${message},sorry,您打开的页面出现问题了。</c:if>
</body>
</html>