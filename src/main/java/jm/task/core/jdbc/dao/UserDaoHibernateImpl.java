package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;
    private Transaction transaction = null;

    public UserDaoHibernateImpl() {
        session = Util.getSessionFactory().openSession();
    }

    public void createUsersTable() {
        transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS `user` " +
                "(`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "`name` VARCHAR(50) NOT NULL, " +
                "`lastName` VARCHAR(50) NOT NULL, " +
                "`age` INT NOT NULL)";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
    }

    public void dropUsersTable() {
        transaction = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS user";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
    }

    public void saveUser(String name, String lastName, byte age) {
        transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
    }

    public void removeUserById(long id) {
        transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
    }

    public List<User> getAllUsers() {
        return (List<User>) session.createQuery("FROM User").list();
    }

    public void cleanUsersTable() {
        transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
    }
}
