package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.AchievementDto;
import org.example.entity.Achievement;
import org.example.repository.AchievementRepository;
import org.example.service.serviceInterface.AchievementService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

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
    public void deleteById(long id) {
        achievementRepository.deleteById(id);

    }

    @Override
    public AchievementDto getById(long id) {
        Achievement achievement = (Achievement) achievementRepository.findById(id);
        return mapper.toDto(AchievementDto.class, achievement);
    }

    @Override
    public void updateAchievement(AchievementDto achievementDto) {
        achievementRepository.findById(achievementDto.getId());
        Achievement updateAchievement = mapper.toEntity(Achievement.class, achievementDto);
        achievementRepository.update(updateAchievement);
    }
}
