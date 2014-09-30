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
        Entity.removeAll(this.clazz);
    }

    @After
    public void tearDown() {
    }

    // TODO add test for saveAndUpdate and saveAndUpdateAll.

    /**
     * Test of getAll method, of class Entity.
     */
    @Test
    public void testGetAll() throws InstantiationException,
                                    IllegalAccessException,
                                    IllegalArgumentException,
                                    InvocationTargetException {
        /* TODO Find a way to test when a exception is thrown in this method and
         *      the others that throw an exception. */
        Collection entities = Arrays.asList(
                this.ctor.newInstance(),
                this.ctor.newInstance(),
                this.ctor.newInstance()
                );

        Entity.saveOrUpdateAll(entities);
        List entitiesRetrieved = Entity.getAll(this.clazz);
        assertEquals(entities, entitiesRetrieved);
    }

    /**
     * Test getById method, of cass Entity.
     */
    @Test
    public void testGetById() throws InstantiationException,
                                     IllegalAccessException,
                                     IllegalArgumentException,
                                     InvocationTargetException {
        Collection entities = Arrays.asList(
                this.ctor.newInstance(),
                this.ctor.newInstance(),
                this.ctor.newInstance()
                );

        Entity.saveOrUpdateAll(entities);
        List retrieved = Entity.getAll(this.clazz);

        Entity ent1 = (Entity) retrieved.get(0);
        Entity ent2 = (Entity) retrieved.get(1);
        Entity ent3 = (Entity) retrieved.get(2);

        assertEquals(ent1, Entity.getById(this.clazz, ent1.getId()));
        assertEquals(ent2, Entity.getById(this.clazz, ent2.getId()));
        assertEquals(ent3, Entity.getById(this.clazz, ent3.getId()));

        assertEquals(null, Entity.getById(this.clazz, 13));
    }

    /**
     * Tests the removeAll method, of class Entity.
     */
    @Test
    public void testRemoveAll() throws InstantiationException,
                                       IllegalAccessException,
                                       IllegalArgumentException,
                                       InvocationTargetException {
        Collection entities = Arrays.asList(
                this.ctor.newInstance(),
                this.ctor.newInstance(),
                this.ctor.newInstance()
                );

        Entity.saveOrUpdateAll(entities);
        Integer size = Entity.getAll(this.entity.getClass()).size();
        assertTrue(size > 0);
        Entity.removeAll(this.clazz);
        size = Entity.getAll(this.entity.getClass()).size();
        assertEquals(new Integer(0), size);
    }

    /**
     * Test of setId and getId methods, of class Entity.
     */
    @Test
    public void testSetAndGetId() {
        Entity ent = new EntityImpl();

        assertEquals(new Integer(-1), ent.getId());
        ent.setId(68);
        assertEquals(new Integer(68), ent.getId());
        ent.setId(54);
        assertEquals(new Integer(54), ent.getId());
    }

    /**
     * Test the toString method, of class Entity.
     */
    @Test
    public void testToString() {
        Entity ent1 = new EntityImpl();
        Entity ent2 = new EntityImpl();
        String str1 = "Entity: id=13";
        String str2 = "Entity: id=17";

        assertEquals(ent1.toString(), ent2.toString());
        ent1.setId(13);
        assertEquals(str1, ent1.toString());
        assertNotEquals(ent1.toString(), ent2.toString());
        ent2.setId(13);
        assertEquals(ent1.toString(), ent2.toString());
        ent1.setId(17);
        assertEquals(str2, ent1.toString());
        assertNotEquals(ent1.toString(), ent2.toString());
        ent2.setId(17);
        assertEquals(ent1.toString(), ent2.toString());
    }

    /**
     * Test toDetailedString method, of class Entity.
     */
    @Test
    public void testToDetailedString() {
        Entity ent1 = new EntityImpl();
        Entity ent2 = new EntityImpl();
        String str1 = "Entity: id=13";
        String str2 = "Entity: id=17";

        assertEquals(ent1.toDetailedString(), ent2.toDetailedString());
        ent1.setId(13);
        assertEquals(str1, ent1.toDetailedString());
        assertNotEquals(ent1.toDetailedString(), ent2.toDetailedString());
        ent2.setId(13);
        assertEquals(ent1.toDetailedString(), ent2.toDetailedString());
        ent1.setId(17);
        assertEquals(str2, ent1.toDetailedString());
        assertNotEquals(ent1.toDetailedString(), ent2.toDetailedString());
        ent2.setId(17);
        assertEquals(ent1.toDetailedString(), ent2.toDetailedString());
        assertEquals(ent1.toDetailedString(), ent1.toString());
        assertEquals(ent1.toDetailedString(), ent2.toString());
    }

    /**
     * Test hashCode method, of class Entity.
     */
    @Test
    public void testHashCode() {
        Entity ent1 = new EntityImpl();
        Entity ent2 = new EntityImpl();

        assertEquals(ent1.hashCode(), ent2.hashCode());
        ent2.setId(13);
        assertNotEquals(ent1.hashCode(), ent2.hashCode());
        ent1.setId(13);
        assertEquals(ent1.hashCode(), ent2.hashCode());
    }

    /**
     * Test equals method, of class Entity.
     */
    @Test
    public void testEquals() {
        Entity ent1 = new EntityImpl();
        Entity ent2 = new EntityImpl();
        String str = "Not an Entity";

        assertEquals(ent1, ent2);
        ent1.setId(13);
        assertNotEquals(ent1, ent2);
        ent2.setId(13);
        assertEquals(ent1, ent2);
        assertNotEquals(ent1, str);
        assertEquals(ent1, ent1);
    }

    public class EntityImpl extends Entity {
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        Object[][] data = new Object[][] { {new Sport()} };
        return Arrays.asList(data);
    }
}
