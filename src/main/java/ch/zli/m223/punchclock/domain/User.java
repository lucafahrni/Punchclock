package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @author Luca Fahrni
 * @version 25.11.2020
 * @Class ApplicationUser
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String username;

    @OneToMany
    private List<Entry> entries;

    @Column(nullable = false)
    private String role;


    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}