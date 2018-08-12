package com.achini.dataaccess.util;

import java.sql.*;

/**
 * @author Chanaka Rathnayaka
 */
public class DBConnectionManager {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/tuition_class";
        String user = "root";
        String pwd = "password";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(dbURL, user, pwd);
    }

    public static void closeResources(ResultSet rs, Statement st, Connection cn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
