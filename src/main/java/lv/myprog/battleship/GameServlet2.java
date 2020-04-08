package lv.myprog.battleship;

import lv.myprog.battleship.model.Game;
import lv.myprog.battleship.model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GameServlet2", urlPatterns = "/game2")
public class GameServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var addr = request.getParameter("cell");
        if (addr == null) {
            request.setAttribute("message", "you forgot to select a cell");
            return;
        }
        var game = (Game) request.getSession().getAttribute("game");
        var player = (Player) request.getSession().getAttribute("player");
        if (game.isMyTurn(player)) {
            game.fire(addr);
        }
        doGet(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var player = (Player) request.getSession().getAttribute("player");
        var game = (Game) request.getSession().getAttribute("game");
        if(game.isFinished()) {
            response.sendRedirect("/finish");
        } else if (game.isMyTurn(player)) {
            request.getRequestDispatcher("/WEB-INF/fire.jsp").include(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/waitFire.jsp").include(request, response);
        }
    }
}