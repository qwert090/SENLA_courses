package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity{

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "description")
    private String description;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "total_experience")
    private Integer totalExp;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Role> roles;

}
