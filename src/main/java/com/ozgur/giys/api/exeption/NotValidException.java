package com.ozgur.giys.api.exeption;

import java.time.ZonedDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotValidException extends ApiException {

    private final int effectedFieldSize;
    private final Map<String, String> errors;

    public NotValidException(String message, Map<String, String> errors, int effectedFieldSize, HttpStatus httpStatus,
            ZonedDateTime timestamp) {
        super(message, httpStatus, timestamp);
        this.effectedFieldSize = effectedFieldSize;
        this.errors = errors;
    }
}