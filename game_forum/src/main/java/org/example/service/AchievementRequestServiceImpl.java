package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.AchievementRequestDto;
import org.example.entity.AchievementRequest;
import org.example.exception.EntityNotFoundException;
import org.example.repository.AchievementRequestRepository;
import org.example.service.serviceInterface.AchievementRequestService;
import org.example.utils.Mapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AchievementRequestServiceImpl implements AchievementRequestService {
    private final AchievementRequestRepository achievementRequestRepository;
    private final Mapper mapper;


    @Override
    public void createAchievementRequest(AchievementRequestDto achievementRequestDto) {
        AchievementRequest achievementRequest = mapper.toEntity(AchievementRequest.class, achievementRequestDto);
        achievementRequestRepository.create(achievementRequest);

    }

    @Override
    public void deleteById(long id) {
        achievementRequestRepository.delete(id);

    }

    @Override
    public AchievementRequestDto getById(long id) {
        AchievementRequest achievementRequest = achievementRequestRepository.read(id)
                .orElseThrow(() -> new EntityNotFoundException("no such achievement request"));
        System.out.println(achievementRequestRepository.getAchievementRequests().stream()
                .filter(achievementRequest1 -> achievementRequest1.getId() == id)
                .toList()
        );
        return mapper.toDto(AchievementRequestDto.class, achievementRequest);
    }

    @Override
    public void updateAchievementRequest(AchievementRequestDto achievementRequestDto) {
        AchievementRequest achievementRequest = achievementRequestRepository.read(achievementRequestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("no such achievement request"));
        achievementRequest.setGameName(achievementRequestDto.getGameName());
        achievementRequest.setAchievementName(achievementRequestDto.getAchievementName());
        achievementRequest.setPlatform(achievementRequestDto.getPlatform());
    }
}
