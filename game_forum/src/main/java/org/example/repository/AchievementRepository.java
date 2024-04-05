package org.example.repository;

import lombok.Getter;
import org.example.entity.Achievement;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
public class AchievementRepository {
    private List<Achievement> achievements = new ArrayList<>();

    public void create(Achievement achievement){
        achievements.add(achievement);
    }

    public void delete(long achievementId){
        achievements = achievements.stream()
                .filter(achievement -> achievement.getId() != achievementId)
                .toList();
    }

    public Optional<Achievement> read(long achievementId){
        Optional<Achievement> readAchievement = achievements.stream()
                .filter(id -> id.getId() == achievementId)
                .findFirst();
        return readAchievement;
    }
}
