<%--
  Created by IntelliJ IDEA.
  User: Євген
  Date: 5/16/2023
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h2 align="center">Registration Account</h2>
    <div align="center">
        <form action="/WeatherProject_war_exploded/auth/login" method="POST">
            <h5>Input login</h5>
            <input type="text" name="login" value="pirat">
            <br>
            <h5>Input password</h5>
            <input type="password" name="password" value="1313">
            <br>
            <input type="submit" value="Отправить">
        </form>
        <br>
        <a href="/WeatherProject_war_exploded/"><h4>Login in account</h4></a>
    </div>
</body>
</html>
