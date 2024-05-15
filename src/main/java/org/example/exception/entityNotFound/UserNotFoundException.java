package org.example.exception.entityNotFound;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String attribute) {
        super("User with " + attribute + " not found");
    }
}