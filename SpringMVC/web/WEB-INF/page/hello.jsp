<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2020/3/31
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<!-- 请求方式的示例 -->
<a href="account/saveAccount">保存账户，get 请求 会405</a> <br/>
<form action="account/saveAccount" method="post">
    <input type="submit" value="保存账户，post 请求">
</form>
<!-- 当我们点击第一个超链接时,可以访问成功。
当我们点击第二个超链接时，无法访问。-->
<a href="account/removeAccount?accountName=aaa&money>100">删除账户，金额 100</a> <br/>
<a href="account/removeAccount?accountName=aaa&money>150">删除账户，金额 150</a>
</body>
</html>
