<%--
  Created by IntelliJ IDEA.
  User: 周太宇
  Date: 2017/9/12
  Time: 19:20
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
    <title></title>
    <link href='<c:url value="/bootstrap/bootstrap.min.css"/>' rel="stylesheet"/>
    <script type="text/javascript" src="${baseurl}/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${baseurl}/bootstrap/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            //添加记录中的课程名
            $("#cName").click(
                function () {
                    $.ajax({
                        url: "${baseurl}" + "findClassesByCname.action",
                        type: "POST",
                        dataType: "json",
                        data: {
                            cName: $("#cName").val(),
                        },
                        success: function (data) {
                            var Classes = data;
                            //参数data是后台传回来的数据
                            //做相关的解析处理
                            $('#className').html("");

                            for (var i = 0; i < Classes.length; i++) {

                                $("#className").append(
                                    "<option>" + Classes[i]
                                    + "</option>");
                            }

                        }
                    })
                })
            //开始检索
            $("#sub").click(
                function () {
                    $.ajax({
                        url: "${baseurl}" + "selectEvents.action",
                        type: "POST",
                        dataType: "json",
                        data: {
                            cName: $("#cName").val(),
                            className: $("#className").val(),
                        },
                        success: function (data) {
                            var records = data;
                            //参数data是后台传回来的数据
                            //做相关的解析处理
                            $("#mytable").html("");
                            $("#mytable").append(
                                "<tr>"
                                + "<td>日期</td>"
                                + "<td>学号</td>"
                                + "<td>姓名</td>"
                                + "<td>事件</td>"
                                + "<td>扣分</td>"
                                + "</tr>");
                            for (var i = 0; i < records.length; i++) {
                                $("#mytable").append(
                                    "<tr>"
                                    + "<td>" + records[i].theDate + "</td>"
                                    + "<td>" + records[i].sno + "</td>"
                                    + "<td>" + records[i].name + "</td>"
                                    + "<td>" + records[i].thingName + "</td>"
                                    + "<td>" + records[i].score + "</td>"
                                    + "</tr>");
                            }

                        }
                    })
                })
        })

    </script>
</head>
<body>
<form action="" method="post">
    课程：<select name="cName" id="cName" >
    <option>请选择：</option>
    <c:forEach items="${MyCname}" var="MyCname">
        <option value="${MyCname}">${MyCname}</option>
    </c:forEach>
</select>
    &nbsp; 班级：<select name="className" id="className">
    <option>请选择：</option>
</select>
    <input type="button" value="检索" id="sub"/>
</form>
<div>
    <table border="1" id="mytable" width="100%" class="table table-bordered table-hover"></table>
</div>
</body>

</html>
