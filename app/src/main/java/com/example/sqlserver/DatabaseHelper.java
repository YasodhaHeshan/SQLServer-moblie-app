package com.example.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    private static final String IP = "10.0.2.2"; // Replace with your IP
    private static final String PORT = "1433"; // Default port for MS SQL Server
    private static final String DATABASE = "androidDB"; // Replace with your database name
    private static final String USERNAME = "test1";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        String connectionUrl = "jdbc:jtds:sqlserver://" + IP + ":" + PORT + "/" + DATABASE + ";user=" + USERNAME + ";password=" + PASSWORD + ";";
        return DriverManager.getConnection(connectionUrl);
    }
}