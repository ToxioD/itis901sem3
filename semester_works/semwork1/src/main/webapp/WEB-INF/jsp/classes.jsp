<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>D&D Classes</title>
</head>
<body>
<table style="width:100%; border-collapse: collapse">
<c:forEach items="${classes}" var="dndClass">
        <tr style="border-bottom:1px solid black">
            <td style="padding-top:15px; padding-bottom:15px">
                <h3>${dndClass.getName()}</h3>
                <p>${dndClass.getDescription()}</p>
                <p>Hit Dice: 1d${dndClass.getHitDice()}</p>
                <p>Spellcaster: ${dndClass.isSpellcasting()}</p>
            </td>
        </tr>
</c:forEach>
</table>
</body>
</html>

