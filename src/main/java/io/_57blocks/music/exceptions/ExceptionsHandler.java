package io._57blocks.music.exceptions;

import io._57blocks.music.dto.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionsHandler {

    @ExceptionHandler(value = {EmailAlreadyExistException.class, EmailNotExistException.class})
    public ResponseEntity<Object> emailExceptions(MusicAppException exception) {

        log.error(exception.getMessage());

        var headers = new HttpHeaders();
        headers.add("message", exception.getMessage());

        GenericResponse genericResponse = GenericResponse
                .builder()
                .status(HttpStatus.CONFLICT.value())
                .message(exception.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .headers(headers)
                .body(genericResponse);
    }

    @ExceptionHandler(value = {BadCredentialsException.class, UsernameNotFoundException.class}) // when Invalid Credentials
    public ResponseEntity<Object> handleInvalidCredentialsException(
            AuthenticationException exception) {
        log.error(exception.getMessage());

        var headers = new HttpHeaders();
        headers.add("message", exception.getMessage());

        GenericResponse genericResponse = GenericResponse
                .builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(exception.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .headers(headers)
                .body(genericResponse);
    }

    @ExceptionHandler(value = MusicAppException.class)
    public ResponseEntity<Object> musicAppException(MusicAppException exception) {

        log.error(exception.getMessage());

        var headers = new HttpHeaders();
        headers.add("message", exception.getMessage());

        GenericResponse genericResponse = GenericResponse
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .headers(headers)
                .body(genericResponse);
    }

}
