package net.shnee.bitys.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import net.shnee.bitys.db.Db;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
     * Create a new NamedEntity with an empty string for the name and null for
     * the id.
     */
    public NamedEntity() { this(""); }

    /**
     * Creates a new NamedEntity with the given name and null for the id.
     * @param name Name for the NamedEntity.
     */
    public NamedEntity(String name) { this.name = name; }

    @Override
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
     * @return Returns a list of saved objects of type type and name name.
     *         Returns null if there was an error.
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
            return null;
        } finally {
            if(session != null) { session.close(); }
        }
        return list;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NamedEntity)) { return false; }
        if (obj == this) { return true; }

        NamedEntity rhs = (NamedEntity) obj;
        return new EqualsBuilder().
                   appendSuper(super.equals(obj)).
                   append(this.name, rhs.name).
                   isEquals();
    }

}
