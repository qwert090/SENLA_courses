package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AchievementRequestDto;
import org.example.entity.AchievementRequest;
import org.example.exception.EntityNotFoundException;
import org.example.repository.impl.AchievementRequestRepository;
import org.example.service.serviceInterface.AchievementRequestService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AchievementRequestServiceImpl implements AchievementRequestService {
    private final AchievementRequestRepository achievementRequestRepository;
    private final CustomMapper mapper;


    @Override
    public void createAchievementRequest(AchievementRequestDto achievementRequestDto) {
        AchievementRequest achievementRequest = mapper.toEntity(AchievementRequest.class, achievementRequestDto);
        achievementRequestRepository.save(achievementRequest);

    }

    @Override
    public void deleteById(Long id) {
        achievementRequestRepository.deleteById(id);

    }

    @Override
    public AchievementRequestDto getById(Long id) {
        AchievementRequest achievementRequest = achievementRequestRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Achievement request not found"));
        return mapper.toDto(AchievementRequestDto.class, achievementRequest);
    }

    @Override
    public void updateAchievementRequest(AchievementRequestDto achievementRequestDto) {
        AchievementRequest updateAchievementRequest = mapper.toEntity(AchievementRequest.class, achievementRequestDto);
        achievementRequestRepository.update(updateAchievementRequest);
    }
}
