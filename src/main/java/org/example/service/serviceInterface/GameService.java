package org.example.service.serviceInterface;

import org.example.dto.GameDto;

public interface GameService {

    void createGame(GameDto gameDto);

    void deleteById(long id);

    GameDto getById(long id);

    void updateGame(GameDto gameDto);
}
