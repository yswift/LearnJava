<%@ page import="example.Student" %>
<%@ page import="example.StudentDAO" %><%--
  Created by IntelliJ IDEA.
  User: yswif
  Date: 2019-4-23
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息</title>
    <%--包括html头信息，主要是css和js文件--%>
    <jsp:include page="../share/header.html" />
</head>
<body>
<%--显示导航条--%>
<jsp:include page="../share/nav.html" />
<br />
<%
    String id = request.getParameter("id");
    StudentDAO dao = new StudentDAO();
    Student s = dao.findById(id);
    if (s == null) {
        response.sendError(404, "student not found by id: " + id);
    }
%>
<div class="container">
    <h3>学生信息</h3>
    <div class="row">
        <div class="col-4">
            <img src="/student/photo?id=<%=id%>" />
        </div>
        <div class="col-8">
            <ul>
                <li>学号：<%= s.getNo() %></li>
                <li>姓名：<%= s.getName() %></li>
                <li>年龄：<%= s.getAge() %></li>
                <li>出生日期：<%= s.getBirthday() %></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
