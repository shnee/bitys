package net.shnee.bitys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents a real world sport. Each sport has a unique identifier and a name.
 */
@Entity
@Table(name="Sports")
public class Sport extends GenericEntity implements java.io.Serializable {
    
    /**
     * Name of the sport.
     */
    @Column(length=50, nullable=false)
    private String  name;
    
    /**
     * Create a new sport with an empty string for the name and -1 for the id.
     */
    public Sport() {
        this(-1, "");
    }
    
    /**
     * Creates a new sport with the given name and an id of -1.
     * @param name Name for the Sport.
     */
    public Sport(String name) {
        this(-1, name);
    }
    
    /**
     * Creates a new sport with the given id and name.
     * @param id   Unique identifier of the Sport.
     * @param name Name for the Sport.
     */
    public Sport(final Integer id, final String name) {
        super(id);
        this.name = name;
    }
    
    /**
     * Converts Sport to a String.
     * @return Returns a String representation of Sport.
     */
    public String toString() {
        return "Sport: id=" + this.getId() + ", name=" + this.name;
    }
    
    /**
     * name getter.
     * @return Returns the name of the Sport.
     */
    public String getName() { return this.name; }
    
    /**
     * name setter.
     * @param name New name for Sport.
     */
    public void setName(String name) { this.name = name; }
}
