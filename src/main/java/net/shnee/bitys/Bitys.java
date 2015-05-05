package net.shnee.bitys;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import net.shnee.bitys.logger.BitysLogger;
import net.shnee.mlparser.ShneeNode;
import net.shnee.mlparser.ShneeParser;
import net.shnee.mlparser.Node;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;

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
        
        String url = "http://www.vegasinsider.com/nba/matchups/";
        ShneeParser parser = new ShneeParser(url);
        
        try {
            parser.downloadSource();
            parser.parse();
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(Bitys.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Bitys.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(Bitys.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Node root = parser.getRootNode();
        List<Node> nodes = root.css(".SLTables1");
        
        for(Node node : nodes) {
            List<Node> titleList = node.css(".viHeaderNorm");
            log.info(titleList.get(0).toString());
        }
        
        System.exit(0);
    }
}
