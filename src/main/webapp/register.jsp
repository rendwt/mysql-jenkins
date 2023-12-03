<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <div class="registration-form">
        <h2>User Registration</h2>
        <form action="register" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br><br>

            <label for="phone no">Phone number:</label>
            <input type="text" id="password" name="pno" required><br><br>

            <label for="role">Role:</label>
            <select id="role" name="role">
                <option value="admin">Admin</option>
                <option value="user">User</option>
            </select><br><br>

            <input type="submit" value="Register">
        </form>
        <p>Registered already? <a href="login.jsp">Login</a></p>
    </div>
</body>
</html>
