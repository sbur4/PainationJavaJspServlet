package com.example.Pagination.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private static final String DB_CONNECTION_PATH = "dbConnection.properties";

    private static List<Connection> connectionPool = new ArrayList<>();

    public static synchronized Connection getConnection() throws Exception {
        Properties dbProperties = new Properties();
        dbProperties.load(ConnectionPool.class.getClassLoader().getResourceAsStream(DB_CONNECTION_PATH));

        if (connectionPool.size() > 0) {
            return connectionPool.remove(connectionPool.size() - 1);
        }

        try {
            Class.forName(dbProperties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        }

        try {
            return DriverManager.getConnection(dbProperties.getProperty("url"), dbProperties.getProperty("user"), dbProperties.getProperty("password"));
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    protected synchronized void releaseConnection(Connection con) {
        connectionPool.add(con);
    }
}