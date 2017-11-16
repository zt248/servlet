package servlet;


import dao.DaoException;
import dao.impl.AddressImplDao;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionCounterListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        session.setAttribute("addressDao", new AddressImplDao());
        System.out.println("A new session is created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        try {
            ((AddressImplDao)session.getAttribute("addressDao")).close();
            System.out.println("session is destroyed");
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }
}
