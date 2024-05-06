package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AchievementDto;
import org.example.entity.Achievement;
import org.example.exception.EntityNotFoundException;
import org.example.repository.impl.AchievementRepository;
import org.example.service.serviceInterface.AchievementService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository achievementRepository;
    private final CustomMapper mapper;

    @Override
    public void createAchievement(AchievementDto achievementDto) {
        Achievement achievement = mapper.toEntity(Achievement.class, achievementDto);
        achievementRepository.save(achievement);

    }

    @Override
    public void deleteById(Long id) {
        achievementRepository.deleteById(id);

    }

    @Override
    public AchievementDto getById(Long id) {
        Achievement achievement = achievementRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Achievement not found"));
        return mapper.toDto(AchievementDto.class, achievement);
    }

    @Override
    public void updateAchievement(AchievementDto achievementDto) {
        Achievement updateAchievement = mapper.toEntity(Achievement.class, achievementDto);
        achievementRepository.update(updateAchievement);
    }
}
