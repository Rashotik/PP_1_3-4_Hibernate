package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private UserDao jdbc = new UserDaoJDBCImpl();

    public UserDaoHibernateImpl() {

    }

    public void createUsersTable() {
        jdbc.createUsersTable();
    }

    public void dropUsersTable() {
        jdbc.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(session.get(User.class, id));
        tx1.commit();
        session.close();
    }

    public List<User> getAllUsers() {
        return (List<User>) Util.getSessionFactory()
                .openSession()
                .createQuery("FROM User")
                .list();
    }

    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
        session.close();
    }
}
