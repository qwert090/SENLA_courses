package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AchievementDto;
import org.example.service.serviceInterface.AchievementService;
import org.example.utils.Json;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementService achievementService;
    private final Json json;

    public void createAchievement(String serializedAchievement){
        AchievementDto achievementDto = json.deserialize(serializedAchievement, AchievementDto.class);
        achievementService.createAchievement(achievementDto);
    }

    public void deleteById(long achievementId){
        achievementService.deleteById(achievementId);
    }

    public void updateAchievement(String serializedAchievement){
        AchievementDto achievementDto = json.deserialize(serializedAchievement, AchievementDto.class);
        achievementService.updateAchievement(achievementDto);
    }

    public AchievementDto getById(long achievementId){
        return achievementService.getById(achievementId);
    }
}
