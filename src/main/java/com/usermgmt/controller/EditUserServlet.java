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

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        BasicDataSource connectionPool = (BasicDataSource) context.getAttribute("connectionPool");
        userDAO = new UserDAO(connectionPool);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");
        String newPno = request.getParameter("newpno");
        String newRole = request.getParameter("newRole");

        UserDTO updatedUser = new UserDTO();
        updatedUser.setUserId(userId);
        updatedUser.setUsername(newUsername);
        updatedUser.setPassword(newPassword);
        updatedUser.setPno(newPno);
        updatedUser.setRole(newRole);

        int rowCount = userDAO.editUser(updatedUser);
        if (rowCount > 0) {
            request.setAttribute("editSuccess", "User details updated successfully");
            request.getRequestDispatcher("/edit-user.jsp").forward(request, response);
        } else{
            request.setAttribute("editFailure", "User details Failed to update");
            request.getRequestDispatcher("/edit-user.jsp").forward(request, response);
        }

    }
}


