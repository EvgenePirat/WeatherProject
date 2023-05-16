<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <div align="center">
        <h1 >Page login!</h1>
        <br>
        <form action="/WeatherProject_war_exploded/auth/login" method="GET">
            <input type="text" name="login" value="pirat">
            <input type="password" name="password" value="1313">
            <input type="submit" value="Отправить">
        </form>
        <br>
        <a href="/WeatherProject_war_exploded/registration"><h4>Registration</h4></a>
    </div>
</body>
</html>