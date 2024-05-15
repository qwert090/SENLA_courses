package org.example.exception.entityNotFound;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String attribute) {
        super("Role with " + attribute + " not found");
    }
}
