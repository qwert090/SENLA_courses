package org.example.repository;

import lombok.Getter;
import org.example.entity.AchievementRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
public class AchievementRequestRepository {
    private List<AchievementRequest> achievementRequests = new ArrayList<>();

    public void create(AchievementRequest achievementRequest){
        achievementRequests.add(achievementRequest);
    }

    public void delete(long achievementRequestId){
        achievementRequests = achievementRequests.stream()
                .filter(achievementRequest -> achievementRequest.getId() != achievementRequestId)
                .toList();
    }

    public Optional<AchievementRequest> read(long achievementRequestId){
        Optional<AchievementRequest> readAchievementRequest = achievementRequests.stream()
                .filter(id -> id.getId() == achievementRequestId)
                .findFirst();
        return readAchievementRequest;
    }

    public void update(AchievementRequest updateAchievementRequest){
        achievementRequests = achievementRequests.stream()
                .filter(achievementRequest -> achievementRequest.getId() == updateAchievementRequest.getId())
                .map(achievementRequest -> updateAchievementRequest)
                .toList();
    }
}
