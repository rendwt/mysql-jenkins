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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;
    private UserDTO user;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        BasicDataSource connectionPool = (BasicDataSource) context.getAttribute("connectionPool");
        userDAO = new UserDAO(connectionPool);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (!userExists(username,password)) {
            session.setAttribute("loginError", "Invalid username or password");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }else {
            session.setAttribute("userRole",user.getRole());
            session.setAttribute("username", user.getUsername());
            response.sendRedirect("home.jsp");
        }
    }

    private boolean userExists(String username, String password) {
        user = userDAO.checkUser(username, password);
        return user != null;
    }
}
