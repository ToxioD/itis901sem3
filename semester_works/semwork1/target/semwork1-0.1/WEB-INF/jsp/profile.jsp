<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h1>Профиль!</h1>
<p>${firstName} ${lastName}</p>
<table>
    <tr>
        <th>Dices</th>
        <th>Result</th>
    </tr>
<c:forEach items="${rolls}" var="roll">
    <tr>
        <td>${roll.getDices()}</td>
        <td style="color:${roll.getColor()}">${roll.getResult()}</td>
    </tr>
</c:forEach>
</table>
<form action="/profile" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
