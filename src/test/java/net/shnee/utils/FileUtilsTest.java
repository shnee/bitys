package net.shnee.utils;

import org.slf4j.LoggerFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for the FileUtils class.
 */
public class FileUtilsTest {

    public FileUtilsTest() {
    }

    /**
     * Test of fileExist method, of class FileUtils.
     */
    @Test
    public void testFileExist() {
        LoggerFactory.getLogger("utils." + FileUtilsTest.class.getSimpleName()).
                      debug("FileUtilsTest.testFileExists");
        String file = "yay.txt";
        assertFalse(FileUtils.fileExists(file));
        try {
            SystemUtils.runCommand("touch yay.txt");
            assertTrue(FileUtils.fileExists(file));
            SystemUtils.runCommand("rm yay.txt");
            assertFalse(FileUtils.fileExists(file));
        } catch (Exception ex) {
            LoggerFactory.getLogger("utils." +
                                    FileUtilsTest.class.getSimpleName()).
                          error("Error running testFileExist" +
                                ex.getMessage());
            fail("Exception thrown.");
        }
    }
}
