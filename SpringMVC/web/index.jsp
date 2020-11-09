<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2020/3/31
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello spring mvc</title>
</head>
<body>
$END$
<a href="${pageContext.request.contextPath}/hello">SpringMVC 入门案例</a> <br/>
<!--当我们使用此种方式配置时，在 jsp 中第二种写法时，不要在访问 URL 前面加/，否则无法找到资源。-->
<a href="hello">SpringMVC 入门案例</a>


</body>
</html>
