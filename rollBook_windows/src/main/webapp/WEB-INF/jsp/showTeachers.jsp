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
    <title>查询列表</title>
    <link href='<c:url value="/bootstrap/bootstrap.min.css"/>' rel="stylesheet"/>
    <script type="text/javascript" src="${baseurl}bootstrap/jquery.js"></script>
    <script type="text/javascript" src="${baseurl}bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript">
        function deleteTecs() {

            //将form的action指向删除老师信息的地址
            document.teachersForm.action = "${baseurl}deleteTecs.action";

            //进行form提交
            document.teachersForm.submit();

        }
    </script>
</head>
<body>
<form name="teachersForm" action="${baseurl }conditionSubmit.action" method="post">
    查询条件：
    <table width="100%" border=1 class="table table-bordered table-hover">
        <tr>
            <td>
                按工号查询：
                <input type="type" name="wid"/>
                按姓名查询：
                <input type="type" name="name"/>
            </td>
            <td><input type="submit" value="查询"/>
                <input type="button" value="批量删除" onclick="deleteTecs()"/>
            </td>
        </tr>
    </table>
    教师信息列表：
    <table width="100%" border=1 class="table table-bordered table-hover">
        <tr>
            <td>选择</td>
            <td>工号</td>
            <td>姓名</td>
            <td>密码</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${tlist}" var="tea">
            <tr>
                <td><input type="checkbox" name="id" value="${tea.id}"/></td>
                <td>${tea.wid }</td>
                <td>${tea.name }</td>
                <td>${tea.password }</td>
                <td><a href="${baseurl }/editTea.action?id=${tea.id}">修改</a></td>
            </tr>
        </c:forEach>
    </table>
</form>
<center>
    <c:if test="${empty tlist}">
        <span style="color: red">sorry.没有查询到此教师信息。</span>
    </c:if>
</center>
</body>

</html>