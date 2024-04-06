package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.GameDto;
import org.example.entity.Game;
import org.example.exception.EntityNotFoundException;
import org.example.repository.GameRepository;
import org.example.service.serviceInterface.GameService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final CustomMapper mapper;

    @Override
    public void createGame(GameDto gameDto) {
        Game game = mapper.toEntity(Game.class, gameDto);
        gameRepository.create(game);

    }

    @Override
    public void deleteById(long id) {
        gameRepository.delete(id);
    }

    @Override
    public GameDto getById(long id) {
        Game game = gameRepository.read(id)
                .orElseThrow(() -> new EntityNotFoundException("no such game"));
        System.out.println(gameRepository.getGames().stream()
                .filter(game1 -> game1.getId() == id)
                .toList()
        );
        return mapper.toDto(GameDto.class, game);
    }

    @Override
    public void updateGame(GameDto gameDto) {
        gameRepository.read(gameDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("no such game"));
        Game updateGame = mapper.toEntity(Game.class, gameDto);
        gameRepository.update(updateGame);
    }
}
