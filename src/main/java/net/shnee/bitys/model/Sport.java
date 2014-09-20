package net.shnee.bitys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name="Sports")
public class Sport implements java.io.Serializable {
    @Id @GeneratedValue
    private Integer id;
    
    @Column(length=50)
    private String  name;
    
    public Sport() {
        this(-1, "");
    }
    
    public Sport(String name) {
        this(-1, name);
    }
    
    public Sport(final Integer id, final String name) {
        this.id   = id;
        this.name = name;
    }
    
    public Integer getId() { return this.id; }
    
    public void setId(Integer id) { this.id = id; }
    
    public String getName() { return this.name; }
    
    public void setName(String name) { this.name = name; }
}
