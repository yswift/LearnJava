<%--
  Created by IntelliJ IDEA.
  User: yswif
  Date: 2019-4-22
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>新建学生</title>
    <%--包括html头信息，主要是css和js文件--%>
    <jsp:include page="../share/header.html" />
</head>
<body>
<%--显示导航条--%>
<jsp:include page="../share/nav.html" />
<br />
<%--学生信息表单--%>
<div class="container">

    <h3>添加学生</h3>
<form action="/student/create" method="post" enctype="multipart/form-data">
    <div class="form-group row">
        <label for="No" class="col-md-2 col-form-label">学号</label>
        <div class="col-md-10">
            <input type="text" class="form-control" id="No" name="No" placeholder="12个字符">
        </div>
    </div>
    <div class="form-group row">
        <label for="Name" class="col-md-2 col-form-label">姓名</label>
        <div class="col-md-10">
            <input type="text" class="form-control" id="Name" name="Name" placeholder="姓名">
        </div>
    </div>
    <div class="form-group row">
        <label for="Age" class="col-md-2 col-form-label">年龄</label>
        <div class="col-md-10">
            <input type="number" class="form-control" id="Age" name="Age" >
        </div>
    </div>
    <div class="form-group row">
        <label for="Birthday" class="col-md-2 col-form-label">出生日期</label>
        <div class="col-md-10">
            <input type="Date" class="form-control" id="Birthday" name="Birthday" >
        </div>
    </div>
    <div class="form-group row">
        <label for="Photo" class="col-md-2 col-form-label">照片</label>
        <div class="col-md-10">
            <input type="file" class="form-control" id="Photo" name="Photo" >
        </div>
    </div>
    <div class="form-group row">
        <div class="col-md-10 offset-md-2">
            <input type="submit" class="btn btn-success" value="保存" >
        </div>
    </div>
</form>
</div>

</body>
</html>
