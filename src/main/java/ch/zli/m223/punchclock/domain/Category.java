package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @author Luca Fahrni
 * @version 25.11.2020
 * @Class Category
 */
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    private List<Entry> entries;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Entry> getEntries() {
        return entries;
    }
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

}
