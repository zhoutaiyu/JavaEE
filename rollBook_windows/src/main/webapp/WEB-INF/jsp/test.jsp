<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../../js/jquery-1.4.4.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form>

<table>

<tr>
    <td align="center"><input id="t1" type="text" size="5" msg="姓名" /> </td>
    <td align="center"><input id="t2" type="text" size="5" msg="性别" /></td>
    <td align="center"><input id="t3" type="text" size="5" msg="年龄" /></td>
    <td align="center"><input id="t4" type="text" size="5" msg="地址" /></td>
    <td align="center"><input id="t5" type="text" size="5" msg="邮箱" /></td>
    <td align="center"><input id="t6" type="text" size="5" msg="省份" /></td>

  </tr>

 <tr>
    <td align="center"><input type="submit"  onclick="return checkNull()" /></td>

  </tr>

</table>

</form>

<!--js部分-------------------------->

<!--<script type="text/javascript">
function checkNull()
{
     var num=0;
     var str="";
     $("input[type$='text']").each(function(n){
          if($(this).val()=="")
          {
               num++;
               str+="?"+$(this).attr("msg")+"不能为空！\r\n";
          }
     });
     if(num>0)
     {
          alert(str);
          return false;
     }
     else
     {
          return true;
     }
}

</script>-->
</body>
</html>