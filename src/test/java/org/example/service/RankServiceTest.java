package org.example.service;

import org.example.config.ApplicationConfigTest;
import org.example.dto.RankDto;
import org.example.entity.Rank;
import org.example.repository.impl.RankRepository;
import org.example.utils.CustomMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(MockitoExtension.class)
public class RankServiceTest {

    @Mock
    private RankRepository rankRepository;

    @Mock
    private CustomMapper mapper;

    @InjectMocks
    private RankServiceImpl rankService;

    @Test
    public void createRankTest() {
        Rank rank = new Rank();
        RankDto rankDto = new RankDto();
        when(mapper.toEntity(Rank.class, rankDto)).thenReturn(rank);
        rankService.createRank(rankDto);
        verify(rankRepository, times(1)).save(rank);
    }

    @Test
    public void updateRankTest() {
        Rank rank = new Rank();
        RankDto rankDto = new RankDto();
        when(mapper.toEntity(Rank.class, rankDto)).thenReturn(rank);
        rankService.updateRank(rankDto);
        verify(rankRepository, times(1)).update(rank);
    }

    @Test
    public void getByIdTest() {
        long rankId = 1L;
        Rank rank = new Rank();
        RankDto rankDto = new RankDto();
        when(mapper.toDto(RankDto.class, rank)).thenReturn(rankDto);
        when(rankRepository.findById(rankId)).thenReturn(rank);
        RankDto result = rankService.getById(rankId);
        assertSame(rankDto, result);
    }

    @Test
    public void deleteByIdTest() {
        long rankId = 1L;
        rankService.deleteById(rankId);
        verify(rankRepository, times(1)).deleteById(rankId);
    }
}
