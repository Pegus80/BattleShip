<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>BattleShip - setup your ships</title>
    <style>
        td {
            text-align: center;
        }
    </style>
</head>
<body>

<form action="/setup" method="post">
    <table>
        <tr>
            <td></td>
            <c:forEach items="A,B,C,D,E,F,G,H,I,J" var="col">
                <td>${col}</td>
            </c:forEach>
        </tr>
        <c:forEach begin="1" end="10" var="row">
            <tr>
                <td>${row}</td>
                <c:forEach items="A,B,C,D,E,F,G,H,I,J" var="col">
                    <td>
                        <c:set var="addr" value="${col}${row}"/>
                        <input type="checkbox"
                               name="cells"
                               value="${addr}"
                               <c:if test="${sessionScope.player.ownField.getState(addr) == 'SHIP'}">checked</c:if>

                        >
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty requestScope.message}">
        <h2>${requestScope.message}</h2>
    </c:if>
    <button type="submit">Start</button>
    <button type="submit" name="btn" value="cancel">Cancel</button>
</form>

</body>
</html>