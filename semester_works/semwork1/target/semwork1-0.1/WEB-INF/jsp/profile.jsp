<%@ page import="ru.itis.dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Профиль!</h1>
<%
    UserDto user = (UserDto)session.getAttribute("user");
%>
<%=user.getFirstName()%>
<%=user.getLastName()%>
</body>
</html>
