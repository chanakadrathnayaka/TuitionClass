package com.achini.dataaccess;

import com.achini.dataaccess.util.DBConnectionManager;
import com.achini.dataaccess.util.DBUtils;
import com.achini.models.User;

import java.sql.*;

/**
 * @author Chanaka Rathnayaka
 */
public class UserDataAccess {

    private static final String USER_INSERT_QUERY = "INSERT INTO " +
            "Users(name,email,username,password,userType,enrolledDate,birthDate) VALUES (?,?,?,?,?,?,?)";

    private static final String USER_SELECT_QUERY = "SELECT * FROM " +
            "Users WHERE username=? AND password=?";

    private static final String USER_UPDATE_QUERY = "UPDATE " +
            "Users set name=?,email=?,username=?,password=?,birthDate=? WHERE userId=?";

    public int insertUser(User user) {
        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int userId = -1;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(USER_INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUsername());
            statement.setString(4, new String(user.getPassword()));
            statement.setString(5, user.getUserType().name());
            statement.setDate(6, new Date(new java.util.Date().getTime()));
            statement.setDate(7, new Date(user.getBirthDate().getTime()));

            if (statement.executeUpdate() == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    userId = resultSet.getInt(1);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(resultSet, statement, connection);
        }
        return userId;
    }

    public User getUser(User u) {
        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(USER_SELECT_QUERY);

            statement.setString(1, u.getUsername());
            statement.setString(2, new String(u.getPassword()));

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = DBUtils.getUser(resultSet);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(resultSet, statement, connection);
        }
        return user;
    }


    public User updateUser(User user) {

        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;
        User newUser = null;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(USER_UPDATE_QUERY);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUsername());
            statement.setString(4, new String(user.getPassword()));
            statement.setDate(5, new Date(user.getBirthDate().getTime()));
            statement.setInt(6, user.getUserId());

            if (statement.executeUpdate() == 1) {
                statement.clearParameters();
                statement = connection.prepareStatement("SELECT * FROM " +
                        "Users WHERE userId=?");
                statement.setInt(1, user.getUserId());
                resultset = statement.executeQuery();
                resultset.first();
                newUser = DBUtils.getUser(resultset);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(resultset, statement, connection);
        }
        return newUser;

    }
}
