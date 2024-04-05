package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.RankDto;
import org.example.service.serviceInterface.RankService;
import org.example.utils.Json;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RankController {
    private final RankService rankService;
    private final Json json;

    public void createRank(String serializedRank){
        RankDto rankDto = json.deserialize(serializedRank, RankDto.class);
        rankService.createRank(rankDto);
    }

    public void deleteById(long rankId){
        rankService.deleteById(rankId);
    }

    public void updateRank(String serializedRank){
        RankDto rankDto = json.deserialize(serializedRank, RankDto.class);
        rankService.updateRank(rankDto);
    }

    public RankDto getById(long rankId){
        return rankService.getById(rankId);
    }
}
