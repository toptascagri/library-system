package com.example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)

public class ExceptionAlreadyFound extends RuntimeException {

    public ExceptionAlreadyFound(String message){
        super(message);
    }

}
