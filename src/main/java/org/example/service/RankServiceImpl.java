package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.RankDto;
import org.example.entity.Rank;
import org.example.exception.EntityNotFoundException;
import org.example.repository.impl.RankRepository;
import org.example.service.serviceInterface.RankService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public void deleteById(Long id) {
        rankRepository.deleteById(id);

    }

    @Override
    public RankDto getById(Long id) {
        Rank rank = rankRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rank not found"));
        return mapper.toDto(RankDto.class, rank);
    }

    @Override
    public void updateRank(RankDto rankDto) {
        Rank updateRank = mapper.toEntity(Rank.class, rankDto);
        rankRepository.update(updateRank);
    }
}
