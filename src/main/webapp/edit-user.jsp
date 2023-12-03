<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
    <h1>Edit User</h1>
    <% if (request.getAttribute("editSuccess") != null) { %>
        <p style="color: green;"><%= request.getAttribute("editSuccess") %></p>
    <% } %>
    <% if (request.getAttribute("editFailure") != null) { %>
            <p style="color: red;"><%= request.getAttribute("editFailure") %></p>
    <% } %>
    <div id="userlist">
    </div><br><br>
    <form action="editUser" method="post">
        User ID: <input type="text" name="userId"><br>
        New Username: <input type="text" name="newUsername"><br>
        New Password: <input type="password" name="newPassword"><br>
        New Phone no: <input type="text" name="newpno"><br>
        <label for="role">Role:</label>
        <select id="role" name="newRole">
            <option value="admin">Admin</option>
            <option value="user">User</option>
        </select><br>
        <input type="submit" value="Update">
    </form>
    <a href="home.jsp">Back to home</a>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="scripts/display.js"></script>
</body>
</html>
