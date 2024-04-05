package org.example.service.serviceInterface;

import org.example.dto.RankDto;

public interface RankService {

    void createRank(RankDto rankDto);

    void deleteById(long id);

    RankDto getById(long id);

    void updateRank(RankDto rankDto);
}
