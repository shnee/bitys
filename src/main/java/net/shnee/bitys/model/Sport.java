package net.shnee.bitys.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents a real world sport. Each sport has a unique identifier and a name.
 */
@Entity
@Table(name="Sports")
public class Sport extends NamedEntity implements Serializable {

    /**
     * Create a new Sport with an empty string for the name and -1 for the
     * id.
     */
    public Sport() { super(); }

    /**
     * Creates a new Sport with the given name and an id of -1.
     * @param name Name for the Sport.
     */
    public Sport(String name) { super(name); }

    /**
     * Creates a new Sport with the given id and name.
     * @param id   Unique identifier of the Sport.
     * @param name Name for the Sport.
     */
    public Sport(Integer id, String name) { super(id, name); }

    /**
     * Get all saved Sport objects.
     * @return Returns a list of all saved Sport objects.
     */
    public static List<Sport> getAll() {
        return net.shnee.bitys.model.Entity.getAll(Sport.class);
    }

    /**
     * java.lang.Object.toString() but with more details.
     * @return Returns a detailed String representation of the Sport.
     */
    public String toStringDetailed() {
        return "Sport: id=" + this.getId() + ", name=" + this.getName();
    }

}
