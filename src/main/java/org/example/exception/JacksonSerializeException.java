package org.example.exception;

public class JacksonSerializeException extends RuntimeException {

    public JacksonSerializeException(Throwable exception){
        super(exception);
    }
}
