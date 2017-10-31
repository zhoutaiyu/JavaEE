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
    <title><title>点名册视图</title></title>
    <link href='<c:url value="/bootstrap/bootstrap.min.css"/>' rel="stylesheet"/>
    <script type="text/javascript" src="${baseurl}bootstrap/jquery.js"></script>
    <script type="text/javascript" src="${baseurl}bootstrap/bootstrap.min.js"></script>
    <style type="text/css">
        #dropfloat {
            float: left
        }

        .avg {
            color: red;
            position: absolute;
            right: 50px;
            top: 159px;
        }
    </style>
</head>
<body>

<div>
    <h5 align="center" class="thick" style="font-size: 14px;">添加学生违纪/加分记录</h5>
    <form action="${pageContext.request.contextPath }/addRecord.action"
          method="post" class="form-inline">
        <div class="form-group">
            <label>课程：</label>
            <select name="cname" id="cname" class="form-control input-sm">
                <option>请选择：</option>
                <c:forEach items="${MyCname}" var="MyCname">
                    <option value="${MyCname}">${MyCname}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>班级：</label>
            <select name="class_Name" id="class_Name" class="form-control input-sm">
                <option>请选择：</option>
            </select>
        </div>
        <div class="form-group">
            <label>学号、姓名：</label>
            <select name="sno" id="sno" class="form-control input-sm">
                <option>请选择：</option>
            </select>
        </div>
        <div class="form-group">
            <label>日期：</label>
            <input type="date" name="happen_time" class="form-control input-sm"/>
        </div>
        <div class="form-group">
            <label>周次：</label>
            <select
                    name="rweek" class="form-control input-sm">
                <option disabled="disabled">请选择：</option>
                <c:forEach var='i' begin='1' end='18' step='1'>
                    <option value='${i}'>第${i}周</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>星期：</label>
            <select name="rsection" class="form-control input-sm">
                <option disabled="disabled">请选择：</option>
                <option value="1">星期一</option>
                <option value="2">星期二</option>
                <option value="3">星期三</option>
                <option value="4">星期四</option>
                <option value="5">星期五</option>
                <option value="6">星期六</option>
                <option value="7">星期天</option>
            </select>
        </div>
        <div class="form-group">
            <label>节次：</label>
            <select name="rday" class="form-control input-sm">
                <option disabled="disabled">请选择：</option>
                <option value="1">1-2节</option>
                <option value="2">3-4节</option>
                <option value="3">5-6节</option>
                <option value="4">7-8节</option>
                <option value="5">9-10节</option>
            </select>
        </div>
        <div class="form-group"  style=" margin-top: 12px;">
            <label>事件：</label>
            <select name="thing" class="form-control input-sm" >
                <option disabled="disabled">请选择：</option>
                <option disabled="disabled">扣分事件</option>
                <option value="1">缺勤</option>
                <option value="2">早退</option>
                <option value="3">迟到</option>
                <option value="4">玩手机</option>
                <option value="6">课后作业差</option>
                <option value="7">课内实验差</option>
                <option disabled="disabled">扣分事件</option>
                <option value="5">提问</option>
            </select>
        </div>

        <input type="submit" value="添加记录" class="btn btn-default" style="margin-top: 10px;margin-left: 34px;"/>
    </form>
    <br> <font color="red">${msg }</font>
</div>
<div>
    <form class="form-inline">
        <h5 align="center" class="thick">点名册：</h5>
        <div class="form-group">
            <label>课程：</label>
            <select name="cName" id="cName" class="form-control input-sm">
                <option>请选择：</option>
                <c:forEach items="${MyCname}" var="MyCname">
                    <option value="${MyCname}">${MyCname}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>班级：</label><h5 id="avg" class="avg"></h5>
            <select name="className" id="className" class="form-control input-sm">
                <option>请选择：</option>
            </select>
        </div>
    </form>
</div>
<br>
<div>
    <table id="roll_table" border='1' width="100%" class="table table-bordered table-hover"></table>
</div>

<script type="text/javascript">
    $(function () {
        $("#class_Name").click(
            function () {
                $.ajax({
                    url: "${baseurl}" + "findPics.action",
                    type: "POST",
                    dataType: "json",
                    data: {
                        class_Name: $("#class_Name").val(),
                    },
                    success: function (data) {
                        var students = data;
                        //参数data是后台传回来的数据
                        //做相关的解析处理

                        //先把之前的学号移除，再加学号下拉列表
                        $('#sno').html("");

                        for (var i = 0; i < students.length; i++) {

                            $("#sno").append(
                                "<option>" + students[i].sno + ""
                                + students[i].name
                                + "</option>");
                        }

                    }
                })
            })
        //
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
        //添加记录中的课程名
        $("#cname").click(
            function () {
                $.ajax({
                    url: "${baseurl}" + "findClassesByCname.action",
                    type: "POST",
                    dataType: "json",
                    data: {
                        cName: $("#cname").val(),
                    },
                    success: function (data) {
                        var Classes = data;
                        //参数data是后台传回来的数据
                        //做相关的解析处理
                        $('#class_Name').html("");

                        for (var i = 0; i < Classes.length; i++) {

                            $("#class_Name").append(
                                "<option>" + Classes[i]
                                + "</option>");
                        }

                    }
                })
            })
        //触发显示点名册的班级名称
        $("#className")
            .click(
                function () {

                    $
                        .ajax({
                            url: "${baseurl}"
                            + "findRoll.action",
                            type: "POST",
                            dataType: "json",
                            data: {
                                cName: $("#cName").val(),
                                className: $("#className")
                                    .val(),
                            },
                            success: function (data) {
                                var studentVo = data;
                                var avg = 0;
                                var len = studentVo.length;
                                //参数data是后台传回来的数据
                                //做相关的解析处理
                                //先把之前的点名册移除
                                $('#roll_table').html("");
                                $("#roll_table")
                                    .append(
                                        "<tr>"
                                        + "<td>序号</td>"
                                        + "<td>学号</td>"
                                        + "<td>姓名</td>"
                                        + "<td>性别</td>"
                                        + "<td>行政班级</td>"
                                        //+ "<td>"
                                        //+ "<table>"
                                        //+ "<tr><td>日期</td></tr>"
                                        //+ "<tr>"
                                        //+ "<c:forEach var='i' begin='1' end='18' step='1'>"
                                        //+ "<td></td>"
                                        //+ "</c:forEach>"
                                        //+ "</tr>"
                                        //+ "</table>"
                                        //+ "</td>"
                                        + "<td>课堂出勤</td>"
                                        + "<td>课堂表现</td>"
                                        + "<td>作业</td>"
                                        + "<td>课内实验</td>"
                                        + "<td>其他成绩</td>"
                                        + "<td>合计</td>"
                                        + "</tr>")
                                for (var i = 0; i < studentVo.length; i++) {
                                    avg = studentVo[i].sc.total + avg;
                                    if(studentVo[i].sc.total>=90){
                                        $("#roll_table")
                                            .append(
                                                "<tr>"
                                                + "<td>"
                                                + studentVo[i].id
                                                + "</td>"
                                                //+ "<td id="+"'sno'"+">"
                                                + "<td>"
                                                + studentVo[i].sno
                                                + "</td>"
                                                + "<td>"
                                                //+ "<td id="+"'sname'"+">"
                                                + studentVo[i].name
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].sex
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].className
                                                + "</td>"
                                                //注释日期区
                                                //+ "<td>"
                                                //+ "</td>"
                                                + "<td>"
                                                + studentVo[i].sc.attendance
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].sc.performance
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].sc.task
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].sc.experiment
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].sc.other
                                                + "</td>"
                                                + "<td style='color: red'>"
                                                + studentVo[i].sc.total
                                                + "</td>"
                                                + "</tr>");
                                    }
                                    if(studentVo[i].sc.total>=70&&studentVo[i].sc.total<90){
                                        $("#roll_table")
                                            .append(
                                                "<tr>"
                                                + "<td>"
                                                + studentVo[i].id
                                                + "</td>"
                                                //+ "<td id="+"'sno'"+">"
                                                + "<td>"
                                                + studentVo[i].sno
                                                + "</td>"
                                                + "<td>"
                                                //+ "<td id="+"'sname'"+">"
                                                + studentVo[i].name
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].sex
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].className
                                                + "</td>"
                                                //注释日期区
                                                //+ "<td>"
                                                //+ "</td>"
                                                + "<td>"
                                                + studentVo[i].sc.attendance
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].sc.performance
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].sc.task
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].sc.experiment
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].sc.other
                                                + "</td>"
                                                + "<td>"
                                                + studentVo[i].sc.total
                                                + "</td>"
                                                + "</tr>");
                                    }
                                        if(studentVo[i].sc.total<70){
                                            $("#roll_table")
                                                .append(
                                                    "<tr>"
                                                    + "<td>"
                                                    + studentVo[i].id
                                                    + "</td>"
                                                    //+ "<td id="+"'sno'"+">"
                                                    + "<td>"
                                                    + studentVo[i].sno
                                                    + "</td>"
                                                    + "<td>"
                                                    //+ "<td id="+"'sname'"+">"
                                                    + studentVo[i].name
                                                    + "</td>"
                                                    + "<td>"
                                                    + studentVo[i].sex
                                                    + "</td>"
                                                    + "<td>"
                                                    + studentVo[i].className
                                                    + "</td>"
                                                    //注释日期区
                                                    //+ "<td>"
                                                    //+ "</td>"
                                                    + "<td>"
                                                    + studentVo[i].sc.attendance
                                                    + "</td>"
                                                    + "<td>"
                                                    + studentVo[i].sc.performance
                                                    + "</td>"
                                                    + "<td>"
                                                    + studentVo[i].sc.task
                                                    + "</td>"
                                                    + "<td>"
                                                    + studentVo[i].sc.experiment
                                                    + "</td>"
                                                    + "<td>"
                                                    + studentVo[i].sc.other
                                                    + "</td>"
                                                    + "<td style='color: blue'>"
                                                    + studentVo[i].sc.total
                                                    + "</td>"
                                                    + "</tr>");
                                    }

                                }
                                avg = (avg / len).toFixed(1);
                                $('#avg').html("");
                                $("#avg")
                                    .append("平均分为" + avg);
                            }
                        })
                })
    })
</script>
</body>
</html>