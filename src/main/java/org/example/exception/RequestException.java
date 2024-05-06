package org.example.exception;

import jakarta.persistence.NoResultException;
import org.example.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class RequestException {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setName("Error");
        errorDto.setMessage("Internal Server Error");
        return errorDto;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
        ErrorDto error = new ErrorDto();
        error.setName("Not found");
        error.setMessage("Oh, it looks like there is no such page here.");
        return error;
    }
    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto handleNoResultException(NoResultException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setName("Error");
        errorDto.setMessage("Not found such result");
        return errorDto;
    }
}
