package ru.gb.patterns.relationObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHolder {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/default", "sa", "");
    }
}
