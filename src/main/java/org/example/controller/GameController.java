package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.GameDto;
import org.example.service.serviceInterface.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping
    public void createGame(@RequestBody GameDto gameDto){
        gameService.createGame(gameDto);
    }

    @DeleteMapping("/{gameId}")
    public void deleteById(@PathVariable("gameId") Long gameId){
        gameService.deleteById(gameId);
    }

    @PutMapping
    public void updateGame(@RequestBody GameDto gameDto){
        gameService.updateGame(gameDto);
    }

    @GetMapping("/{gameId}")
    public GameDto getById(@PathVariable("gameId") Long gamerId){
        return gameService.getById(gamerId);
    }
}
