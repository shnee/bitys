package net.shnee.bitys.model;

import org.slf4j.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for the class Sport
 */
public class SportTest {

    public SportTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Sport.
     */
    @Test
    public void testToStringDeatiled() {
        LoggerFactory.getLogger("model." + SportTest.class.getSimpleName()).
                      debug("testToStringDetailed");
        Sport sport = new Sport(1, "Football");
        String expResult = "Sport: id=1, name=Football";
        String result = sport.toDetailedString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Sport.
     */
    @Test
    public void testToString() {
        LoggerFactory.getLogger("model." + SportTest.class.getSimpleName()).
                      debug("testToString");
        Sport sport = new Sport(1, "Football");
        String expResult = "Football";
        String result = sport.toString();
        assertEquals(expResult, result);
    }

    // TODO Add test with null value for name.
    /**
     * Test of getName method, of class Sport.
     */
    @Test
    public void testName() {
        LoggerFactory.getLogger("model." + SportTest.class.getSimpleName()).
                      debug("testGetName");
        Sport sport = new Sport();

        // Default value shoud be the empty string.
        assertEquals("", sport.getName());

        sport.setName("New Name");
        assertEquals("New Name", sport.getName());
    }

    @Test
    public void testPersistence() {
    }
}
