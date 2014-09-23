package net.shnee.bitys.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

// TODO add unit test for GenericEntity

/**
 * Shared code for all of the entities in the bitys model.
 */
@MappedSuperclass
abstract public class Entity {

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

}
