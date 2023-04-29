<%@ page import="com.example.weatherproject.exception.ExceptionBody" %>
<%--
Created by IntelliJ IDEA.
  User: Євген
  Date: 4/27/2023
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
  <h2>You get error</h2>
  <%
      ExceptionBody exceptionBody = (ExceptionBody) request.getAttribute("bodyError");
      out.println(exceptionBody.getMessage());
      out.println(" Code error = ");
      out.println(exceptionBody.getStatus());
  %>
</body>
</html>
