package com.ozgur.giys.api.exeption;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.amqp.core.AmqpMessageReturnedException;

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

    @ExceptionHandler(MaxUploadSizeExceededException.class) 
    public ResponseEntity<Object> handleMaxSizeException(Exception e, WebRequest webRequest){

        System.out.println("File size exception...");

        HttpStatus badRequest = HttpStatus.EXPECTATION_FAILED;

        ApiException apiException = new ApiException("File too large!", badRequest, ZonedDateTime.now(zoneId));
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

    @ExceptionHandler({ AmqpMessageReturnedException.class })
    public ResponseEntity<Object> noRouteException(AmqpMessageReturnedException e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException("No route to host", badRequest,
                ZonedDateTime.now(zoneId));
        return new ResponseEntity<>(apiException, badRequest);
    }

}