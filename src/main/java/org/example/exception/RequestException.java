package org.example.exception;

import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ErrorDto;
import org.example.exception.entityNotFound.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class RequestException {

    @ExceptionHandler(value = {
            UserNotFoundException.class,
            RoleNotFoundException.class,
            RankNotFoundException.class,
            PostNotFoundException.class,
            GameNotFoundException.class,
            CredentialsNotFoundException.class,
            CommentNotFoundException.class,
            CredentialsNotFoundException.class,
            AchievementNotFoundException.class,
            AchievementRequestNotFoundException.class,
            NoHandlerFoundException.class,
            NoResultException.class
    })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto notFoundHandler(RuntimeException runtimeException) {
        log.info("Caught not found exception: {}", runtimeException.getMessage());
        ErrorDto errorDto = new ErrorDto();
        errorDto.setName("Not found exception");
        errorDto.setMessage(runtimeException.getMessage());
        return errorDto;
    }

    @ExceptionHandler(value = {
            AccessDeniedException.class,
            BadCredentialsException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto loginFailedHandler(RuntimeException runtimeException) {
        log.info("Caught login failed exception: {}", runtimeException.getMessage());
        ErrorDto errorDto = new ErrorDto();
        errorDto.setName("Login failed exception");
        errorDto.setMessage(runtimeException.getMessage());
        return errorDto;
    }
}
