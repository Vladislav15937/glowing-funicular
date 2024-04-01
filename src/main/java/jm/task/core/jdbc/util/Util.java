package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String username = "root";
    private static final String password = "1984";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/test1";
    public Connection connection;

    {
        try {
            connection = DriverManager.getConnection(connectionUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
