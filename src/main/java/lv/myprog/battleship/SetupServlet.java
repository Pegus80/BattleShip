package lv.myprog.battleship;



import lv.myprog.battleship.model.CellState;
import lv.myprog.battleship.model.Game;
import lv.myprog.battleship.model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "SetupServlet", urlPatterns = "/setup")
public class SetupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selected = request.getParameterValues("cells");

        var player = (Player) request.getSession().getAttribute("player");

        player.getOwnField().clear();

        if (selected != null) {
            System.out.println(Arrays.toString(selected));
            for (String addr : selected) {
                player.getOwnField().setState(addr, CellState.SHIP);
            }
        }

        if (player.getOwnField().isValid()) {
            request.getRequestDispatcher("/WEB-INF/waitOpponentShips.jsp").include(request, response);
        } else {
            request.setAttribute("message", "wrong placement!");
            request.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO insert logic to check state, whether everything is ready to setup ships.

        var player = (Player) request.getSession().getAttribute("player");
        var game = (Game) request.getSession().getAttribute("game");
        var opponentPlayer = game.getOpponent(player);


        if (opponentPlayer == null) {

            response.sendRedirect("/start");
        } else if (!player.getOwnField().isValid()) {
            request.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(request, response);

        } else if (opponentPlayer.getOwnField().isValid()) {
            response.sendRedirect("/game");

        } else {
            request.getRequestDispatcher("/WEB-INF/waitOpponentShips.jsp").include(request, response);

        }


    }
}