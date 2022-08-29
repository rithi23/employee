package com.ideas2it.employee.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class ConnectionUtil {

    private static Connection connection = null;
    private static String URL = "jdbc:mysql://localhost:3306/employee";
    private static String USERNAME = "root";
    private static String PASSWORD = "Rithi@10329";  
    private static ConnectionUtil createConnection;

    private ConnectionUtil() {
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Connection getInstance() throws SQLException {
        if (connection == null || connection.isClosed()) {
           createConnection = new ConnectionUtil();
        }
        return connection;
    }
}