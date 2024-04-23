package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "game")
public class Game extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "category_id")
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> category;
}
