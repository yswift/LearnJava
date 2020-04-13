<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Spring MVC 运行成功
<h3>@RequestParam参数</h3>
<ul>
    <li><a href="/request/p1">p1接收参数 name，参数可选</a></li>
    <li><a href="/request/p1?name=李四">p1接收参数 name=李四</a></li>
    <li><a href="/request/p2?name=王五">p2接收参数 必须提供name</a></li>
    <li><a href="/request/p3?req=王五五">p3客户端提供的参数和形参的名称不一样</a></li>
    <li><a href="/request/p4">p4参数默认值</a></li>
    <li><a href="/request/p4?req=aaa">p4参数默认值，req=aaa</a></li>
    <li><a href="/request/p5?id=01&name=工学院">p5整体参数,p5?id=01&name=工学院</a></li>
</ul>
<h3>@PathVariable</h3>
<ul>
    <li><a href="/pathVar/p1/张三">/pathVar/p1/张三</a></li>
    <li><a href="/pathVar/p2/2020/07">/pathVar/p2/2020/07</a></li>
    <li><a href="/pathVar/p3/2021/17">带参数验证 /pathVar/p3/2020/17</a></li>
</ul>
<h3>传递数据到视图</h3>
<ul>
    <li><a href="/data2view/model">Model model</a></li>
    <li><a href="/data2view/modelMap">ModelMap</a></li>
    <li><a href="/data2view/modelAndView">ModelAndView</a></li>
</ul>
<h3>使用View</h3>
<ul>
    <li><a href="demo/param">demo/param</a></li>
    <li><a href="demo/college">demo/college</a></li>
</ul>
<h3>Controller返回值</h3>
<ul>
    <li><a href="returnValue/json">json</a></li>
    <li><a href="returnValue/college">college</a></li>
    <li><a href="returnValue/redirect">redirect to json</a></li>
    <li><a href="returnValue/redirect2">redirect2 to json</a></li>
</ul>
<h3>学生案例</h3>
<ul>
    <li><a href="/student/list">学生案例</a></li>
</ul>
</body>
</html>
