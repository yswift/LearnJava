<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>编辑学生信息</title>
    <%--包括html头信息，主要是css和js文件--%>
    <jsp:include page="../share/header.jsp"/>
</head>
<body>
<%--显示导航条--%>
<jsp:include page="../share/nav.jsp"/>
<br/>
<%--学生信息表单--%>
<div class="container">
    <h3>编辑学生信息</h3>
    <form method="post" enctype="multipart/form-data">
        <div class="form-group row">
            <label for="Id" class="col-md-2 col-form-label">Id</label>
            <div class="col-md-10">
                <input type="text" readonly class="form-control" id="Id" name="Id" value="${student.id}">
            </div>
        </div>
        <div class="form-group row">
            <label for="No" class="col-md-2 col-form-label">学号</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="No" name="No" placeholder="12个字符"
                       pattern="^20\d{10}$" required
                       value="${student.no}">
            </div>
        </div>
        <div class="form-group row">
            <label for="Name" class="col-md-2 col-form-label">姓名</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="Name" name="Name" placeholder="姓名"
                       required pattern=".{2,}"
                       value="${student.name}">
            </div>
        </div>
        <div class="form-group row">
            <label for="Age" class="col-md-2 col-form-label">年龄</label>
            <div class="col-md-10">
                <input type="number" class="form-control" id="Age" name="Age"
                       required min="1" max="100"
                       value="${student.age}">
            </div>
        </div>
        <div class="form-group row">
            <label for="Birthday" class="col-md-2 col-form-label">出生日期</label>
            <div class="col-md-10">
                <input type="Date" class="form-control" id="Birthday" name="Birthday" value="${student.birthday}">
            </div>
        </div>
        <div class="form-group row">
            <label for="photofile" class="col-md-2 col-form-label">照片</label>
            <div class="col-md-10">
                <input type="file" class="form-control" id="photofile" name="photofile">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-10 offset-md-2">
                <input type="submit" class="btn btn-success" value="保存">
            </div>
        </div>
    </form>
</div>

</body>
</html>
