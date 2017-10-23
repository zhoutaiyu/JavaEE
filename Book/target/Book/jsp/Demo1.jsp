<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>easyUi01使用和说明</title>
    <!-- 引入css文件，不限制顺序 -->
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">

    <!-- 引入js文件，限制顺序,先加jquery再加jquery.easyui -->
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>

</head>
<body>
<!--
    第一：写一个普通的div标签
    第二：提倡为div标签取一个id属性，将来用jquery好定位
    第三：为普通的div标签添加easyui组件的样式
        所有的easyui组件的样式均按如下格式设置：easyui-组件名
    第四：如果要用easyui组件自身的属性时，必须在普通标签上书写data-options属性名，
        内容为，key:value,key:value,如果value时string类型加引号，外面双引号，
        则里面单引号
    注意：要在普通标签中书写data-options属性的前提是:在普通标签上加clsss="easyui-组件名"
 -->
<table id="dg" title="My books" class="easyui-datagrid" style="width:550px;height:250px"
       url="book_queryAll.action"
       toolbar="#toolbar"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="isbn" width="50">isbn</th>
        <th field="title" width="50">title</th>
        <th field="type" width="50">type</th>
        <th field="price" width="50">price</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
</div>
</body>
</html>