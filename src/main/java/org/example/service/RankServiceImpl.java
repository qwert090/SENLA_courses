package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.RankDto;
import org.example.entity.Rank;
import org.example.exception.EntityNotFoundException;
import org.example.repository.RankRepository;
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
        rankRepository.create(rank);

    }

    @Override
    public void deleteById(long id) {
        rankRepository.delete(id);

    }

    @Override
    public RankDto getById(long id) {
        Rank rank = rankRepository.read(id)
                .orElseThrow(() -> new EntityNotFoundException("no such rank"));
        System.out.println(rankRepository.getRanks().stream()
                .filter(rank1 -> rank1.getId() == id)
                .toList()
        );
        return mapper.toDto(RankDto.class, rank);
    }

    @Override
    public void updateRank(RankDto rankDto) {
        rankRepository.read(rankDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("no such rank"));
        Rank updateRank = mapper.toEntity(Rank.class, rankDto);
        rankRepository.update(updateRank);
    }
}
