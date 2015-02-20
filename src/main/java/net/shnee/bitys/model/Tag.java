package net.shnee.bitys.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import net.shnee.bitys.db.Db;
import net.shnee.bitys.logger.BitysLogger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.slf4j.Logger;

/**
 * TODO
 * @author shnee
 */
@Entity 
@Table(name="Tags")
public class Tag extends NamedEntity implements Serializable {

    private static final Logger log = BitysLogger.MODEL;

    /* This Cascade stuff is crap, it sucks, it makes no sense. Whats the point
     * of using a framework if the crap doesnt work!! */
    @ManyToOne
    @JoinColumn(name="parentId")
    @Cascade({CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    private Tag parent;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    //@Cascade(CascadeType.REMOVE)
    private Set<Team> teams = null;

    /**
     * TODO
     */
    public Tag() {
        super("", "");
        this.parent = null;
        this.parent = null;
    }

    /**
     * TODO
     * @param name
     * @param abbrev
     * @param parent
     */
    public Tag(String name, String abbrev, Tag parent) {
        super(name, abbrev);
        this.parent = null;
        this.parent = parent;
    }

    public static List<Tag> getByName(String name) {
        return NamedEntity.getByName(Tag.class, name);
    }

    /**
     * Delete every instance of Tag.
     * @return Returns the number of instances deleted.
     */
    public static Integer deleteAll() {
        Tag.log.info("Deleting all instances of " +
                     Tag.class.getSimpleName());

        /* We can't use the normal method calling Entity.deleteAll because Tag
         * has a self reference. Therefore we must delete the parent tags
         * last. */
        /** TODO this statement is inaccurate, Entity.deleteAll will not be able
         *  to delete anything that is referenced by a foreign key. */
        Session session    = null;
        Transaction tx     = null;
        Integer numDeleted = 0;

        session = Db.getInstance().createSession();
        tx = session.beginTransaction();
        //Query query = session.createQuery("FROM " + Tag.class.getSimpleName());
        Query query = session.createQuery("FROM " + Tag.class.getSimpleName() + " T WHERE T.id > 1");
        query.setMaxResults(1);

        List<Tag> list = query.list();
        while(!list.isEmpty()) {
            Tag tag = list.get(0);
            numDeleted += tag.deleteChildren(session) + 1;
            session.delete(tag);
            list = query.list();
        }

        tx.commit();
        session.close();

        return numDeleted;
    }

    /**
     * TODO
     * @param session
     * @return
     */
    private Integer deleteChildren(Session session) {
        Integer rv = 0;

        Set<Tag> children = this.getChildren();
        if(children.isEmpty()) {
            rv = 0;
        } else if(children.size() == 1) {
            rv = 1;
            session.delete(children.toArray()[0]);
        } else {
            for(Tag tag : children) {
                rv += tag.deleteChildren(session) + 1;
                session.delete(tag);
            }
        }

        return rv;
    }

    /**
     * Get all Tags that have this tag as a parent.
     * @return Returns a set of Tags that have this tag as a parent.
     */
    public Set<Tag> getChildren() {
        // TODO implement this method with a generic query method
        Session session = null;
        Transaction tx  = null;
        List<Tag> list  = null;
        String queryStr = "FROM " + Tag.class.getSimpleName() + " T " +
                          "WHERE T.parent.id = :parentId";
        try {
            session = Db.getInstance().createSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(queryStr);
            query.setParameter("parentId", this.getId());
            list = query.list();
            tx.commit();
        } catch(HibernateException ex) {
            Tag.log.error("Error executing query: " + queryStr +
                          " ex: " + ex.getMessage());
            tx.rollback();
            return null;
        } finally {
            if(session != null) { session.close(); }
        }
        return new HashSet<Tag>(list);
    }

}
