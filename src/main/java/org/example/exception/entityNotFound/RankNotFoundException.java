package org.example.exception.entityNotFound;

public class RankNotFoundException extends RuntimeException {

    public RankNotFoundException(String attribute) {
        super("Rank with " + attribute + " not found");
    }
}
