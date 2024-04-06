package org.example.service.serviceInterface;

import org.example.dto.AchievementRequestDto;

public interface AchievementRequestService {

    void createAchievementRequest(AchievementRequestDto achievementRequestDto);

    void deleteById(long id);

    AchievementRequestDto getById(long id);

    void updateAchievementRequest(AchievementRequestDto achievementRequestDto);
}
