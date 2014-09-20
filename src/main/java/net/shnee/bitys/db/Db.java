package net.shnee.bitys.db;

import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Common database code.
 */
public class Db {
    
    private SessionFactory sessionFactory;
    
    private Db() {
        Configuration configuration = new Configuration(); 
        configuration.configure(); 
         
        Properties properties = configuration.getProperties();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
                                              applySettings(properties).
                                              buildServiceRegistry();
        this.sessionFactory = configuration.
                                           buildSessionFactory(serviceRegistry);
    }
    
    public static Db getInstance() {
        return DbHolder.INSTANCE;
    }
    
    public Session createSession() {
        return sessionFactory.openSession();
    }
    
    private static class DbHolder {

        private static final Db INSTANCE = new Db();
    }
}
