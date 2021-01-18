package com.ozgur.giys.api.exeption;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiException {
    
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;


}