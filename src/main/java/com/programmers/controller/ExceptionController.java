package com.programmers.controller;

import com.programmers.dto.error.ErrorResponseDto;
import com.programmers.exception.DuplicateNickNameException;
import com.programmers.exception.DuplicatePhoneNumberException;
import com.programmers.exception.FoodNotFoundException;
import com.programmers.exception.InvalidTokenException;
import com.programmers.exception.JwtTokenProviderException;
import com.programmers.exception.LoginFailedException;
import com.programmers.exception.MenuNotFoundException;
import com.programmers.exception.StoreNotFoundException;
import com.programmers.exception.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(DuplicateNickNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto handleStoreNotFoundException(DuplicateNickNameException e) {
        return new ErrorResponseDto("DUPLICATE_NICKNAME", e.getMessage());
    }

    @ExceptionHandler(DuplicatePhoneNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto handleStoreNotFoundException(DuplicatePhoneNumberException e) {
        return new ErrorResponseDto("DUPLICATE_PHONE_NUMBER", e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDto handleStoreNotFoundException(UserNotFoundException e) {
        return new ErrorResponseDto("USER_NOT_FOUND", e.getMessage());
    }

    @ExceptionHandler(StoreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDto handleStoreNotFoundException(StoreNotFoundException e) {
        return new ErrorResponseDto("STORE_NOT_FOUND", e.getMessage());
    }

    @ExceptionHandler(MenuNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDto handleMenuNotFoundException(MenuNotFoundException e) {
        return new ErrorResponseDto("MENU_NOT_FOUND", e.getMessage());
    }

    @ExceptionHandler(FoodNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDto handleFoodNotFoundException(FoodNotFoundException e) {
        return new ErrorResponseDto("FOOD_NOT_FOUND", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception e) {
        return new ErrorResponseDto("INTERNAL_SERVER_ERROR", e.getMessage());
    }


    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseDto handleLoginFailedException(LoginFailedException e) {
        return new ErrorResponseDto("LOGIN_FAILED", e.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseDto handleInvalidTokenException(InvalidTokenException e) {
        return new ErrorResponseDto("INVALID_TOKEN", e.getMessage());
    }

    @ExceptionHandler(JwtTokenProviderException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseDto handleJwtTokenProviderException(JwtTokenProviderException e) {
        return new ErrorResponseDto("JWT_TOKEN_PROVIDER_ERROR", e.getMessage());
    }
}
