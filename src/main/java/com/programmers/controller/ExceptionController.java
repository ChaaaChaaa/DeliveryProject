package com.programmers.controller;

import com.programmers.dto.exception.ErrorResponseDto;
import com.programmers.exception.StoreNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(StoreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDto handleStoreNotFoundException(StoreNotFoundException e){
        return new ErrorResponseDto("STORE_NOT_FOUND", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception e){
        return new ErrorResponseDto("INTERNAL_SERVER_ERROR",e.getMessage());
    }
}
