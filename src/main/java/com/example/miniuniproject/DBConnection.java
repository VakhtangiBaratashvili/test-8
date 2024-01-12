package com.example.miniuniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/xunxulinho";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "password";

    private static Statement statement;

    static {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Statement getStatement() {
        return statement;
    }
}
