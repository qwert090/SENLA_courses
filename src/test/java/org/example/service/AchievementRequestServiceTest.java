package org.example.service;

import org.example.config.ApplicationConfigTest;
import org.example.dto.AchievementRequestDto;
import org.example.entity.AchievementRequest;
import org.example.repository.impl.AchievementRequestRepository;
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
public class AchievementRequestServiceTest {
    @Mock
    private AchievementRequestRepository achievementRequestRepository;

    @Mock
    private CustomMapper mapper;

    @InjectMocks
    private AchievementRequestServiceImpl achievementRequestService;

    @Test
    public void createAchievementRequestTest() {
        AchievementRequest achievementRequest = new AchievementRequest();
        AchievementRequestDto achievementRequestDto = new AchievementRequestDto();
        when(mapper.toEntity(AchievementRequest.class, achievementRequestDto)).thenReturn(achievementRequest);
        achievementRequestService.createAchievementRequest(achievementRequestDto);
        verify(achievementRequestRepository, times(1)).save(achievementRequest);
    }

    @Test
    public void updateAchievementRequestTest() {
        AchievementRequest achievementRequest = new AchievementRequest();
        AchievementRequestDto achievementRequestDto = new AchievementRequestDto();
        when(mapper.toEntity(AchievementRequest.class, achievementRequestDto)).thenReturn(achievementRequest);
        achievementRequestService.updateAchievementRequest(achievementRequestDto);
        verify(achievementRequestRepository, times(1)).update(achievementRequest);
    }

    @Test
    public void getAchievementRequestByIdTest() {
        long achievementRequestId = 1L;
        AchievementRequest achievementRequest = new AchievementRequest();
        AchievementRequestDto achievementRequestDto = new AchievementRequestDto();
        when(mapper.toDto(AchievementRequestDto.class, achievementRequest)).thenReturn(achievementRequestDto);
        when(achievementRequestRepository.findById(achievementRequestId)).thenReturn(Optional.of(achievementRequest));
        AchievementRequestDto result = achievementRequestService.getById(achievementRequestId);
        assertSame(achievementRequestDto, result);
    }

    @Test
    public void deleteAchievementRequestByIdTest() {
        long achievementRequestId = 1L;
        achievementRequestService.deleteById(achievementRequestId);
        verify(achievementRequestRepository, times(1)).deleteById(achievementRequestId);
    }
}
