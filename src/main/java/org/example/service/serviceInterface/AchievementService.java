package org.example.service.serviceInterface;

import org.example.dto.AchievementDto;

public interface AchievementService {

    void createAchievement(AchievementDto achievementDto);

    void deleteById(Long id);

    AchievementDto getById(Long id);

    void updateAchievement(AchievementDto achievementDto);
}
