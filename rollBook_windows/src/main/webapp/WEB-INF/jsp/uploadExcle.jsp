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
    <script type="text/javascript" src="${baseurl}bootstrap/jquery.js"></script>
    <script type="text/javascript" src="${baseurl}bootstrap/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            margin: 0 auto;
        }
    </style>
    <title>添加课程</title>
</head>
<body>
<div align="center">
    <form action="${pageContext.request.contextPath }/uploadExcleSubmit.action"
          method="post" enctype="multipart/form-data">
        <h5 align="center" class="thick">添加课程</h5>
        <div class="form-group col-xs-2">
            <label>课程名称：</label>
            <input type="text" name="course_name" class="form-control input-sm"/>
        </div>
        <div class="form-group col-xs-2">
            <label>填写班级名称：</label>
            <input type="text" name="class_name" class="form-control input-sm" placeholder="2015级软件工程（1）班"/>
        </div>
        <div class="form-group col-xs-2">
            <label>课程开始时间：</label>
            <input type="date" name="beginDate" class="form-control input-sm"/>
        </div>
        <div class="form-group col-xs-2">
            <label>课程结束时间：</label>
            <input type="date" name="endDate" class="form-control input-sm"/>
        </div>
        <div class="form-group col-xs-2">
            <label>班级点名册上传：</label>
            <input type="file" name="excleFile" style="/* margin-right: 50px; */padding-left: 26px;"/>
        </div>
        <div class="form-group col-xs-2" style="padding-top: 23px;">
            <input type="submit" value="添加课程" class="btn btn-default input-sm"/>
        </div>
    </form>
    <div style="height: 100px;"><font color="red">${msg }</font></div>
    <h5 style="color: red" align="center">Excle请按下面的上传模板上传，以防数据录入不成功。</h5><br>
    <img src="${baseurl}images/aboutExcle/student_excle.png" alt="上传模板" width="620" height="188"/>
</div>
</body>
</html>