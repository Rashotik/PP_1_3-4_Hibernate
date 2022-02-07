package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();

        service.saveUser("Sergey", "Hohlov", (byte) 21);
        service.saveUser("Andrey", "Fink", (byte) 22);
        service.saveUser("Nikita", "Smorov", (byte) 19);
        service.saveUser("Nasir", "Chan", (byte) 35);

        List<User> users = service.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }

        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
