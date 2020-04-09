
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>Player "${sessionScope.game.winner.name}" won!</h1>
  <h1>Player "${sessionScope.game.loser.name}" lost!</h1>

<form action="/finish" method="post">
      <table>
          <input type="radio" name="operation" value="again">Play again with player
          "${sessionScope.game.getOpponent(sessionScope.player).name}"</input><br>
          <input type="radio" name="operation" value="new">Play with a new player</input>

      </table>
      <tr>
          <button type="submit">Ok</button>
      </tr>
  </form>


</body>
</html>
