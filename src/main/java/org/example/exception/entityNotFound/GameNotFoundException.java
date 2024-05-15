package org.example.exception.entityNotFound;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(String attribute) {
        super("Game with " + attribute + " not found");
    }
}
