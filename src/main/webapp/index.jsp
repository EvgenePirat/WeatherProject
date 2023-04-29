<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1 align="center">Page login!</h1>
    <br>
    <form action="/WeatherProject_war_exploded/auth/login" method="GET">
        <input type="text" name="login" value="pirat">
        <input type="password" name="password" value="1313">
        <input type="submit" value="Отправить">
    </form>
</body>
</html>