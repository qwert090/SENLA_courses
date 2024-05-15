package org.example.exception.entityNotFound;

public class AchievementRequestNotFoundException extends RuntimeException {

    public AchievementRequestNotFoundException(String attribute) {
        super("AchievementRequest with " + attribute + " not found");
    }
}
