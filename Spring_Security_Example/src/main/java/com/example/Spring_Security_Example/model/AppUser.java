package com.example.Spring_Security_Example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid4")
    @Column(length = 16)
    private UUID id;
    private String email;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<UserRole> roles;

    public AppUser(String email, String username, String password) {
        this();
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();
    }
}
