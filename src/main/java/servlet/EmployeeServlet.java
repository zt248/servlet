package servlet;

import dao.DaoException;
import dao.impl.AddressImplDao;
import entity.Address;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/employeeServlet")
public class EmployeeServlet extends HttpServlet {


    protected void forward(String to, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(to);
        dispatcher.forward(req,resp);
    }

        @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AddressImplDao addressImplDao = new AddressImplDao();
        String employeeId = req.getParameter("employeeId");
        String employeeCountry = req.getParameter("employeeCountry");
        String employeeCity = req.getParameter("employeeCity");
        String employeeStreet = req.getParameter("employeeStreet");
        String employeePostCode = req.getParameter("employeePostCode");
        String action = req.getParameter("action");

        if("Employee".equals(action)){
            forward("/WEB-INF/jsp/employee.jsp",req,resp);
        }else if("AddressList".equals(action)) {
        try {
             List<Address> addressList = addressImplDao.getAll();
            req.setAttribute("getAll",addressList);
            forward("/WEB-INF/jsp/addressList.jsp",req,resp);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        } else if("NewEmployee".equals(action)){
                forward("/WEB-INF/jsp/newEmployee.jsp",req,resp);
        }else if("AddEmployee".equals(action)){
            try {
                addressImplDao.add(new Address(null,employeeCountry,employeeCity,employeeStreet,employeePostCode));
                forward("/WEB-INF/jsp/employee.jsp",req,resp);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
    }
}

