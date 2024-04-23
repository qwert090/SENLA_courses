package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ranks")
public class Rank extends AbstractEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
