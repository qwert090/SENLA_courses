package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "credentials")
@NoArgsConstructor
@AllArgsConstructor
public class Credentials extends AbstractEntity {

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
}
