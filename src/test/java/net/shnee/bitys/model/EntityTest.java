package net.shnee.bitys.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
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
@RunWith(Parameterized.class)
public class EntityTest {

    private Entity entity;
    private Class<?> clazz;
    private Constructor<?> ctor;

    public EntityTest(Entity entity) throws NoSuchMethodException {
        this.entity = entity;
        this.clazz = entity.getClass();
        this.ctor = clazz.getConstructor();
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
    public void testGetAll() throws InstantiationException,
                                    IllegalAccessException,
                                    IllegalArgumentException,
                                    InvocationTargetException {
        Collection entities = Arrays.asList(
                this.ctor.newInstance(),
                this.ctor.newInstance(),
                this.ctor.newInstance()
                );

        Entity.saveOrUpdateAll(entities);
        List entitiesRetrieved = Entity.getAll(this.entity.getClass());
        assertEquals(entities, entitiesRetrieved);
    }

    // TODO See if you can use reflection to run Sports.getAll()

    /**
     * Test of getId method, of class Entity.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Entity instance = new EntityImpl();
        Integer expResult = null;
        Integer result = instance.getId();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
        //fail("The test case is a prototype.");
    }

    public class EntityImpl extends Entity {
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        Object[][] data = new Object[][] { {new Sport()} };
        return Arrays.asList(data);
    }
}
