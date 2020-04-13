package lv.myprog.battleship;

import lv.myprog.battleship.model.Game;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "GameOverFilter", urlPatterns = "/*")
public class GameOverFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        var request = (HttpServletRequest) req;
        var response = (HttpServletResponse) resp;

        boolean isGameOver = Optional.ofNullable(request.getSession(false))
                .map(session -> (Game) session.getAttribute("game"))
                .map(Game::isGameOver)
                .orElse(false);

        if (isGameOver) {
            request.getSession().invalidate();
            response.sendRedirect("/index.html");
        } else {
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
