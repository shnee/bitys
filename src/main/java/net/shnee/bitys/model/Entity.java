package net.shnee.bitys.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import net.shnee.bitys.db.Db;
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
            Query query = session.createQuery("from " + type.getName());
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
     * Unique identifier for an enitity. id is unique per entitity type and only
     * among those entities that have been saved.
     */
    @Id @GeneratedValue
    private Integer id;

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

    protected String getPluralName() {
        String pluralForm = this.getClass().getSimpleName() + "s";
        return pluralForm;
    }

}
