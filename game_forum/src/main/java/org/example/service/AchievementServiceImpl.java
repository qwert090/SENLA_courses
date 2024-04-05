package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.AchievementDto;
import org.example.entity.Achievement;
import org.example.exception.EntityNotFoundException;
import org.example.repository.AchievementRepository;
import org.example.service.serviceInterface.AchievementService;
import org.example.utils.Mapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository achievementRepository;
    private final Mapper mapper;

    @Override
    public void createAchievement(AchievementDto achievementDto) {
        Achievement achievement = mapper.toEntity(Achievement.class, achievementDto);
        achievementRepository.create(achievement);

    }

    @Override
    public void deleteById(long id) {
        achievementRepository.delete(id);

    }

    @Override
    public AchievementDto getById(long id) {
        Achievement achievement = achievementRepository.read(id)
                .orElseThrow(() -> new EntityNotFoundException("no such achievement"));
        System.out.println(achievementRepository.getAchievements().stream()
                .filter(achievement1 -> achievement1.getId() == id)
                .toList()
        );
        return mapper.toDto(AchievementDto.class, achievement);
    }

    @Override
    public void updateAchievement(AchievementDto achievementDto) {
        Achievement achievement = achievementRepository.read(achievementDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("no such achievement"));
        achievement.setName(achievementDto.getName());
        achievement.setCondition(achievementDto.getCondition());
        achievement.setPlatform(achievementDto.getPlatform());
        achievement.setAchievementExperience(achievementDto.getAchievementExperience());
        achievement.setType(achievementDto.getType());

    }
}
