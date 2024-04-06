package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class User {
    private long id;
    private String nickname;
    private String description;
    private String avatar;
    private int totalExp;
    private Rank rank;
    private Credentials credentials;
    private List<Post> posts;
    private List<Comment> comments;
    private List<AchievementRequest> achievementRequests;
    private List<User> followers;
    private List<Achievement> achievements;
}
