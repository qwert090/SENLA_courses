package org.example.service;

import org.example.config.ApplicationConfigTest;
import org.example.dto.AchievementDto;
import org.example.entity.Achievement;
import org.example.repository.impl.AchievementRepository;
import org.example.utils.CustomMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(MockitoExtension.class)
public class AchievementServiceTest {
    @Mock
    private AchievementRepository achievementRepository;

    @Mock
    private CustomMapper mapper;

    @InjectMocks
    private AchievementServiceImpl achievementService;

    @Test
    public void createAchievementTest() {
        AchievementDto achievementDto = new AchievementDto();
        Achievement achievement = new Achievement();
        when(mapper.toEntity(Achievement.class, achievementDto)).thenReturn(achievement);
        achievementService.createAchievement(achievementDto);
        verify(achievementRepository, times(1)).save(achievement);
    }

    @Test
    public void getByIdTest() {
        long achievementId = 1L;
        Achievement achievement = new Achievement();
        AchievementDto achievementDto = new AchievementDto();
        when(achievementRepository.findById(achievementId)).thenReturn(Optional.of(achievement));
        when(mapper.toDto(AchievementDto.class, achievement)).thenReturn(achievementDto);
        AchievementDto result = achievementService.getById(achievementId);
        assertSame(achievementDto, result);
    }

    @Test
    public void deleteByIdTest() {
        long achievementId = 1L;
        achievementService.deleteById(achievementId);
        verify(achievementRepository, times(1)).deleteById(achievementId);
    }

    @Test
    public void updateAchievementTest() {
        AchievementDto achievementDto = new AchievementDto();
        Achievement updateAchievement = new Achievement();
        when(mapper.toEntity(Achievement.class, achievementDto)).thenReturn(updateAchievement);
        achievementService.updateAchievement(achievementDto);
        verify(achievementRepository, times(1)).update(updateAchievement);
    }
}
