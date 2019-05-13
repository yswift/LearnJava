<%@ page import="demo.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生列表</title>
    <%--包括html头信息，主要是css和js文件--%>
    <jsp:include page="../share/header.jsp" />
</head>
<body>
<%--显示导航条--%>
<jsp:include page="../share/nav.jsp" />
<br />

<div class="container">
    <%--如果是从其它页面调整到，本页面，需要显示消息--%>
    <% String msg = request.getParameter("msg"); %>
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
            <th scope="col"><a href="/student/create" >新建</a></th>
        </tr>
        </thead>
        <tbody>
        <%--获取从controll传递过来的数据--%>
        <% List<Student> list = (List<Student>) request.getAttribute("list"); %>
        <% for (Student s : list) { %>
        <tr>
            <td><%= s.getId() %></td>
            <td><%= s.getNo() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getAge() %></td>
            <td><%= s.getBirthday() %></td>
            <td>
                <a href="detail/<%= s.getId()%>" >详细</a>
                &nbsp;
                <a href="edit/<%= s.getId()%>" >编辑</a>
                &nbsp;
                <a href="delete/<%= s.getId()%>" >删除</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
