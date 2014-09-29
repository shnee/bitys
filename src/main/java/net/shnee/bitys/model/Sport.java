package net.shnee.bitys.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

/* TODO Should name be unique? is there a way to enforce the uniqueness if its
 *      not unique in the parent class. */

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
    
    // TODO add getById method.
    // TODO move common methods to parent classes as abstract methods.

    /**
     * Save the Sport if it's new or update it if it already exists.
     * @return The id of the Sport after it's been saved/updated.
     */
    public Integer saveOrUpdate() {
        return net.shnee.bitys.model.Entity.saveOrUpdate(this);
    }

    /**
     * Get all saved Sport objects.
     * @return Returns a list of all saved Sport objects.
     */
    public static List<Sport> getAll() {
        return net.shnee.bitys.model.Entity.getAll(Sport.class);
    }

    /**
     * Get the all Sports with a given name.
     * @param name The name of the Sports to retreive.
     * @return A List of all Sports with a given name.
     */
    public static List<Sport> getByName(String name) {
        return NamedEntity.getByName(Sport.class, name);
    }

    // TODO change the name of all the removeAlls to deleteAll
    /**
     * Deletes all the saved Sports.
     * @return The number of Sports deleted.
     */
    public static Integer removeAll() {
        return net.shnee.bitys.model.Entity.removeAll(Sport.class);
    }

    /**
     * java.lang.Object.toString() but with more details.
     * @return Returns a detailed String representation of the Sport.
     */
    public String toStringDetailed() {
        return "Sport: id=" + this.getId() + ", name=" + this.getName();
    }

    /**
     * Get the plural form of the class.
     * @return Returns the plural form of the class as a String.
     */
    public static String getPluralName() {
        return Sport.class.getSimpleName() + "s";
    }

}
