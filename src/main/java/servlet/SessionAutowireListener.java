package servlet;

import dao.impl.AddressImplDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionAutowireListener implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");

        AddressImplDao addressImplDao = (AddressImplDao) context.getBean("addressImplDao");

        HttpSession session = httpSessionEvent.getSession();
        session.setAttribute("addressDao", addressImplDao);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }


}
