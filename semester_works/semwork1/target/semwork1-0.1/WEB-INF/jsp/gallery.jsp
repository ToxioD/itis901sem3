<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 015 15.11.20
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gallery</title>
</head>
<body>
<form action="/gallery" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="Upload File">
</form>
<c:forEach items="${photosForJsp}" var="photo">
    <img src="${photo}" style="border: 1px solid #ddd; border-radius: 4px; padding: 5px; height: 150px; width: 150px;">
</c:forEach>
</body>
</html>
