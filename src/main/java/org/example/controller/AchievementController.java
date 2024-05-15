package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AchievementDto;
import org.example.service.serviceInterface.AchievementService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/achievements")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementService achievementService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public void createAchievement(@RequestBody AchievementDto achievementDto){
        achievementService.createAchievement(achievementDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{achievementId}")
    public void deleteById(@PathVariable("achievementId") Long achievementId){
        achievementService.deleteById(achievementId);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping
    public void updateAchievement(@RequestBody AchievementDto achievementDto){
        achievementService.updateAchievement(achievementDto);
    }

    @GetMapping("/{achievementId}")
    public AchievementDto getById(@PathVariable("achievementId") Long achievementId){
        return achievementService.getById(achievementId);
    }
}
