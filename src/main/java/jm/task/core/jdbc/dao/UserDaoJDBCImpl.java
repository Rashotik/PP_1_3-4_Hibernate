package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    static public Connection connect = Util.getConnect();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement stmt = connect.createStatement( );
            stmt.execute("CREATE TABLE IF NOT EXISTS `my`.`Users` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `name` varchar(45)," +
                    "  `lastName` varchar(45)," +
                    "  `age` int," +
                    "  PRIMARY KEY (`id`));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement stmt = connect.createStatement( );
            stmt.execute("DROP TABLE If EXISTS Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO " +
                    "Users (`name`, `lastName`, `age`) " +
                    "VALUES (?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User with name: " + name + " is added to database");
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM Users WHERE `id` = ?");
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM my.Users;");
            ResultSet set = stmt.executeQuery();
            while(set.next()) {
                User u = new User(
                        set.getString("name"),
                        set.getString("lastName"),
                        set.getByte("age"));
                u.setId(set.getLong("id"));
                users.add(u);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM my.Users;");
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
