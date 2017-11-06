<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='<c:url value="/bootstrap/bootstrap.min.css"/>' rel="stylesheet"/>
    <script type="text/javascript" src="${baseurl}/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${baseurl}/js/jquery-1.4.4.js"></script>
    <style type="text/css">
        #dropfloat {
            float: left
        }
        /*去掉li之前的点*/
        li
        {list-style:none;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#class_Name")
                .click(
                    function () {
                        $
                            .ajax({
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
                                    //去掉之前班级的图片
                                    $('#ulId').html("");
                                    //先把之前的学号移除，再加学号下拉列表
                                    $('#stu_sno').html("");

                                    for (var i = 0; i < students.length; i++) {
                                        $("#ulId")
                                            .append(
                                                "<li id='dropfloat'>"
                                                + "<img src='/rollResourse/images/"+students[i].image+"' data-src='holder.js/200x240' class='img-thumbnail img-rounded' alt='200x240' style='width: 200px; height: 240px'; data-holder-rendered='true'/>"
                                                + "<br />"
                                                + students[i].sno
                                                + " "
                                                + students[i].name
                                                + "</li>");

                                        $("#stu_sno")
                                            .append(
                                                "<option>"
                                                + students[i].sno
                                                + "</option>");
                                    }

                                }
                            })
                    })
            //点击学号，查找学生照片
            $("#stu_sno")
                .click(
                    function () {

                        $
                            .ajax({
                                url: "${baseurl}" + "findPic.action",
                                type: "POST",
                                dataType: "json",
                                data: {
                                    stu_sno: $("#stu_sno").val(),
                                },
                                success: function (data) {
                                    //参数data是后台传回来的数据
                                    //如果之前div（ulId）中存在照片，则先移除

                                    $('#ulId').html("");

                                    $("#ulId")
                                        .append(
                                            "<li>"
                                            //+ "<img src='/pic/"+data.image+"' width=190 height=240 />"
                                            + "<img src='/rollResourse/images/"+data.image+"' data-src='holder.js/200x240' class='img-thumbnail' alt='200x240' style='width: 200px; height: 240px'; data-holder-rendered='true'/>"
                                            + "<br />"
                                            + data.sno
                                            + " "
                                            + data.name
                                            + "</li>");

                                }

                            })
                    })
        })
    </script>
    <title>图片视图</title>
</head>
<body>
<!-- 搜索该班级下的学生照片 -->
<div>
    <form action="" method="post">
        班级：<select name="class_Name" id="class_Name">
        <option>请选择：</option>
        <c:forEach items="${MyClass}" var="class_name">
            <option value="${class_name}">${class_name}</option>
        </c:forEach>

    </select><br> 学号：<select name="stu_sno" id="stu_sno">
        <option>请选择：</option>
    </select><br>
    </form>
</div>
<div id="showImages">
    <ul id="ulId"></ul>
</div>
<!--  -->
</body>
</html>