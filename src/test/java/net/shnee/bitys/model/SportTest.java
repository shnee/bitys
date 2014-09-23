package net.shnee.bitys.model;

import org.slf4j.LoggerFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for the class Sport
 */
public class SportTest {

    private SessionFactory sessionFactory;
    private Session session = null;

    public SportTest() {
    }

    @Before
    public void setUp() {

        /* TODO create class for this code, it will be used in all model unit
         * tests. */
        // TODO This code needs to be moved to a unit test for dao

        // setup the session factory
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.addAnnotatedClass(Sport.class);
        configuration.setProperty("hibernate.dialect",
                                  "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class",
                                  "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:test");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");

        this.sessionFactory = configuration.buildSessionFactory();
        this.session = sessionFactory.openSession();
    }

    @After
    public void tearDown() {
        this.session.close();
        this.sessionFactory.close();
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
        String result = sport.toStringDetailed();
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
}
