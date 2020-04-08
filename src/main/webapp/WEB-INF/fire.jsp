<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Wait Fire - BattleShip</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<form action="/game" method="post">
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
                    <c:set var="addr" value="${col}${row}"/>
                    <c:set var="ship" value="${sessionScope.player.enemyField.getState(addr)}"/>
                    <td class="${ship}">
                        <input type="radio"
                               name="cell"
                               value="${addr}"
                        >
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
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
                    <c:set var="addr" value="${col}${row}"/>
                    <c:set var="ship" value="${sessionScope.player.ownField.getState(addr)}"/>
                    <td class="${ship}">

                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty requestScope.message}">
        <h2>${requestScope.message}</h2>
    </c:if>
    <button type="submit">Fire</button>
</form>

<c:forEach var="h" items="${sessionScope.player.history}">
    <p>${h}</p>
</c:forEach>


</body>
</html>