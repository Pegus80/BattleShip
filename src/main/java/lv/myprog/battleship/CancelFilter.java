package lv.myprog.battleship;

import lv.myprog.battleship.model.Game;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CancelFilter", urlPatterns = "/*")
public class CancelFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        var request = (HttpServletRequest) req;
        var response = (HttpServletResponse) resp;

        var btn = req.getParameter("btn");
        if (btn != null && btn.equals("cancel")) {
            var game = (Game) request.getSession().getAttribute("game");
            if (game != null) {
                game.setGameOver(true);
            }
            request.getSession().invalidate();
            response.sendRedirect("/index.html");
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void destroy() {
    }

}