package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Vova", "Petrov", (byte) 22);
        userService.saveUser("Zaur", "Tregulov", (byte) 42);
        userService.saveUser("Nail", "Alishev", (byte) 52);
        userService.saveUser("Valeriy", "Vasechkin", (byte) 62);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
