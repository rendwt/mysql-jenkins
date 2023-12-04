<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create User</title>
</head>
<body>
    <h1>Create User</h1>
    <div id="userlist">
    </div><br><br>
    <form action="register" method="post">
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        Phone number: <input type="text" name="pno"><br>
        <label for="role">Role:</label>
        <select id="role" name="role">
            <option value="admin">Admin</option>
            <option value="user">User</option>
        </select><br>
        <input type="submit" value="Create">
    </form>
    <a href="home.jsp">Back to home</a>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="scripts/display.js"></script>
</body>
</html>
