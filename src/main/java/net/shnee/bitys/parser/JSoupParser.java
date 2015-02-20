package net.shnee.bitys.parser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author shnee
 */
public class JSoupParser {
    
    public JSoupParser() {
        
        String url = "http://www.vegasinsider.com/college-basketball/matchups/";
        
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex) {
            Logger.getLogger(JSoupParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Elements games = doc.select(".SLTables1");
        
        for(Element game : games) {
            Elements headerElement = game.select(".viHeaderNorm");
            String header = headerElement.text();
            Logger.getLogger(JSoupParser.class.getName()).log(Level.INFO, header);
        }
        
    }
    
    
}
