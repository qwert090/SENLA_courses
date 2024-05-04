package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AchievementDto;
import org.example.service.serviceInterface.AchievementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/achievements")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementService achievementService;

    @PostMapping
    public void createAchievement(@RequestBody AchievementDto achievementDto){
        achievementService.createAchievement(achievementDto);
    }

    @DeleteMapping("/{achievementId}")
    public void deleteById(@PathVariable("achievementId") Long achievementId){
        achievementService.deleteById(achievementId);
    }

    @PutMapping
    public void updateAchievement(@RequestBody AchievementDto achievementDto){
        achievementService.updateAchievement(achievementDto);
    }

    @GetMapping("/{achievementId}")
    public AchievementDto getById(@PathVariable("achievementId") Long achievementId){
        return achievementService.getById(achievementId);
    }
}
