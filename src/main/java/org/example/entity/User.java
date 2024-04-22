package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "description")
    private String description;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "total_experience")
    private int totalExp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ranks_id")
    private Rank rank;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "credentials_id", nullable = false)
    private Credentials credentials;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "users")
    private List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "users")
    private List<AchievementRequest> achievementRequests;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_id")
    private List<Achievement> achievements;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private List<User> follower;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_id")
    private List<User> followed;
}
