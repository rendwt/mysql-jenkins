package com.usermgmt.controller;

import com.usermgmt.dao.UserDAO;
import com.usermgmt.dto.UserDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private UserDAO usersDAO;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        BasicDataSource connectionPool = (BasicDataSource) context.getAttribute("connectionPool");
        usersDAO = new UserDAO(connectionPool);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        if (isUsernameTaken(username)) {
            response.sendRedirect(request.getContextPath()+"/registration-error.jsp");
        }else {
            try {
                UserDTO user = new UserDTO();
                user.setUsername(username);
                user.setPassword(request.getParameter("password"));
                user.setPno(request.getParameter("pno"));
                user.setRole(request.getParameter("role"));
                int rowCount = usersDAO.createUser(user);
                if (rowCount > 0)
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                else
                    response.sendRedirect(request.getContextPath() + "/registration-error.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private boolean isUsernameTaken(String username) {
        UserDTO existingUser = usersDAO.getUserByUsername(username);
        return existingUser != null;
    }

}

