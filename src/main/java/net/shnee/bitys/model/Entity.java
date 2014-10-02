package net.shnee.bitys.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import net.shnee.bitys.db.Db;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Shared code for all of the entities in the bitys model.
 */
@MappedSuperclass
abstract public class Entity implements Serializable {

    /**
     * Unique identifier for an enitity. id is unique per entitity type and only
     * among those entities that have been saved.
     */
    @Id @GeneratedValue
    private Integer id;

    /**
     * Empty constructor. Assigns a negative value to id. The id of a saved
     * entity can never be negative.
     */
    public Entity() {
        this(-1);
    }

    /**
     * Creates a new entity with a given identifier.
     * @param id Identifier for newly created entity.
     */
    public Entity(Integer id) {
        this.id = id;
    }

    /**
     * Saves the given object.
     * @param obj The object to be saved.
     * @return Returns generated identifier. Returns -1 if there was an error.
     */
    public static Integer saveOrUpdate(Entity entity) {
        Session session = null;
        Transaction tx = null;

        try {
            session = Db.getInstance().createSession();
            tx = session.beginTransaction();

            session.saveOrUpdate(entity);

            tx.commit();
        } catch(HibernateException ex) {
            // TODO add logging statement
            tx.rollback();
            return -1;
        } finally {
            if(session != null) { session.close(); }
        }
        return entity.getId();
    }

    /**
     * Save or update all the objects in entities.
     * @param <T>      Type of the objcts in entities.
     * @param entities Objects to be saved or updated.
     * @return Returns the number of objects saved or updated.
     */
    public static <T> Integer saveOrUpdateAll(Collection<T> entities) {
        Integer numSaved = 0;
        Session session = null;
        Transaction tx  = null;

        try {
            session = Db.getInstance().createSession();
            tx = session.beginTransaction();
            for(T entity : entities) {
                session.saveOrUpdate(entity);
                numSaved++;
            }
            tx.commit();
        } catch(HibernateException ex) {
            // TODO add logging statement
            tx.rollback();
            return -1;
        } finally {
            if(session != null) { session.close(); }
        }

        return numSaved;
    }

    /**
     * Return all saved objects of type type.
     * @param <T>  Type of object to retrieve.
     * @param type Type of object to retrieve.
     * @return Reurns a list of saved objects of type type.
     */
    public static <T> List<T> getAll(Class<T> type) {
        Session session = null;
        Transaction tx  = null;
        List<T> list    = null;
        try {
            session = Db.getInstance().createSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM " + type.getName());
            list = query.list();
            tx.commit();
        } catch(HibernateException ex) {
            // TODO add logging statements
            tx.rollback();
        } finally {
            if(session != null) { session.close(); }
        }
        return list;
    }

    /**
     * Get the object with the given id and of the given type.
     * @param <T> Type of the object to retrieve.
     * @param type Type of the object to retrieve.
     * @param id Id of the object to retrieve.
     * @return Returns the saved object with the given id of the given type.
     *         Returns null if the object doesn't exist.
     */
    public static <T> T getById(Class<T> type, Integer id) {
        Session session = null;
        Transaction tx  = null;
        List<T> list    = null;
        try {
            session = Db.getInstance().createSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from " + type.getName() +
                                              " where id = :id");
            query.setParameter("id", id);
            list = query.list();
            tx.commit();
        } catch(HibernateException ex) {
            // TODO add logging statements
            tx.rollback();
            return null;
        } finally {
            if(session != null) { session.close(); }
        }
        return (list.size() > 0 ? list.get(0) : null);
    }

    /**
     * Remove all saved objects of type.
     * @param <T>  Type of the objects to be deleted.
     * @param type Type of the objects to be deleted.
     * @return Returns the number of objects deleted.
     */
    public static <T> Integer removeAll(Class<T> type) {
        Session session = null;
        Transaction tx  = null;
        Integer numActions;

        session = Db.getInstance().createSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM " + type.getName());
        numActions = query.executeUpdate();
        tx.commit();
        session.close();

        return numActions;
    }

    /**
     * id getter.
     * @return Returns the unique identifier for this entity type.
     */
    public Integer getId() { return this.id; }

    /**
     * id setter.
     * @param id The new identifier for this type. If this id is not unique for
     *           this entity type then a new unique identifier will be assigned
     *           when the entity is saved.
     */
    public void setId(Integer id) { this.id = id; }

    @Override
    public String toString() {
        return "Entity: id=" + this.id;
    }

    /**
     * This is a toString method with more details, including most of the
     * private fields.
     * @return Returns a detailed represention of the Entity as a String.
     */
    public String toDetailedString() {
        return this.toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 37).append(this.id).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Entity)) { return false; }
        if (obj == this) { return true; }

        Entity rhs = (Entity) obj;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }
}
