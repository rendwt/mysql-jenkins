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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/displayUsers")
public class DisplayUsersServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        BasicDataSource connectionPool = (BasicDataSource) context.getAttribute("connectionPool");
        userDAO = new UserDAO(connectionPool);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            List<UserDTO> users = userDAO.getUsers();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Users</h2>");
            out.println("<table border='1'>");
            if(users.isEmpty())
                out.println("<p>The list is empty</p>");
            else {
                out.println("<tr><th>User Id</th><th>Username</th><th>Password</th><th>Pno</th><th>Role</th></tr>");
                for (UserDTO user : users) {
                    int userId = user.getUserId();
                    String username = user.getUsername();
                    String password = user.getPassword();
                    String pno = user.getPno();
                    String role = user.getRole();
                    out.println("<tr>");

                    out.println("<td>" + userId + "</td>");
                    out.println("<td>" + username + "</td>");
                    out.println("<td>" + password + "</td>");
                    out.println("<td>" + pno + "</td>");
                    out.println("<td>" + role + "</td>");

                    out.println("</tr>");
                }
                out.println("</table>");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}