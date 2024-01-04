package com.pokeshop.pokemonshop.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UsernameInUseAdvice {
    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String usernameInUseHandler(UserAlreadyExistsException ex){
        return "{ \"code\":1 "
                + ", \"message\":\"" + ex.getMessage() + "\""
                + ", \"type\":\"USERNAME_IN_USE\""
                + "}";
    }
}
