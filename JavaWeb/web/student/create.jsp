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
    <title>Title</title>
    <jsp:include page="../share/header.html" />
</head>
<body>
<jsp:include page="../share/nav.html" />

<div class="container">
    <form action="/createStudent" method="post">
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
            <div class="col-md-10 offset-md-2">
                <input type="submit" class="btn btn-success" value="保存" >
            </div>
        </div>
    </form>
</div>

</body>
</html>
