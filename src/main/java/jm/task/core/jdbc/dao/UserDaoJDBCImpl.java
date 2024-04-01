package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.sql.Insert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    Util util = new Util();

    public void createUsersTable() {

        try (Statement statement = util.connection.createStatement()) {

            statement.execute("CREATE TABLE if not exists user(id BIGINT not null auto_increment," +
                    " Name VARCHAR(50), LastName VARCHAR(50), Age SMALLINT, primary key (id))");
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {

        try (Statement statement = util.connection.createStatement()) {

            statement.execute("drop TABLE if exists user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement = util.connection.prepareStatement("Insert into user(Name, LastName, Age)" +
                "values (?,?,?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement preparedStatement = util.connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();

        try (Statement statement = util.connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            int i = 0;
            while (resultSet.next()) {

                list.add(new User(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4)));
                User user = list.get(i);
                System.out.println(user.toString());
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {

        try (Statement statement = util.connection.createStatement()) {

            statement.execute("DELETE FROM user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
