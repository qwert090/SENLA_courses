package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.GameDto;
import org.example.entity.Game;
import org.example.exception.EntityNotFoundException;
import org.example.repository.impl.GameRepository;
import org.example.service.serviceInterface.GameService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final CustomMapper mapper;

    @Override
    public void createGame(GameDto gameDto) {
        Game game = mapper.toEntity(Game.class, gameDto);
        gameRepository.save(game);

    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public GameDto getById(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Game not found"));
        return mapper.toDto(GameDto.class, game);
    }

    @Override
    public void updateGame(GameDto gameDto) {
        Game updateGame = mapper.toEntity(Game.class, gameDto);
        gameRepository.update(updateGame);
    }
}
