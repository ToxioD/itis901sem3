<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach items="${errors}" var="error">
        <li>${error.getMessage()}</li>
    </c:forEach>
</ul>
<form action="/signUp" method="post">
    <input type="email" name="email" placeholder="Email">
    <input type="text" name="firstName" placeholder="First Name">
    <input type="text" name="lastName" placeholder="Last Name">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" value="Sign Up">
</form>
<a href="/signIn">Already have an account?</a>
</body>
</html>
