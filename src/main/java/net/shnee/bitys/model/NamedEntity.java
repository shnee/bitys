package net.shnee.bitys.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
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

}
