package org.example.exception;

public class JacksonDeserializeException extends RuntimeException {

    public JacksonDeserializeException(Throwable exception){
        super(exception);
    }
}
