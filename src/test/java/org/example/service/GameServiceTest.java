package org.example.service;

import org.example.config.ApplicationConfigTest;
import org.example.dto.GameDto;
import org.example.entity.Game;
import org.example.repository.impl.GameRepository;
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
public class GameServiceTest {
    @Mock
    private GameRepository gameRepository;

    @Mock
    private CustomMapper mapper;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    public void createGameTest() {
        Game game = new Game();
        GameDto gameDto = new GameDto();
        when(mapper.toEntity(Game.class, gameDto)).thenReturn(game);
        gameService.createGame(gameDto);
        verify(gameRepository, times(1)).save(game);
    }

    @Test
    public void updateGameTest() {
        Game game = new Game();
        GameDto gameDto = new GameDto();
        when(mapper.toEntity(Game.class, gameDto)).thenReturn(game);
        gameService.updateGame(gameDto);
        verify(gameRepository, times(1)).update(game);
    }

    @Test
    public void getByIdTest() {
        long gameId = 1L;
        Game game = new Game();
        GameDto gameDto = new GameDto();
        when(mapper.toDto(GameDto.class, game)).thenReturn(gameDto);
        when(gameRepository.findById(gameId)).thenReturn(game);
        GameDto result = gameService.getById(gameId);
        assertSame(gameDto, result);
    }

    @Test
    public void deleteByIdTest() {
        long gameId = 1L;
        gameService.deleteById(gameId);
        verify(gameRepository, times(1)).deleteById(gameId);
    }
}
