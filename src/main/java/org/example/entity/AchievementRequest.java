package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AchievementRequest {
    private long id;
    private String gameName;
    private String achievementName;
    private String platform;
    private User user;
}
