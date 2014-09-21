package net.shnee.bitys;

import java.util.Properties;
import net.shnee.bitys.db.Db;
import net.shnee.bitys.model.Sport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class Bitys 
{
    public static void main( String[] args )
    {
        Logger log = LoggerFactory.getLogger("Bitys");
        log.info("Word to your mother...");
        
        Db db = Db.getInstance();
        
        Session session = null;
        Transaction tx = null;
        try {
            session = db.createSession();
            Sport sprt = new Sport(1, "Testing");
            session.save(sprt);
            tx = session.beginTransaction();
            Sport sport = new Sport(1, "Soccer");
            session.save(sport);
            session.flush();
            tx.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        System.exit(0);
    }
}
