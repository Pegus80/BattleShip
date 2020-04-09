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

@WebServlet(urlPatterns = "/start")
public class StartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var name = request.getParameter("name");
        var player = new Player();
        player.setName(name);

        var gameMgr = (GameManager) request.getServletContext().getAttribute("gameManager");
        var game = gameMgr.getIncompleteGameAndJoin(player);

        request.getSession().setAttribute("player", player);
        request.getSession().setAttribute("game", game);

        request.getRequestDispatcher("/WEB-INF/waitOpponentLogin.jsp")
                .include(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var player = (Player) request.getSession().getAttribute("player");
        var game = (Game) request.getSession().getAttribute("game");

        if (player == null) {
            response.sendRedirect("/index.html");
            return;
        }

        if (game.isReady()) {
            response.sendRedirect("/setup");
        } else {
            request.getRequestDispatcher("/WEB-INF/waitOpponentLogin.jsp")
                    .include(request, response);
        }
    }
}