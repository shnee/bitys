package net.shnee.bitys;

import net.shnee.bitys.db.populate.PopulateSport;
import net.shnee.bitys.logger.BitysLogger;
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
        Logger log = BitysLogger.BITYS;
        log.info("Word to your mother...");
            
        PopulateSport.deleteAllAndPopulate();
        
        System.exit(0);
    }
}
