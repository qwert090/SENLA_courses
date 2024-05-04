package org.example.service.serviceInterface;

import org.example.dto.GameDto;

public interface GameService {

    void createGame(GameDto gameDto);

    void deleteById(Long id);

    GameDto getById(Long id);

    void updateGame(GameDto gameDto);
}
