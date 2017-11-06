<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script type="text/javascript" src="${baseurl}/js/jquery-1.4.4.js"></script>
<style type="text/css">
div {
	margin: 0 auto;
	width: 500px;
	height: 100px;
}
</style>

<title>欢迎您，管理员</title>
</head>
<body>
	<div>
		<h5 style="color: red">${msg}</h5>
		<form name="form1"
			action="${pageContext.request.contextPath }/uploadTecSubmit.action"
			method="post" enctype="multipart/form-data">
			老师名单上传： <input type="file" name="excleFile" />
			<input type="button" value="添加" id="sub" />
		</form>
		<h5 style="color: red">Excle请按下面的上传模板上传，以防数据录入不成功。</h5><br>
		<img src="${baseurl}images/aboutExcle/teacher_excle.png" alt="上传模板" width="220" height="290"/>
	</div>
</body>
<script type="text/javascript">
    $("#sub").click(
        function(){
            var s=document.form1.excleFile.value;
            if(s==""){
                alert("请选择一个excle文件.");
                document.form1.excleFile.focus();
                return;
            }
           // showLoadingWnd('请稍等...');
            document.form1.submit();
        }
    );
</script>
</html>