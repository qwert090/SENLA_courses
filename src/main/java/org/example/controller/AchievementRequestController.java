package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AchievementRequestDto;
import org.example.service.serviceInterface.AchievementRequestService;
import org.example.utils.JsonMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AchievementRequestController {
    private final AchievementRequestService achievementRequestService;
    private final JsonMapper json;

    public void createAchievementRequest(String serializedAchievementRequest){
        AchievementRequestDto achievementRequestDto = json.deserialize(serializedAchievementRequest, AchievementRequestDto.class);
        achievementRequestService.createAchievementRequest(achievementRequestDto);
    }

    public void deleteById(long achievementRequestId){
        achievementRequestService.deleteById(achievementRequestId);
    }

    public void updateAchievementRequest(String serializedAchievementRequest){
        AchievementRequestDto achievementRequestDto = json.deserialize(serializedAchievementRequest, AchievementRequestDto.class);
        achievementRequestService.updateAchievementRequest(achievementRequestDto);
    }

    public AchievementRequestDto getById(long achievementRequestId){
        return achievementRequestService.getById(achievementRequestId);
    }
}
