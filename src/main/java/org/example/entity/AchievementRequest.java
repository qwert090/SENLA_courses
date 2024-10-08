package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "achievement_request")
@NoArgsConstructor
@AllArgsConstructor
public class AchievementRequest extends AbstractEntity {

    @Column(name = "game_name")
    private String gameName;

    @Column(name = "achievement_name")
    private String achievementName;

    @Column(name = "platform")
    private String platform;

    @JoinColumn(name = "users_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User users;
}
