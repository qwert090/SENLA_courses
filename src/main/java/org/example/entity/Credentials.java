package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "credentials")
public class Credentials extends AbstractEntity {

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @JoinColumn(name = "roles_id")
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;
}
