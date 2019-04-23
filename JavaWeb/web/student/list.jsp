<%@ page import="example.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="example.StudentDAO" %><%--
  Created by IntelliJ IDEA.
  User: yswif
  Date: 2019-4-23
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生列表</title>
    <%--包括html头信息，主要是css和js文件--%>
    <jsp:include page="../share/header.html" />
</head>
<body>
<%--显示导航条--%>
<jsp:include page="../share/nav.html" />
<br />

<div class="container">
    <%--如果是从其它页面调整到，本页面，需要显示消息--%>
    <% String msg = (String)request.getAttribute("msg"); %>
    <% if (msg != null && msg.length() > 0) { %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <%= msg %>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <% } // end if msg %>

    <h3>学生列表</h3>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">学号</th>
            <th scope="col">姓名</th>
            <th scope="col">年龄</th>
            <th scope="col">出生日期</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <%
            StudentDAO dao = new StudentDAO();
            List<Student> list = dao.listAll();
        %>
        <% for (Student s : list) { %>
        <tr>
            <td><%= s.getId() %></td>
            <td><%= s.getNo() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getAge() %></td>
            <td><%= s.getBirthday() %></td>
            <td>
                <a href="detail.jsp?id=<%= s.getId()%>" >详细</a>
                &nbsp;
                <a href="edit.jsp?id=<%= s.getId()%>" >编辑</a>
                &nbsp;
                <a href="/student/delete?id=<%= s.getId()%>" >删除</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
