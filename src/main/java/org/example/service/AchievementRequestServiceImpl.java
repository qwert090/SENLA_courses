package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.AchievementRequestDto;
import org.example.entity.AchievementRequest;
import org.example.repository.impl.AchievementRequestRepository;
import org.example.service.serviceInterface.AchievementRequestService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

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
    public void deleteById(long id) {
        achievementRequestRepository.deleteById(id);

    }

    @Override
    public AchievementRequestDto getById(long id) {
        AchievementRequest achievementRequest = achievementRequestRepository.findById(id);
        return mapper.toDto(AchievementRequestDto.class, achievementRequest);
    }

    @Override
    public void updateAchievementRequest(AchievementRequestDto achievementRequestDto) {
        achievementRequestRepository.findById(achievementRequestDto.getId());
        AchievementRequest updateAchievementRequest = mapper.toEntity(AchievementRequest.class, achievementRequestDto);
        achievementRequestRepository.update(updateAchievementRequest);
    }
}
