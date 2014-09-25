package net.shnee.bitys.model;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

// TODO Implement EntityTest

/**
 * 
 */
public class EntityTest {
    
    public EntityTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAll method, of class Entity.
     */
    @Test
    public void testGetAll() {
        List<Sport> sports = Entity.getAll(Sport.class);
        System.out.println("Output: " + sports);
    }

    /**
     * Test of getId method, of class Entity.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Entity instance = new EntityImpl();
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Entity.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = null;
        Entity instance = new EntityImpl();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPluralName method, of class Entity.
     */
    @Test
    public void testGetPluralName() {
        System.out.println("getPluralName");
        Entity instance = new EntityImpl();
        String expResult = "";
        String result = instance.getPluralName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class EntityImpl extends Entity {
    }
}
