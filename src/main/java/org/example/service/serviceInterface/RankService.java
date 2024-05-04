package org.example.service.serviceInterface;

import org.example.dto.RankDto;

public interface RankService {

    void createRank(RankDto rankDto);

    void deleteById(Long id);

    RankDto getById(Long id);

    void updateRank(RankDto rankDto);
}
