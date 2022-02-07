package jm.task.core.jdbc.util;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

import java.sql.*;

public class Util {
    static private Connection connect = null;
    static private SessionFactory sessionFactory = null;

    static public Connection getConnect (){
        if(connect == null) {
            try {
                connect = DriverManager.getConnection("jdbc:mysql://localhost/my?"
                        + "user=root&password=rashot-8937561");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try{
                Properties prop = new Properties();
                prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/my");
                prop.setProperty("hibernate.connection.username", "root");
                prop.setProperty("hibernate.connection.password", "rashot-8937561");
                prop.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");

                prop.setProperty("hibernate.hbm2ddl.auto", "create");

                sessionFactory = new org.hibernate.cfg.Configuration()
                        .addProperties(prop)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory()
                ;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
