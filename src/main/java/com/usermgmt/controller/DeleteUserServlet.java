package com.usermgmt.controller;

import com.usermgmt.dao.UserDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        BasicDataSource connectionPool = (BasicDataSource) context.getAttribute("connectionPool");
        userDAO = new UserDAO(connectionPool);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        int rowCount = userDAO.deleteUser(userId);
        if (rowCount > 0) {
            request.setAttribute("deleteSuccess", "User deleted successfully");
            request.getRequestDispatcher("/delete-user.jsp").forward(request, response);
        } else{
            request.setAttribute("deleteFailure", "User failed to delete");
            request.getRequestDispatcher("/delete-user.jsp").forward(request, response);
        }

    }
}



