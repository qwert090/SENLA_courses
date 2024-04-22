package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.RankDto;
import org.example.entity.Rank;
import org.example.repository.impl.RankRepository;
import org.example.service.serviceInterface.RankService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankServiceImpl implements RankService {
    private final RankRepository rankRepository;
    private final CustomMapper mapper;

    @Override
    public void createRank(RankDto rankDto) {
        Rank rank = mapper.toEntity(Rank.class, rankDto);
        rankRepository.save(rank);

    }

    @Override
    public void deleteById(long id) {
        rankRepository.deleteById(id);

    }

    @Override
    public RankDto getById(long id) {
        Rank rank = new Rank();
        return mapper.toDto(RankDto.class, rank);
    }

    @Override
    public void updateRank(RankDto rankDto) {
        rankRepository.findById(rankDto.getId());
        Rank updateRank = mapper.toEntity(Rank.class, rankDto);
        rankRepository.update(updateRank);
    }
}
