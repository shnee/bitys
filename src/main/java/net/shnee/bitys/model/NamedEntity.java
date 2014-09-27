package net.shnee.bitys.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import net.shnee.bitys.db.Db;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Models an entiry with a name.
 */
@MappedSuperclass
public class NamedEntity extends Entity {

    /** Name of the NamedEntity. */
    @Column(length=50, nullable=false)
    private String name;

    /**
     * Create a new NamedEntity with an empty string for the name and -1 for the
     * id.
     */
    public NamedEntity() { this(-1, ""); }

    /**
     * Creates a new NamedEntity with the given name and an id of -1.
     * @param name Name for the NamedEntity.
     */
    public NamedEntity(String name) { this(-1, name); }

    /**
     * Creates a new NamedEntity with the given id and name.
     * @param id   Unique identifier of the NamedEntity.
     * @param name Name for the NamedEntity.
     */
    public NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    /**
     * name getter.
     * @return Returns the name of the NamedEntity.
     */
    public String getName() { return this.name; }

    /**
     * name setter.
     * @param name New name for NamedEntity.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Return all saved objects of type type with name name.
     * @param <T>  Type of objects to retrieve.
     * @param type Type of objects to retrieve.
     * @param name Name of objects to retrieve.
     * @return Reurns a list of saved objects of type type and name name.
     */
    public static <T> List<T> getByName(Class<T> type, String name) {
        Session session = null;
        Transaction tx  = null;
        List<T> list    = null;
        try {
            session = Db.getInstance().createSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from " + type.getName() +
                                              " where name = :name");
            query.setParameter("name", name);
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

}
