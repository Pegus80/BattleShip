package lv.myprog.battleship;

import lv.myprog.battleship.model.Game;
import lv.myprog.battleship.model.GameManager;
import lv.myprog.battleship.model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "FinishServlet", urlPatterns = "/finish")
public class FinishServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operation = request.getParameter("operation");
        var game =  (Game) request.getSession().getAttribute("game");
        game.clearGame();

        if (operation.equals("again")) {
            request.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(request, response);
        }

        if (operation.equals("new")) {
            request.getSession().setAttribute("game", null);

            var player = (Player) request.getSession().getAttribute("player");
            var gameMgr = (GameManager) request.getServletContext().getAttribute("gameManager");
            game = gameMgr.getIncompleteGameAndJoin(player);
            request.getSession().setAttribute("game", game);

          //  request.getRequestDispatcher("/WEB-INF/waitOpponentLogin.jsp").include(request, response);
            response.sendRedirect("/start");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var game = (Game) request.getSession().getAttribute("game");

        if (game.isFinished()){
            request.getRequestDispatcher("/WEB-INF/finish.jsp").include(request, response);

        }

    }
}
