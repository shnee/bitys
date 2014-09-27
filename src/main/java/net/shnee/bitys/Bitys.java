package net.shnee.bitys;

import java.util.List;
import net.shnee.bitys.db.Db;
import net.shnee.bitys.db.populate.PopulateSport;
import net.shnee.bitys.model.Sport;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
            
        PopulateSport.deleteAllAndPopulate();
        
        System.exit(0);
    }
}
