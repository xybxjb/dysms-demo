<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2020/3/31
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>pojo</title>
</head>
<body>
<form action="account/saveAccount" method="post">
    账户名称： <input type="text" name="name"><br/>
    账户金额：<input type="text" name="money">
    账户省份：<input type="text" name="address.provinceName">
    账户城市：<input type="address.cityName">
    <input type="submit" value="保存">
</form>
</body>
</html>
