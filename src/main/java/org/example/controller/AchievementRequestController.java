package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AchievementRequestDto;
import org.example.service.serviceInterface.AchievementRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/achievementRequests")
@RequiredArgsConstructor
public class AchievementRequestController {
    private final AchievementRequestService achievementRequestService;

    @PostMapping
    public void createAchievementRequest(@RequestBody AchievementRequestDto achievementRequestDto){
        achievementRequestService.createAchievementRequest(achievementRequestDto);
    }

    @DeleteMapping("/{achievementRequestId}")
    public void deleteById(@PathVariable("achievementRequestId") Long achievementRequestId){
        achievementRequestService.deleteById(achievementRequestId);
    }

    @PutMapping
    public void updateAchievementRequest(@RequestBody AchievementRequestDto achievementRequestDto){
        achievementRequestService.updateAchievementRequest(achievementRequestDto);
    }

    @GetMapping("/{achievementRequestId}")
    public AchievementRequestDto getById(@PathVariable("achievementRequestId") Long achievementRequestId){
        return achievementRequestService.getById(achievementRequestId);
    }
}
