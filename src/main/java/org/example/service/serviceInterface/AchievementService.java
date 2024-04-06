package org.example.service.serviceInterface;

import org.example.dto.AchievementDto;

public interface AchievementService {

    void createAchievement(AchievementDto achievementDto);

    void deleteById(long id);

    AchievementDto getById(long id);

    void updateAchievement(AchievementDto achievementDto);
}
