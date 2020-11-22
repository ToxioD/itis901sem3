<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>D&D Races</title>
</head>
<body>
<table style="width:100%; border-collapse: collapse">
    <c:forEach items="${races}" var="dndRace">
        <tr style="border-bottom:1px solid black">
            <td style="padding-top:15px; padding-bottom:15px">
                <h3>${dndRace.getName()}</h3>
                <p>${dndRace.getDescription()}</p>
                <p>${dndRace.getAbility()}</p>
                <p>Size: ${dndRace.getSize()}</p>
                <p>Base speed: ${dndRace.getSpeed()} ft.</p>
                <p>Darkvision: ${dndRace.isHasDarkvision()}</p>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>


