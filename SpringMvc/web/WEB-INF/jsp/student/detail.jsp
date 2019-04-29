<%@ page import="demo.model.Student" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息</title>
    <%--包括html头信息，主要是css和js文件--%>
    <jsp:include page="../share/header.jsp" />
</head>
<body>
<%--显示导航条--%>
<jsp:include page="../share/nav.jsp" />
<br />
<div class="container">
    <h3>学生信息</h3>
    <div class="row">
        <div class="col-4">
            <img style="max-width: 100%;" src="/student/photo/${student.id}" />
        </div>
        <div class="col-8">
            <ul>
                <li>学号：${student.no}</li>
                <li>姓名：${student.name}</li>
                <li>年龄：${student.age}</li>
                <li>出生日期：${student.birthday}</li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
