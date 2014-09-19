package net.shnee.utils;

import java.io.PrintWriter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shnee
 */
public class SystemUtilsTest {
    
    public SystemUtilsTest() {
    }

    /**
     * Test of runCommand method, of class SystemUtils.
     */
    @Test
    public void testRunCommand() throws Exception {
        
        PrintWriter writer = new PrintWriter("script.sh", "UTF-8");
        
        String create = "echo yay > yay.txt";
        String cat    = "cat yay.txt";
        
        writer.println(create);
        writer.println(cat);
        writer.close();
        
        String expResult = SystemUtils.runCommand("bash script.sh");
        String result = SystemUtils.runCommand("cat yay.txt");
        
        assertEquals(expResult, result);
        
        SystemUtils.runCommand("rm script.sh yay.txt");
        
        assertFalse(FileUtils.fileExist("script.sh"));
        assertFalse(FileUtils.fileExist("yay.txt"));
    }
}
