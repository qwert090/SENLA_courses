package org.example.exception.entityNotFound;

public class AchievementNotFoundException extends RuntimeException {

    public AchievementNotFoundException(String attribute) {
        super("Achievement with " + attribute + " not found");
    }
}
