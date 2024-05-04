package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AchievementDto {
    private Long id;
    private String name;
    private String condition;
    private String platform;
    private Integer achievementExperience;
    private String type;
}
