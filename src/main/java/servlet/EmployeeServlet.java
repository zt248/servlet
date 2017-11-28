package servlet;

import dao.impl.AddressImplDao;
import entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employeeServlet")
public class EmployeeServlet extends HttpServlet {

    @Autowired
    AddressImplDao addressImplDao;


    public void init(ServletConfig config) {
        try {
            super.init(config);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    protected void forward(String to, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(to);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String employeeId = req.getParameter("employeeId");
        String employeeCountry = req.getParameter("employeeCountry");
        String employeeCity = req.getParameter("employeeCity");
        String employeeStreet = req.getParameter("employeeStreet");
        String employeePostCode = req.getParameter("employeePostCode");
        String action = req.getParameter("action");

        if ("Employee".equals(action)) {
            forward("/WEB-INF/jsp/employee.jsp", req, resp);
        } else if ("AddressList".equals(action)) {
            List<Address> addressList = addressImplDao.getAll();
            req.setAttribute("getAll", addressList);
            forward("/WEB-INF/jsp/addressList.jsp", req, resp);
        } else if ("NewEmployee".equals(action)) {
            forward("/WEB-INF/jsp/newEmployee.jsp", req, resp);
        } else if ("AddEmployee".equals(action)) {
            addressImplDao.add(new Address(null, employeeCountry, employeeCity, employeeStreet, employeePostCode));
            forward("/WEB-INF/jsp/employee.jsp", req, resp);
        } else if ("DeleteEmployee".equals(action)) {
            forward("/WEB-INF/jsp/deleteEmployee.jsp", req, resp);
        } else if ("RemoveEmployee".equals(action)) {
            addressImplDao.remove(new Address(Long.valueOf(employeeId), null, null, null, null));
            forward("/WEB-INF/jsp/employee.jsp", req, resp);
        }
    }
}

