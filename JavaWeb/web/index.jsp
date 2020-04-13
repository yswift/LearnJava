<%--
  Created by IntelliJ IDEA.
  User: yswif
  Date: 2019-4-10
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
    <jsp:include page="share/header.html" />
  </head>
  <body>
  <%--显示导航条--%>
  <jsp:include page="share/nav.html" />
  <br />

  <div class="container">
    <h3>Servlet</h3>
    <ul>
      <li><a href="h1">第一个servlet /h1</a></li>
      <li><a href="h2">使用注解配置servlet /h2</a></li>
      <li><a href="h3">Url参数 /h3</a></li>
      <li><a href="h3?name=李四">Url参数 /h3?name=李四</a></li>
      <li><a href="h4">Servlet是单例 /4</a></li>
    </ul>
    <br/>
    <ul>
      <li><a href="displayHeader">客户端 HTTP 请求</a></li>
      <li><a href="refresh">5秒自动刷新页面</a></li>
      <li><a href="showStatus?status=200">HTTP 状态码200</a></li>
      <li><a href="showStatus?status=404">HTTP 状态码404</a></li>
      <li><a href="showStatus?status=500">HTTP 状态码500</a></li>
      <li><a href="sessionTrack">Session</a></li>
      <li><a href="contextW">ServletContext contextW</a></li>
      <li><a href="contextR">ServletContext contextR</a></li>
    </ul>
    <br/>
    <ul>
      <li><a href="Life.jsp">JSP 生命周期</a></li>
    </ul>
  </div>
  </body>
</html>
