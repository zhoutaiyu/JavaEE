<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='<c:url value="/bootstrap/bootstrap.min.css"></c:url>'
          rel="stylesheet">
    <script type="text/javascript" src="/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/jquery-1.4.4.js"></script>
    <title>设置扣分分值</title>
</head>
<body>
<font color="red">${msg }</font>
<br>
<center>
    各种加分扣分项：
    <form action="ReduceMarksSubmit.action" method="post">
        您设置的五项成绩所占比例为：<br>
        请注意：如果您想把提问的加分计算在课堂表现上，请勾选课堂表现旁边的单选框，反之请勾选其他成绩的单选框，勾选框的成绩占的比例不会计入原始成绩。
        <br> 课堂出勤：<input type="text"
                         name="proportion.attendance"
                         value="${proportionObject.attendance}">%<br>
        <br> 课堂表现：<input type="text" name="proportion.performance"
                         value="${proportionObject.performance}"/>%
        <c:choose>
        <c:when test="${proportionObject.sradio=='1'}">
            <input type="radio" name="proportion.sradio" value="1" checked="checked"/>
        </c:when>
        <c:otherwise>
            <input type="radio" name="proportion.sradio" value="1"/>
        </c:otherwise>
    </c:choose>
        <br><br>
        作业情况：<input type="text" name="proportion.task"
                    value="${proportionObject.task}">%<br>
        <br>课内实验：<input
            type="text" name="proportion.experiment"
            value="${proportionObject.experiment}">%<br>
        <br>
        其他成绩：<input type="text" name="proportion.other"
                    value="${proportionObject.other}">%
        <c:choose>
            <c:when test="${proportionObject.sradio=='2'}">
                <input type="radio" name="proportion.sradio" value="2" checked="checked"/>
            </c:when>
            <c:otherwise>
                <input type="radio" name="proportion.sradio" value="2"/>
            </c:otherwise>
        </c:choose> <br><br> 扣分项：
        缺勤：<input type="text" name="score.absent"
                  value="${scoreObject.absent}" size="1">分/每次&nbsp;&nbsp;早退：<input
            type="text" name="score.early" value="${scoreObject.early}" size="1"/>分/每次&nbsp;&nbsp;
        迟到：<input type="text" name="score.late" value="${scoreObject.late}"
                  size="1"/>分/每次&nbsp;&nbsp; 玩手机：<input type="text"
                                                        name="score.play" value="${scoreObject.play}" size="1"/>分/每次&nbsp;&nbsp;
        作业差：<input type="text" name="score.assignment"
                   value="${scoreObject.assignment}" size="1"/>分/每次&nbsp;&nbsp;课内实验差：<input
            type="text" name="score.experiment"
            value="${scoreObject.experiment}" size="1"/>分/每次&nbsp;&nbsp;<br>
        <br>加分项：踊跃答问：<input type="text"
                            name="score.quiz" value="${scoreObject.quiz}" size="1"/>分/每次&nbsp;&nbsp;<br>
        <br> <input type="submit" value="提交">
    </form>
</center>
</body>
</html>