<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
    <h1>Delete User</h1>
    <% if (request.getAttribute("deleteSuccess") != null) { %>
            <p style="color: green;"><%= request.getAttribute("deleteSuccess") %></p>
    <% } %>
    <% if (request.getAttribute("deleteFailure") != null) { %>
            <p style="color: red;"><%= request.getAttribute("deleteFailure") %></p>
    <% } %>
    <div id="userlist">
    </div><br><br>
    <form action="deleteUser" method="post">
        User ID: <input type="text" name="userId"><br>
        <input type="submit" value="Delete">
    </form>
    <a href="home.jsp">Back to home</a>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="scripts/display.js"></script>
</body>
</html>
