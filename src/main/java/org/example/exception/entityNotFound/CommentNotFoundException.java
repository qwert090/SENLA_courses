package org.example.exception.entityNotFound;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(String attribute) {
        super("Comment with " + attribute + " not found");
    }
}
