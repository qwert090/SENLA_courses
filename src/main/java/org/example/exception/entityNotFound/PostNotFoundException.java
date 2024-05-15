package org.example.exception.entityNotFound;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String attribute) {
        super("Post with " + attribute + " not found");
    }
}
