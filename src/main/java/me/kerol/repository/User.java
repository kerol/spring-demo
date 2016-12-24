package me.kerol.repository;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kevin on 24/12/2016.
 */
@Entity
@Table(name = "users", indexes = {
        @Index(name = "user_name", columnList = "name", unique = true)})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;

    public User() {
        super();
    }
    public User(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getEmail() {
        return this.email;
    }
}
