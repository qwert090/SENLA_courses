package org.example.exception.entityNotFound;

public class CredentialsNotFoundException extends RuntimeException {

    public CredentialsNotFoundException(String attribute) {
        super("Credentials with " + attribute + " not found");
    }
}
