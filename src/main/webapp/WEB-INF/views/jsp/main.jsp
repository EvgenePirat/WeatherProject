<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.example.weatherproject.model.WeatherInfoDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.weatherproject.service.WeatherService" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.example.weatherproject.model.mapper.WeatherInfoMapper" %>
<%@ page import="com.example.weatherproject.service.impl.WeatherServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: Євген
  Date: 4/27/2023
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
    <div align="center">
        <h2>Working with weathers!</h2>
        <h4>Add new weather</h4>
        <form action="/WeatherProject_war_exploded/security/weather" method="POST">
            <input type="text" name="city" value="Kyiv">
            <input type="submit" value="Отправить">
        </form>

        <h4>Delete weather</h4>
        <form action="/WeatherProject_war_exploded/security/weather" method="GET">
            <input type="hidden" name="method" value="DELETE">
            <input type="text" name="id">
            <input type="submit" value="Отправить">
        </form>

        <%
            HttpSession sessionLocal = request.getSession();
            Long userId = (Long)sessionLocal.getAttribute("userId");
            WeatherService weatherService = new WeatherServiceImpl();
            Set<WeatherInfoDto> weatherInfoDtoList = WeatherInfoMapper.convertedSet(weatherService.getAll(userId));
            for (WeatherInfoDto weatherInfoDtoInList : weatherInfoDtoList) {
                out.println("Id = "+weatherInfoDtoInList.getId()+"<br>");
                out.println("City name = " + weatherInfoDtoInList.getName()+"<br>");
                out.println("temp = " + weatherInfoDtoInList.getTemp()+"<br>");
                out.println("Description = " + weatherInfoDtoInList.getDescription()+"<br>");
                out.println("Speed = " + weatherInfoDtoInList.getSpeed()+"<br>");
                out.println("<br>");
            }
        %>
    </div>
</body>
</html>
