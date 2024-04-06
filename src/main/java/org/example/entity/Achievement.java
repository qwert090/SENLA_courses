package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Achievement {
    private long id;
    private String name;
    private Game game;
    private String condition;
    private String platform;
    private int achievementExperience;
    private String type;
    private List<User> users;
}
