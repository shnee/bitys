package net.shnee.utils;
// TODO Move the utlility classes into it's own projects

import org.slf4j.LoggerFactory;
import java.io.PrintWriter;
import org.junit.Test;
import static org.junit.Assert.*;

public class SystemUtilsTest {

    public SystemUtilsTest() {
    }

    /**
     * Test of runCommand method, of class SystemUtils. This test will only work
     * on a unix based operating system.
     */
    @Test
    public void testRunCommandHappy() throws Exception {
        /* TODO Add a check to make sure the current operating system is a unix
         *      based one. */

        
        LoggerFactory.getLogger(SystemUtilsTest.class).
                      trace("SystemUtilsTest.testRunCommandHappy");
       
        /* Create a script that will create a text file with the text 'yay' and
         * then output the text in the file. */
        try (PrintWriter writer = new PrintWriter("script.sh", "UTF-8")) {
            String create = "echo yay > yay.txt";
            String cat    = "cat yay.txt";

            writer.println(create);
            writer.println(cat);
        }

        /* Run the newly created script, it should output the text in the file
         * yay.txt */
        String expResult = SystemUtils.runCommand("bash script.sh");
        String result = SystemUtils.runCommand("cat yay.txt");

        assertEquals(expResult, result);

        SystemUtils.runCommand("rm script.sh yay.txt");

        assertFalse(FileUtils.fileExists("script.sh"));
        assertFalse(FileUtils.fileExists("yay.txt"));
    }
    
    @Test (expected=Exception.class)
    public void testRunCommandFail() throws Exception {
        LoggerFactory.getLogger(SystemUtilsTest.class).
                      trace("SystemUtilsTest.testRunCommandFail");
        SystemUtils.runCommand("cp doesnt_exists.txt yay.txt");
    }
}
