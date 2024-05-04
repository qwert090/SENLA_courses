package org.example.service.serviceInterface;

import org.example.dto.AchievementRequestDto;

public interface AchievementRequestService {

    void createAchievementRequest(AchievementRequestDto achievementRequestDto);

    void deleteById(Long id);

    AchievementRequestDto getById(Long id);

    void updateAchievementRequest(AchievementRequestDto achievementRequestDto);
}
