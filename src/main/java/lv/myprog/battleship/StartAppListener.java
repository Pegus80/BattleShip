package lv.myprog.battleship;


import lv.myprog.battleship.model.GameManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class StartAppListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        var gameMgr = new GameManager();
        sce.getServletContext().setAttribute("gameManager", gameMgr);


    }
}