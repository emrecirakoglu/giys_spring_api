package com.ozgur.giys.api.exeption;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import org.springframework.http.converter.HttpMessageNotReadableException;

import javax.persistence.EntityNotFoundException;


@RestControllerAdvice
public class ApiExeptionHandler {

    private ZoneId zoneId = ZoneId.of("Europe/Istanbul");

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleApiRequestException(Exception e, WebRequest webRequest) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(zoneId));
        return new ResponseEntity<>(apiException, badRequest);
    }

    
    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<Object> handleTaskTypeException(Exception e, WebRequest webRequest) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(zoneId));
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<Object> notFoundException(EntityNotFoundException e) {

        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(e.getMessage(), notFound,
                ZonedDateTime.now(zoneId));
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> notValidException(MethodArgumentNotValidException e) {

        String message = "Not valid exeption occurred!";

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ( (FieldError) error ).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        int effectedFieldSize = e.getBindingResult().getAllErrors().size();

        HttpStatus notAcceptable = HttpStatus.NOT_ACCEPTABLE;

        NotValidException notValidException = new NotValidException(message, errors, effectedFieldSize, notAcceptable,
                ZonedDateTime.now(zoneId));

        return new ResponseEntity<>(notValidException, notAcceptable);

    }

}