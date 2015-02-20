package net.shnee.bitys.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import net.shnee.bitys.logger.BitysLogger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.slf4j.Logger;

/**
 * TODO
 * @author shnee
 */
@Entity
@Table(name="Teams")
public class Team extends NamedEntity implements Serializable {

    private static final Logger log = BitysLogger.MODEL;

    /** TODOs */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="TeamTags",
               joinColumns={@JoinColumn(name="teamId")},
               inverseJoinColumns={@JoinColumn(name="tagId")})
    @Cascade(CascadeType.DELETE_ORPHAN)
    private Set<Tag> tags = new HashSet<>();

    /**
     * TODO
     * @param name
     * @param abbrev
     */
    public Team(String name, String abbrev) {
        super(name, abbrev);
    }

    /**
     * Get all of the Tags that have been directly applied to this team. This
     * method does not take into account a Tag's parent Tag.
     * @return Returns the Tags applied to this Team.
     */
    public Set<Tag> getTags() {
        return this.tags;
    }
    
    /**
     * TODO
     * @param tag
     * @return 
     */
    public boolean addTag(Tag tag) {
        return this.tags.add(tag);
    }
    
    /**
     * TODO
     * The Set has a List for each Tag directly assigned to Team. The List
     * contains the Tag directly assigned first and then the hierarchy of parent
     * Tags.
     * @return 
     */
    public Set<List<Tag>> getAllTags() {
        HashSet<List<Tag>> tags = new HashSet<>();
        
        
        
        return tags;
    }

    /**
     * TODO
     * @return
     */
    public static Integer deleteAll() {
        Team.log.info("Deleting all instances of " +
                      Team.class.getSimpleName());

        return net.shnee.bitys.model.Entity.deleteAll(Team.class);
    }

}