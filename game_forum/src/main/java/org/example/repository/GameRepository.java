package org.example.repository;

import lombok.Getter;
import org.example.entity.Game;
import org.example.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
public class GameRepository {
    private List<Game> games = new ArrayList<>();

    public void create(Game game){
        games.add(game);
    }

    public Optional<Game> read(long gameId){
        Optional<Game> readGame = games.stream()
                .filter(id -> id.getId() == gameId)
                .findFirst();
        return readGame;
    }

    public void delete(long gameId){
        games = games.stream()
                .filter(game -> game.getId() != gameId)
                .toList();
    }

    public void update(Game updateGame){
        games = games.stream()
                .filter(game -> game.getId() == updateGame.getId())
                .map(game -> updateGame)
                .toList();
    }
}
