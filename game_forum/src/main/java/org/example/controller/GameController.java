package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.GameDto;
import org.example.service.serviceInterface.GameService;
import org.example.utils.Json;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final Json json;

    public void createGame(String serializedGame){
        GameDto gameDto = json.deserialize(serializedGame, GameDto.class);
        gameService.createGame(gameDto);
    }

    public void deleteById(long gameId){
        gameService.deleteById(gameId);
    }

    public void updateGame(String serializedGame){
        GameDto gameDto = json.deserialize(serializedGame, GameDto.class);
        gameService.updateGame(gameDto);
    }

    public GameDto getById(long gamerId){
        return gameService.getById(gamerId);
    }
}
