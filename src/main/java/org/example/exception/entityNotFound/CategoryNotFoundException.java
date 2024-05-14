package org.example.exception.entityNotFound;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String attribute) {
        super("Category with " + attribute + " not found");
    }
}
