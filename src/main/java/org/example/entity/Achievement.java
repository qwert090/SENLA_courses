package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "achievement")
@NoArgsConstructor
@AllArgsConstructor
public class Achievement extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "condition")
    private String condition;

    @Column(name = "platform")
    private String platform;

    @Column(name = "experience")
    private int experience;

    @Column(name = "type")
    private String type;

    @JoinColumn(name = "users_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User users;

    @JoinColumn(name = "game_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
}
