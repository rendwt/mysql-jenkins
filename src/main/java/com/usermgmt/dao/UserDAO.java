package com.usermgmt.dao;

import com.usermgmt.dto.UserDTO;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private BasicDataSource dbConnection;

    public UserDAO(BasicDataSource dbConnection) {
        this.dbConnection = dbConnection;
    }

    public int createUser(UserDTO user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowCount =0;
        try {
            connection = dbConnection.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement=connection.prepareStatement("insert into users(username, password, pno, role) values(?,?,?,?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPno());
            preparedStatement.setString(4, user.getRole());
            rowCount=preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResources(connection, preparedStatement, null);
        }
        return rowCount;
    }

    public UserDTO checkUser(String username, String inputPassword){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        UserDTO user = null;
        try {
            connection = dbConnection.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement=connection.prepareStatement("select * from users where username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                if (storedPassword.equals(inputPassword)) {
                    user = new UserDTO();
                    user.setUserId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setPno(resultSet.getString("pno"));
                    user.setRole(resultSet.getString("role"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResources(connection, preparedStatement, null);
        }
        return user;
    }
    public UserDTO getUserByUsername(String username){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        UserDTO user = null;
        try {
            connection = dbConnection.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement=connection.prepareStatement("select * from users where username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserDTO();
                user.setUserId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setPno(resultSet.getString("pno"));
                user.setRole(resultSet.getString("role"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResources(connection, preparedStatement, null);
        }
        return user;
    }

    public List<UserDTO> getUsers() {

        List<UserDTO> groceryList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dbConnection.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            String query = "SELECT * FROM users ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setPno(resultSet.getString("pno"));
                user.setRole(resultSet.getString("role"));
                groceryList.add(user);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving users", e);
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }
        return groceryList;
    }

    public int editUser(UserDTO user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowCount = 0;
        try {
            connection = dbConnection.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = connection.prepareStatement("UPDATE users SET username=?, password=?, pno=?, role=? WHERE id=?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPno());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setInt(5, user.getUserId());
            rowCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, null);
        }
        return rowCount;
    }


    public int deleteUser(int userId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowCount = 0;
        try {
            connection = dbConnection.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setInt(1, userId);
            rowCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, null);
        }
        return rowCount;
    }

    private void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
